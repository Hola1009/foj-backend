package com.fancier.foj.common.security.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Configuration
public class WebConfig {
     @Bean
     public Jackson2ObjectMapperBuilderCustomizer customizeLocalDateTimeFormat() {
         return jacksonObjectMapperBuilder -> {
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
             LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(formatter);
             LocalDateTimeSerializer serializer = new LocalDateTimeSerializer(formatter);
             jacksonObjectMapperBuilder.serializerByType(LocalDateTime.class, serializer);
             jacksonObjectMapperBuilder.deserializerByType(LocalDateTime.class, deserializer);
             // 创建一个 SimpleModule
             SimpleModule module = new SimpleModule();
             // 将 long 类型和 Long 类型序列化为字符串
             module.addSerializer(Long.class, ToStringSerializer.instance);
             module.addSerializer(long.class, ToStringSerializer.instance);
             // 注册模块到 ObjectMapper 中
             jacksonObjectMapperBuilder.modules(module);

             // 配置不序列化空属性
             jacksonObjectMapperBuilder
                     .serializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
         };
     }
}
