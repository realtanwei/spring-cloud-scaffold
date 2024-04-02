package com.tanwei.spring.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tanwei.spring.core.utils.DateUtil;
import com.tanwei.spring.core.utils.JSONUtil;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author tanwei
 * @date 2024-04-01 15:16
 **/
public class JSONUtilTests {

    @Test
    void jsonFormatTest() {

        Article article = new Article();
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(new Date());

        try {
            String s = JSONUtil.toJSONString(article);
            System.out.println(s);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

@Data
class Article {

    @JsonFormat(pattern = DateUtil.YMD_HMS)
    private LocalDateTime createTime;

    @JsonFormat(pattern = DateUtil.HMS)
    private Date updateTime;

}