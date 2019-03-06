package com.base.wang.service.impl;

import com.base.wang.service.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;


import redis.clients.jedis.JedisCluster;

import java.util.List;

public class JedisClientCluster implements JedisClient {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    @Override
    public String hget(String hkey, String key) {
        return jedisCluster.hget(hkey, key);
    }

    @Override
    public long hset(String hkey, String key, String value) {
        return jedisCluster.hset(hkey, key, value);
    }

    @Override
    public long incr(String key) {
        return jedisCluster.incr(key);
    }

    @Override
    public long expire(String key, int second) {
        return jedisCluster.expire(key, second);
    }

    @Override
    public long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    @Override
    public long del(String key) {

        return jedisCluster.del(key);
    }

    @Override
    public long hdel(String hkey, String key) {

        return jedisCluster.hdel(hkey, key);
    }

    @Override
    public Long rpush(String key, String... strings) {
        return jedisCluster.rpush(key,strings);
    }

    @Override
    public Long lpush(String key, String... strings) {
        return jedisCluster.rpush(key,strings);
    }

    @Override
    public Long llen(String key) {
        return jedisCluster.llen(key);
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        return jedisCluster.lrange(key,start,end);
    }

    @Override
    public String ltrim(String key, long start, long end) {
        return jedisCluster.ltrim(key,start,end);
    }

    @Override
    public String lindex(String key, long index) {
        return jedisCluster.lindex(key,index);
    }

    @Override
    public String lset(String key, long index, String value) {
        return jedisCluster.lset(key,index,value);
    }

    @Override
    public Long lrem(String key, long count, String value) {
        return jedisCluster.lrem(key,count,value);
    }

    @Override
    public String lpop(String key) {
        return jedisCluster.lpop(key);
    }

    @Override
    public String rpop(String key) {
        return jedisCluster.rpop(key);
    }

}