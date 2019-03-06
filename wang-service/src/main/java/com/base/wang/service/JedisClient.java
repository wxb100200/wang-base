package com.base.wang.service;

import java.util.List;

/**
 * Created by wxb on 2019/3/5.
 */
public interface JedisClient {
    String get(String key);//读取数据
    String set(String key,String value);//向redis中写入数据
    String hget(String hkey,String key);//获取存储结构是hashMap类型的数据
    long hset(String hkey,String key,String value);
    long incr(String key);
    long expire(String key,int second);//设置缓存生存时间
    long ttl(String key);
    long del(String key);//删除数据
    long hdel(String hkey,String key);

    //---》》》》List
    /**
     * 在名称为key的list尾添加一个值为value的元素
     */
    Long rpush(String key, String... strings);
    /**
     * 在名称为key的list头添加一个值为value的 元素
     */
    Long lpush(String key, String... strings);
    /**
     * 返回名称为key的list的长度
     */
    Long llen(String key);
    /**
     * 返回名称为key的list中start至end之间的元素（下标从0开始，下同）
     */
    List<String> lrange(String key, long start, long end);
    /**
     * 截取名称为key的list，保留start至end之间的元素
     */
    String ltrim(String key, long start, long end);
    /**
     * 返回名称为key的list中index位置的元素
     */
    String lindex(String key, long index);

    /***
     * 给名称为key的list中index位置的元素赋值为value
     */
    String lset(String key, long index, String value);

    /**
     * 删除count个名称为key的list中值为value的元素。count为0，删除所有值为value的元素，count>0      从头至尾删除count个值为value的元素，count<0从尾到头删除|count|个值为value的元素。
     */
    Long lrem(String key, long count, String value);

    /**
     * 返回并删除名称为key的list中的首元素
     */
    String lpop(String key);
    /**
     * 返回并删除名称为key的list中的尾元素
     */
    String rpop(String key);


}
