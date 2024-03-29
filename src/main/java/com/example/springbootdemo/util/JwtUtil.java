package com.example.springbootdemo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @Author gaosen
 * @Date 2023/3/16 16:22
 * @Version 1.0
 * @Description JWT工具类
 */
public class JwtUtil {

    // 有效期为一小时
    public static final Long JWT_TTL = 60 * 60 * 1000L;

    // 设置密钥明文
    public static final String JWT_KEY = "jbf";

    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-","");
        return token;
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（Json格式）
     * @return
     */
    public static String createJWT(String subject){
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());//设置过期时间
        return builder.compact();
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（Json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis){
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());//设置过期时间
        return builder.compact();
    }

    /**
     * 创建token
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis){
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttttlMillis, String uuid){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        if(ttttlMillis == null){
            ttttlMillis = JwtUtil.JWT_TTL;
        }

        long expMillis = nowMillis + ttttlMillis;
        Date expDate = new Date(expMillis);

        return Jwts.builder()
                .setId(uuid) //唯一的ID
                .setSubject(subject) // 主题  可以是JSON数据
                .setIssuer("jbf") // 签发者
                .setIssuedAt(now) // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);

    }

    /**
     * 生成加密后的密钥 secretKey
     * @return
     */
    public static SecretKey generalKey(){
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception{
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(createJWT("aaa", 3600000L));


        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0Y2RjZTczZTYwODk0OTZkOWMzYzgzMjk3MTNkZWMxNiIsInN1YiI6ImFhYSIsImlzcyI6ImpiZiIsImlhdCI6MTY3OTAzODMzMCwiZXhwIjoxNjc5MDM4MzMxfQ.1-GavrVhU5Og7PnWvuOnTHdA8A9B5My9eDRSozfJ0T0";
        Claims claims = parseJWT(token);
        System.out.println(claims);
    }
}
