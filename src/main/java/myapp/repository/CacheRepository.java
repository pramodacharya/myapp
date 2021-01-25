package myapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Implementation of {@link )
 */
@Repository
public class CacheRepository {

    private RedisTemplate<String,Boolean> redisTemplate;
    private ValueOperations<String, Boolean> valueOps;

    @Autowired
    public CacheRepository(RedisTemplate<String,Boolean> redisTemplate) {
        this.redisTemplate = redisTemplate;
        valueOps = redisTemplate.opsForValue();
    }

    /**
     * Save the key value pair in cache with a ttl
     *
     * @param key   cache key
     * @param value cache value
     */
    public void put(String key, Boolean value) {
        try {
            valueOps.set(key, value);
        } catch (Exception e) {
            throw new RuntimeException("Error while saving to cache ", e);
        }
    }

    /**
     * Returns the cached value
     *
     * @param key cached key
     * @return cached value
     */
    public Optional<Boolean> get(String key) {
        try {
            Boolean b = redisTemplate.hasKey(key);
            if (Boolean.TRUE.equals(b)) {
                return Optional.ofNullable(valueOps.get(key));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while retrieving from the cache ", e);
        }
    }
}
