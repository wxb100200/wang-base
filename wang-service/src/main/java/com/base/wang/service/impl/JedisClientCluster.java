package com.base.wang.service.impl;

import com.base.wang.service.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;


import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public List<String> hmget(String hkey, String... field) {
        return jedisCluster.hmget(hkey, field);
    }

    @Override
    public String hmset(String hkey, Map<String, String> map) {
        return jedisCluster.hmset(hkey, map);
    }

    @Override
    public Boolean hexists(String hkey, String field) {
        return jedisCluster.hexists(hkey, field);
    }

    @Override
    public Long hdel(String hkey, String... field) {
        return jedisCluster.hdel(hkey, field);
    }

    @Override
    public Long hlen(String hkey) {
        return jedisCluster.hlen(hkey);
    }

    @Override
    public Set<String> hkeys(String hkey) {
        return jedisCluster.hkeys(hkey);
    }

    @Override
    public List<String> hvals(String hkey) {
        return jedisCluster.hvals(hkey);
    }

    @Override
    public Map<String, String> hgetAll(String hkey) {
        return jedisCluster.hgetAll(hkey);
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
    public Boolean exists(String key) {
        return jedisCluster.exists(key);
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
    public Long move(String key, int dbindex) {
        return jedisCluster.move(key,dbindex);
    }
    @Override
    public long del(String key) {
        return jedisCluster.del(key);
    }
    @Override
    public long del(String... key) {
        return 0;
    }

    @Override
    public String type(String key) {
        return jedisCluster.type(key);
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
    //---》》》》Set
    @Override
    public Long sadd(String key, String... member) {
        return jedisCluster.sadd(key,member);
    }

    @Override
    public Long srem(String key, String... member) {
        return jedisCluster.srem(key,member);
    }

    @Override
    public String spop(String key) {
        return jedisCluster.spop(key);
    }

    @Override
    public Long scard(String key) {
        return jedisCluster.scard(key);
    }

    @Override
    public Boolean sismember(String key, String member) {
        return jedisCluster.sismember(key,member);
    }

    @Override
    public String srandmember(String key) {
        return jedisCluster.srandmember(key);
    }

}