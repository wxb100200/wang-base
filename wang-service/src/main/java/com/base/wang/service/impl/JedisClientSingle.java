package com.base.wang.service.impl;


import com.base.wang.service.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;


public class JedisClientSingle implements JedisClient {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String string = jedis.get(key);
        jedis.close();
        return string;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String string = jedis.set(key, value);
        jedis.close();
        return string;
    }

    @Override
    public String hget(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        String string = jedis.hget(hkey, key);
        jedis.close();
        return string;
    }

    @Override
    public long hset(String hkey, String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(hkey, key, value);
        jedis.close();
        return result;
    }

    @Override
    public long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    @Override
    public long expire(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.expire(key, second);
        jedis.close();
        return result;
    }

    @Override
    public long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    @Override
    public long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }

    @Override
    public long hdel(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hdel(hkey, key);
        jedis.close();
        return result;
    }
    //---》》》》List
    @Override
    public Long rpush(String key, String... strings) {
        Jedis jedis = jedisPool.getResource();
        Long result=jedis.rpush(key,strings);
        jedis.close();
        return result;
    }

    @Override
    public Long lpush(String key, String... strings) {
        Jedis jedis = jedisPool.getResource();
        Long result=jedis.lpush(key,strings);
        jedis.close();
        return result;
    }

    @Override
    public Long llen(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result=jedis.llen(key);
        jedis.close();
        return result;
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        Jedis jedis = jedisPool.getResource();
        List<String> list=jedis.lrange(key,start,end);
        jedis.close();
        return list;
    }

    @Override
    public String ltrim(String key, long start, long end) {
        Jedis jedis = jedisPool.getResource();
        String result=jedis.ltrim(key,start,end);
        jedis.close();
        return result;
    }

    @Override
    public String lindex(String key, long index) {
        Jedis jedis = jedisPool.getResource();
        String result=jedis.lindex(key,index);
        jedis.close();
        return result;
    }

    @Override
    public String lset(String key, long index, String value) {
        Jedis jedis = jedisPool.getResource();
        String result=jedis.lset(key,index,value);
        jedis.close();
        return result;
    }

    @Override
    public Long lrem(String key, long count, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result=jedis.lrem(key,count,value);
        jedis.close();
        return result;
    }

    @Override
    public String lpop(String key) {
        Jedis jedis = jedisPool.getResource();
        String result=jedis.lpop(key);
        jedis.close();
        return result;
    }

    @Override
    public String rpop(String key) {
        Jedis jedis = jedisPool.getResource();
        String result=jedis.rpop(key);
        jedis.close();
        return result;
    }

}