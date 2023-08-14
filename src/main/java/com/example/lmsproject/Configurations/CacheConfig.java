package com.example.lmsproject.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Configuration class for Redis cache setup.
 * This configuration ensures that the application is connected to a local Redis instance.
 * It is essential to have Redis installed and running on the localhost to make this configuration work.
 */
@Configuration
public class CacheConfig {

    /**
     * Provides configuration for Redis connection factory.
     * This bean creates a connection to a standalone Redis instance running on the localhost at the default port 6379.
     *
     * @return A RedisConnectionFactory for creating Redis connections.
     */
    @Bean
    RedisConnectionFactory getRedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    /**
     * Provides a customized RedisTemplate to ease the operations on Redis cache.
     * This template is set up with specific serializers for keys and values:
     * - StringRedisSerializer for keys
     * - JdkSerializationRedisSerializer for values
     *
     * @return A RedisTemplate configured for the application's Redis operations.
     */
    @Bean
    RedisTemplate<String, Object> getTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(getRedisConnectionFactory());
        return redisTemplate;
    }
}
