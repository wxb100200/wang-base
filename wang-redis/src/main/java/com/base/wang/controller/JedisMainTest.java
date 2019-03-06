package com.base.wang.controller;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @DESC redis的list作为天然的库存队列。
 * 利用这一点，可以做一个list作为库存的公共资源，每次取值成功，就证明抢到商品了，否则，抢购失败
 * @Contact businessfgl@163.com
 */
public class JedisMainTest {
    static final JedisPool pool = new JedisPool(new JedisPoolConfig() {{
        setMaxIdle(1000);
        setMaxTotal(1000);
        setTestOnBorrow(true);
    }}, "127.0.0.1", 6379);

    static final int stores = 1000;//库存量
    static final String storesName = "stores";//抢购商品存放仓库名称
    static final String storesCountKey = "storesCountKey";//记录以往仓库中存放商品的数量的KEY
    static final String setSuccessName = "setSuccessName";//记录抢购成功用户的set集合KEY
    static final String setFailName = "setFailName";//记录抢购失败用户的set集合KEY


    public static void main(String[] args) throws InterruptedException {


        ExecutorService executor = Executors.newFixedThreadPool(1000);
        ExecutorService executor1 = Executors.newFixedThreadPool(1000);
        Jedis jedis = pool.getResource();

        initStores();//初始化
        incrStores(10);//动态增加10个商品
        jedis.close();

        for (int i = 0; i < 1000; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int c = 0; c < 100; c++) {

                        executor1.execute(new MyRunnable());
                    }
                }
            });
//            Thread.sleep(50);
        }
        executor.shutdown();

//        System.exit(1);
    }

    /*初始化库存*/
    public static void initStores() {
        Jedis jedis = pool.getResource();
//        if(jedis.llen(storesName)>0){
//            throw new RuntimeException("库存值大于0，不可初始化");
//        }

        jedis.del(storesName, setSuccessName, setFailName);//删除仓库，删除抢购成功队列，删除抢购失败队列
        jedis.set(storesCountKey, stores + "");
        for (int i = 1; i <= stores; i++) {
            String storeProp = i + ":" + UUID.randomUUID().toString();//模拟   序号:ID
            jedis.rpush(storesName, storeProp);
        }
    }

    /*动态增加库存*/
    public static void incrStores(int stores) {
        Jedis jedis = pool.getResource();
        int storesCount = Integer.valueOf(jedis.get(storesCountKey));
        jedis.set(storesCountKey,(storesCount+stores)+"");//更新库存数量
        for (int i = 1 + storesCount; i <= stores + storesCount; i++) {
            String storeProp = i + ":" + UUID.randomUUID().toString();//模拟   序号:ID
            jedis.rpush(storesName, storeProp);
        }
    }


    static class MyRunnable implements Runnable {
        Jedis jedis = pool.getResource();

        public MyRunnable() {
        }

        @Override
        public void run() {
            try {

                String userifo = UUID.randomUUID().toString();

                //redis list取值
                String ls = jedis.lpop(storesName);

                //取值成功，说明拿到了商品,即抢购成功
                if (ls != null) {
                    String[] er = ls.split(":");
                    int num = Integer.valueOf(er[0]);//商品序号
                    String ID = er[1];//商品ID

                    /* 抢购成功业务逻辑 */
                    jedis.sadd(setSuccessName, userifo);
                    System.out.println("用户：" + userifo + "抢购成功，当前抢购成功人数:"
                            + num + "------抢购成功商品ID:" + ID);

                    Thread.sleep(900);//模拟业务执行时间

                } else {
                    System.out.println("用户：" + userifo + "抢购失败，库存以空");
                    jedis.sadd(setFailName, userifo);
                    Thread.sleep(400);//模拟业务执行时间
                    // Thread.sleep(500);
                    return;
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }

        }

    }
}