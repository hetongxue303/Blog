package com.blog.utils;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 *
 * @author hy
 * @version 1.0
 */
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // Hash类型

    /**
     * 根据 key 获取变量中的键值对
     *
     * @param key key
     * @return redis中的hash对象
     */
    public <T> Map<String, T> getHashMap(final String key) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.entries(key);
    }

    /**
     * 根据 key 获取hash值
     *
     * @param key key
     * @return redis中的hash对象
     */
    public <T> T getHashMap(final String key, final String hashKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hashKey);
    }

    /**
     * 存储hash对象
     *
     * @param key     key
     * @param hashKey hashKey
     * @param maps    maps
     */
    public <T> void setHashMap(final String key, final String hashKey, final Map<String, T> maps) {
        redisTemplate.opsForHash().put(key, hashKey, maps);
    }

    /**
     * 存储hash对象
     *
     * @param key  key
     * @param maps maps
     */
    public <T> void setHashMap(final String key, final Map<String, T> maps) {
        if (!Objects.isNull(maps))
            redisTemplate.opsForHash().putAll(key, maps);
    }

    public <T> void setHashMap(final String key, final String hashKey, final T maps) {
        if (!Objects.isNull(maps))
            redisTemplate.opsForHash().put(key, hashKey, maps);
    }

    public <T> void deleteHashMap(final String key, final String hashKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        opsForHash.delete(key, hashKey);
    }

    public <T> List<T> getMultiCacheMap(final String key, final Collection<T> hKeys) {
        HashOperations<String, T, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.multiGet(key, hKeys);
    }

    /**
     * 对hash中的值递增
     */
    public void incrementHashMapValue(String key, String hashKey, int val) {
        redisTemplate.opsForHash().increment(key, hashKey, val);
    }

    // 基本对象类型

    /**
     * 根据 key 获取对象值
     *
     * @param key key
     * @return redis中的对象
     */
    public <T> T getObject(final String key, Class<T> clazz) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    public Object getObject(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Object getObjectGK(final String prefix) {
        return redisTemplate.opsForValue().get(GK(prefix));
    }

    public Object getObjectGK(final String prefix, final String middle) {
        return redisTemplate.opsForValue().get(GK(prefix, middle));
    }

    public Object getObjectGK(final String prefix, final String middle, final String suffix) {
        return redisTemplate.opsForValue().get(GK(prefix, middle, suffix));
    }


    /**
     * 存储对象
     *
     * @param key      key
     * @param object   value
     * @param timeout  超时时间
     * @param timeUnit 时间单位
     */
    public <T> void setObject(final String key, final T object, final long timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, object, timeout, timeUnit);
    }

    /**
     * 存储对象
     *
     * @param key    key
     * @param object value
     */
    public <T> void setObject(final String key, final T object) {
        redisTemplate.opsForValue().set(key, object);
    }

    /**
     * 修改 key 值名称
     *
     * @param oldKey 旧key
     * @param newKey 新key
     */
    public void renameKey(final String oldKey, final String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * 根据 key 删除
     *
     * @param key key
     */
    public void delete(final String key) {
        redisTemplate.delete(key);
    }

    public void deleteGK(final String prefix) {
        redisTemplate.delete(GK(prefix));
    }

    public void deleteGK(final String prefix, final String middle) {
        redisTemplate.delete(GK(prefix, middle));
    }

    public void deleteGK(final String prefix, final String middle, final String suffix) {
        redisTemplate.delete(GK(prefix, middle, suffix));
    }

    /**
     * 根据 key 批量删除
     *
     * @param keys keys
     */
    public void delete(final Collection<String> keys) {
        redisTemplate.delete(keys);
    }


    // 其他操作

    /**
     * 设置 key 的过期时间
     *
     * @param key      key
     * @param timeout  超时时间
     * @param timeUnit 时间单位
     * @return 设置状态
     */
    public Boolean setKeyExpire(final String key, final Long timeout, final TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    public Long incrExpire(String key, long time) {
        Long count = redisTemplate.opsForValue().increment(key, 1);
        if (count != null && count == 1) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
        return count;
    }

    /**
     * 设置 key 的过期时间
     *
     * @param key  key
     * @param date 在哪个日期
     * @return 设置状态
     */
    public Boolean setKeyExpireAt(final String key, final Date date) {
        return redisTemplate.expireAt(key, date);
    }

    /**
     * 设置 key 的过期时间
     *
     * @param key      key
     * @param expireAt 在哪个时间
     * @return 设置状态
     */
    public Boolean setKeyExpireAt(final String key, final Instant expireAt) {
        return redisTemplate.expireAt(key, expireAt);
    }

    /**
     * 查找匹配的key值，返回一个Set集合类型
     *
     * @param key key
     * @return set集合
     */
    public Set<String> getPatternKey(final String key) {
        return redisTemplate.keys(key);
    }


    /**
     * 返回传入key所存储的值的类型
     *
     * @param key key
     * @return DataType
     */
    public DataType getKeyType(final String key) {
        return redisTemplate.type(key);
    }

    /**
     * 如果旧值存在时，将旧值改为新值
     *
     * @param newKey 新key
     * @param oldKey 旧key
     * @return 修改状态
     */
    public Boolean renameOldKeyIfAbsent(final String oldKey, final String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 将当前传入的key值序列化为byte[]类型
     *
     * @param key key
     * @return 序列化后的值
     */
    public byte[] dump(final String key) {
        return redisTemplate.dump(key);
    }

    /**
     * 随机取key
     *
     * @return 随机key值
     */
    public String randomKey() {
        return redisTemplate.randomKey();
    }

    /**
     * 返回当前key所对应的剩余过期时间
     *
     * @param key key
     * @return 剩余时间(单位 ： 毫秒)
     */
    public Long getExpire(final String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 返回剩余过期时间并且指定时间单位
     *
     * @param key      key
     * @param timeUnit 时间单位
     * @return 剩余时间(单位 ： 毫秒)
     */
    public Long getExpire(final String key, final TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    /**
     * 将key持久化保存
     *
     * @param key key
     * @return 设置状态
     */
    public Boolean persistKey(final String key) {
        return redisTemplate.persist(key);
    }

    /**
     * 将当前数据库的key移动到指定redis中数据库当中
     *
     * @param key     key
     * @param dbIndex 数据库索引
     * @return 操作状态
     */
    public Boolean moveToDbIndex(final String key, final int dbIndex) {
        return redisTemplate.move(key, dbIndex);
    }

    /**
     * 判断是否有 key 值
     *
     * @param key key
     * @return key状态
     */
    public Boolean hasKey(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 批量获取值
     *
     * @param keys keys
     * @return 值集合
     */
    public List<Object> multiGet(final Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    // 生成键
    public String GK(final String prefix) {
        return prefix + ":";
    }

    public <T extends String> String GK(final String prefix, final T middle) {
        return prefix + ":" + middle + ":";
    }

    public <T extends String> String GK(final String prefix, final T middle, final T suffix) {
        return prefix + ":" + middle + ":" + suffix + ":";
    }

}