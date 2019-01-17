package com.base.wang.web;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by wxb on 2018/9/28.
 */
public class CookieSessionHolder {
    Map<String,String> map = new HashMap<String,String>();

    private Set<String> changedKeySet = new HashSet<String>();
    private Set<String> removedKeySet = new HashSet<String>();

    public CookieSessionHolder() {
    }

    public CookieSessionHolder(Map<String, String> map){
        this.map = map;
    }


    public CookieSessionHolder add(String key, String value){
        map.put(key,value);
        changedKeySet.add(key);
        removedKeySet.remove(key);
        return this;
    }
    public void remove(String key){
        map.remove(key);
        changedKeySet.remove(key);
        removedKeySet.add(key);
    }
    public void clear(){
        changedKeySet.removeAll(map.keySet());
        removedKeySet.addAll(map.keySet());
        map.clear();
    }
    public String find(String key){
        return map.get(key);
    }

    public Set<String> getChangedKeySet() {
        return changedKeySet;
    }
    public Set<String> getRemovedKeySet() {
        return removedKeySet;
    }
}
