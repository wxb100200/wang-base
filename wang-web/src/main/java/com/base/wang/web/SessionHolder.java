package com.base.wang.web;

import com.base.wang.entity.BasUser;
import com.base.wang.util.DateUtil;
import com.base.wang.util.MessageDigestUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by wxb on 2018/9/28.
 */
public class SessionHolder {
    final static String COOKIE_HEAD="_wang_";
    final static String USER_ID = "UID";  //用户ID的cookie key

    private static ThreadLocal<BasUser> user = new ThreadLocal<BasUser>();
    private static ThreadLocal<CookieSessionHolder> holder = new ThreadLocal<CookieSessionHolder>();

    public SessionHolder() {
    }

    public static void init(HttpServletRequest request){
        CookieSessionHolder h = get(request);
        holder.set(h);
        user.remove();
    }
    private static CookieSessionHolder get(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return new CookieSessionHolder();
        }

        Map<String,String> map=parseCookies(cookies);
        CookieSessionHolder cookieSessionHolder=new CookieSessionHolder(map);
        return cookieSessionHolder;
    }
    public static List<Cookie> clear(){
        user.set(null);

        CookieSessionHolder h=holder.get();
        h.clear();
        return toCookies();
    }

    private static Map<String,String> parseCookies(Cookie[] cookies){
        if(cookies==null) return null;
        Map<String,String> map=new HashMap<>();
        for(Cookie cookie:cookies){
            String cookieName=cookie.getName();
            if(!cookieName.startsWith(COOKIE_HEAD)) continue;
            String name=cookieName.substring(COOKIE_HEAD.length());
            byte[] bytes= MessageDigestUtil.parseHexString(cookie.getValue());
            try{
                String value=new String(bytes,"utf-8");
                String id=value.substring(0,value.indexOf("="));
                String time=value.substring(value.indexOf("=")+1);
                Date date= DateUtil.parseDate(time,"yyyy-MM-dd HH:mm:ss");
                if(date.compareTo(new Date())>0){
                    map.put(name, id);
                }
            }catch (Exception e){

            }
        }
        return map;
    }

    public static void setCurrentUser(BasUser u ){
        user.set(u);
        CookieSessionHolder h = getOrCreateCookieSessionHolder();
        if(u==null){
            h.remove(USER_ID);
        }else {
            h.add(USER_ID, "" + u.getId());
        }
    }
    private static CookieSessionHolder getOrCreateCookieSessionHolder(){
        CookieSessionHolder h = holder.get();
        if(h!=null){
            return h;
        }
        h =new CookieSessionHolder();
        holder.set(h);
        return h;
    }

    public static Integer getCurrentUserId(){
        if(user.get()!=null){
            return user.get().getId();
        }
        String personId = getOrCreateCookieSessionHolder().find(USER_ID);
        if(personId==null){
            return null;
        }
        return Integer.valueOf(personId);
    }

    /**
     * 每一组key,value对应一个cookie
     * */
    public static List<Cookie> toCookies(){
        CookieSessionHolder h=holder.get();
        List<Cookie> cookieList=new ArrayList<Cookie>();

        // add cookies
        for(String key: h.getChangedKeySet()){
            String name=COOKIE_HEAD+key;
            try {
                Date todayLastDate= DateUtil.todayLastDate();
                String str=h.find(key)+"="+DateUtil.formatDate(todayLastDate,"yyyy-MM-dd HH:mm:ss");
                String value=MessageDigestUtil.toHexString(str.getBytes("utf-8"));//将值转为16进制
                Cookie c=new Cookie(name,value);
                c.setMaxAge(getCookieMaxAge());//设置cookie保存时间为到第二天零点零分结束
                c.setPath("/");
                cookieList.add(c);
            }catch (Exception e){
            }
        }

        // delete cookies
        for(String key: h.getRemovedKeySet()){
            String name=COOKIE_HEAD+key;
            try {
                Cookie c=new Cookie(name,null);
                c.setMaxAge(0); //删除cookie
                c.setPath("/");
                cookieList.add(c);
            }catch (Exception e){
            }
        }

        return cookieList;
    }
    /**
     * 获取当前时间距离今天的23点59分59秒相差的秒数
     */
    public static int getCookieMaxAge(){
        Date todayLastDate=DateUtil.todayLastDate();
        Long time=todayLastDate.getTime();
        long between=(time-System.currentTimeMillis())/1000;
        return (int)between;
    }
}
