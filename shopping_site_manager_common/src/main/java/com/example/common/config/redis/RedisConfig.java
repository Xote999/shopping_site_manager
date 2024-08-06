package com.example.common.config.redis;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
// キャッシュを有効にする
@EnableCaching
public class RedisConfig {

    @Bean
    @SuppressWarnings(value = { "unchecked", "rawtypes" })
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        // StringRedisSerializerを使用して、Redisのキーをシリアライズおよびデシリアライズする
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        // HashキーもStringRedisSerializerのシリアライズ方式を採用する
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }

//    @Bean
//    public DefaultRedisScript<Long> limitScript() {
//        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
//        redisScript.setScriptText(limitScriptText());
//        redisScript.setResultType(Long.class);
//        return redisScript;
//    }
//
//    /**
//     * レート制限スクリプト
//     */
//    private String limitScriptText() {
//        return "local key = KEYS[1]\n" +
//                "local count = tonumber(ARGV[1])\n" +
//                "local time = tonumber(ARGV[2])\n" +
//                "local current = redis.call('get', key);\n" +
//                "if current and tonumber(current) > count then\n" +
//                "    return tonumber(current);\n" +
//                "end\n" +
//                "current = redis.call('incr', key)\n" +
//                "if tonumber(current) == 1 then\n" +
//                "    redis.call('expire', key, time)\n" +
//                "end\n" +
//                "return tonumber(current);";
//    }
}