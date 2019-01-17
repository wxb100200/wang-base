package com.base.wang.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.time.DateUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

/**
 * JSON Web Token (JWT)是一种基于 token 的认证方案
 */
public class JwtUtil {
    public static String secretKey="ZC+BrFRngnZ8MBDJ9yjOpmlC1wEh+SebqbNTZ5IRUUOifGk" +
            "Ii81zas/+i1lFPPtjqVG/38UYnRiQZ9fFfcAsEw==";

    public static String generateSecretKey(){
        return new BASE64Encoder().encode(UUID.randomUUID().toString().getBytes());
    }

    /**
     * 生成令牌
     */
    public static String generateToken(String subject,Date date){
        return JwtUtil.generateToken(subject,date,secretKey);
    }
    public static String generateToken(String subject,Date date,String secretKey){
        Key key=decodeKey(secretKey);
        String token=Jwts.builder().setExpiration(date).setSubject(subject).signWith(SignatureAlgorithm.HS256, key).compact();
        String base64Token=new BASE64Encoder().encode(token.getBytes());
        base64Token=base64Token.replace('=','*');
        base64Token=base64Token.replace("\n","");
        base64Token=base64Token.replace("\r","");
        return base64Token;
    }
    private static Key decodeKey(String secretKey){
        Key key=null;
        try {
            byte[] keyBytes=new BASE64Decoder().decodeBuffer(secretKey);
            key=new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
        }catch (Exception e){

        }
        return  key;
    }

    /**
     * 解析令牌
     */
    public static String verify(String token,String secretKey)throws Exception{
        token=token.replace('*','=');
        Key key=decodeKey(secretKey);
        String decodeToken=new String(new BASE64Decoder().decodeBuffer(token));
        return Jwts.parser().setSigningKey(key).parseClaimsJws(decodeToken).getBody().getSubject();
    }

    public static void main(String[] args) throws Exception {
        String subject="100200123456";
        Date date=new Date();
//        date= DateUtils.addMonths(date,10);
        date= DateUtils.addSeconds(date,5);
        String token=JwtUtil.generateToken(subject,date);
        System.out.println("--->>>>token:"+token);
        System.out.println("---->>>>>解析令牌后的数据："+JwtUtil.verify(token,secretKey));
        String tokenOld="ZXlKaGJHY2lPaUpJVXpJMU5pSjkuZXlKbGVIQWlPakUxTXpRM05UVTROVEVzSW5OMVlpSTZJakV3TURJd01ERXlNelExTmlKOS5JejRYS1QxaEdOTGZfZzJQZ2l0SmlSSVpURlRmQktaN1ZYWXpVWTJTVjBB";

        System.out.println(JwtUtil.verify(tokenOld,secretKey));
    }
}
