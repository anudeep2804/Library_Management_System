package com.example.lmsproject.repositories;

import com.example.lmsproject.models.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * This repository is responsible for managing MyUser entities cached in Redis.
 * Using Redis as a cache can help in reducing the load on the primary database
 * by retrieving frequently accessed user data from the cache.
 */
@Repository
public class MyUserCacheRepository {

    // Prefix for the cache key to differentiate MyUser entities in the Redis store
    private final String USER_KEY_PREFIX = "user:";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;  // The RedisTemplate helps in performing Redis operations.

    /**
     * Caches the provided MyUser entity in Redis.
     * This cache will expire after 24 hours.
     *
     * @param myUser The MyUser entity to cache.
     */
    public void set(MyUser myUser){
        String key = getKey(myUser.getUsername());
        redisTemplate.opsForValue().set(key, myUser, 24, TimeUnit.HOURS);
    }

    /**
     * Fetches the MyUser entity associated with the given username from the Redis cache.
     * Returns null if the entity is not present in the cache.
     *
     * @param username The username of the MyUser entity to retrieve.
     * @return The cached MyUser entity, or null if not found.
     */
    public MyUser get(String username){
        String key = getKey(username);
        return (MyUser) redisTemplate.opsForValue().get(key);
    }

    /**
     * Generates the cache key for the MyUser entity based on the given username.
     * The key will have a prefix defined by USER_KEY_PREFIX.
     *
     * @param username The username of the MyUser entity.
     * @return The generated cache key.
     */
    private String getKey(String username) {
        return USER_KEY_PREFIX + username;
    }
}
