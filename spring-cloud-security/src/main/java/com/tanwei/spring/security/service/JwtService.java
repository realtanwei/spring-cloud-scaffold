package com.tanwei.spring.security.service;

import com.tanwei.spring.security.model.SysUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

/**
 * @author tanwei
 * @date 2023-07-07 14:24
 **/
@Component
public class JwtService {

    /**
     * 创建一个最终字符串，这个字符串称为密钥
     * https://allkeysgenerator.com/
     *
     * JWT最低要求的安全级别是256bit
     */
    private static final String SECRET_KEY = "3F4428472B4B6250655368566D5971337336763979244226452948404D635166";

    private static final String JWT_ID = "tokenId";

    public static final String TOKEN_HEADER = "x-token";

    /**
     * token 过期时间, 单位: 秒
     */
    private static final long TOKEN_EXPIRED_TIME = 8 * 60 * 60 * 1000;


    /**
     * 创建token
     *
     * @param claims  加密信息
     * @param expireTime 过期时间
     * @return
     */
    private String createToken(Claims claims, long expireTime) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date();

        Key key = getSignInKey();
        long nowMillis = System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(JWT_ID)
                .setIssuedAt(now)
                .signWith(key, signatureAlgorithm);
        if (expireTime >= 0) {
            long expMillis = nowMillis + expireTime;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 生成token信息
     *
     * @param sysUser 用户信息
     * @return token
     */
    public String generateToken(SysUser sysUser) {
        Claims claims = new DefaultClaims();
        claims.setSubject(sysUser.getUsername());
        claims.setId(String.valueOf(sysUser.getId()));
        return this.createToken(claims, TOKEN_EXPIRED_TIME);
    }

    /**
     * 1、解析token字符串中的加密信息【加密算法&加密密钥】, 提取所有声明的方法
     * @param token
     * @return
     */
    private Claims extractAllClaims(String token) throws MalformedJwtException {
        return Jwts
                .parserBuilder()
                // 获取alg开头的信息
                .setSigningKey(getSignInKey())
                .build()
                // 解析token字符串
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 2、获取签名密钥的方法
     * @return 基于指定的密钥字节数组创建用于HMAC-SHA算法的新SecretKey实例
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 3、解析token字符串中的权限信息
     * @param token
     * @return
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 4、从token中解析出username
     * @param token
     * @return
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 5、判断token是否过期
     * @param
     * @return
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        // 从token中获取用户名
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) &&!isTokenExpired(token);
    }

    /**
     * 6、验证token是否过期
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    /**
     * 6.1、从授权信息中获取token过期时间
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
