package com.tanwei.spring.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 这里封装一个Jackson的通用工具类
 *
 * @author tanwei
 * @date 2024-04-01 14:29
 **/
@Slf4j
public class JSONUtil {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setDateFormat(new SimpleDateFormat(DateUtil.YMD_HMS));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(getJavaTimeModule()); // 添加时间相关格式化
    }

    /**
     * JSON字符串转对象
     * @param content 字符串内容
     * @param clazz 对象元数据信息
     * @return 对象
     * @param <T> 泛型
     */
    public static <T> T parseObject(String content, Class<T> clazz) {
        T obj = null;
        try {
            obj = objectMapper.readValue(content, clazz);
        } catch (JsonProcessingException ex) {
            log.error("Parse to object is fail. {}", ex.getMessage());
        }
        return obj;

    }

    /**
     * 对象转换为字符串
     * @param obj 对象
     * @return 字符串
     */
    public static String toJSONString(Object obj) {
        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            log.error("Convert to string is fail. {}", ex.getMessage());
        }
        return jsonStr;
    }


    /**
     * Jackson java8 time时间包序列化处理
     * use: 字段@JsonFormat(pattern="yyyy-MM-dd")
     *
     * @return JavaTimeModule
     */
    private static JavaTimeModule getJavaTimeModule() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        javaTimeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateUtil.YMD_HMS)));
        javaTimeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateUtil.YMD_HMS)));

        javaTimeModule.addSerializer(LocalDate.class,
                new LocalDateSerializer(DateTimeFormatter.ofPattern(DateUtil.YMD)));
        javaTimeModule.addDeserializer(LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateUtil.YMD)));

        javaTimeModule.addSerializer(LocalTime.class,
                new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateUtil.HMS)));
        javaTimeModule.addDeserializer(LocalTime.class,
                new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateUtil.HMS)));
        return javaTimeModule;
    }
}
