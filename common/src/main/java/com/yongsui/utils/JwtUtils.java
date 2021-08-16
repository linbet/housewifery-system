package com.yongsui.utils;

import com.yongsui.domain.Payload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.UUID;

/**
 * @author tengmingfa
 * 生成token以及校验token相关方法
 */
public class JwtUtils {

    private static final String JWT_PAYLOAD_USER_KEY = "user";

    /**
     * 私钥加密token
     * @param userInfo 载荷中的数据
     * @param privateKey 私钥
     * @param expire 过期时间，单位秒
     * @return JWT
     */
    public static String generateTokenExpireInMinutes(Object userInfo, PrivateKey privateKey,int expire){
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY,JsonUtils.toString(userInfo))
                .setId(createJtI())
                .setExpiration(DateTime.now().plusMinutes(expire).toDate())
                // 设置签名方式 即签名用什么方式加密 SignatureAlgorithm.ES256
                .signWith(privateKey)
                .compact();
    }

    /**
     * 私钥加密token
     * @param userInfo 载荷中的数据
     * @param privateKey 私钥
     * @param expire 过期时间，单位秒
     * @return JWT
     */
    public static String generateTokenExpireInSeconds(Object userInfo, PrivateKey privateKey,int expire){
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY,JsonUtils.toString(userInfo))
                .setId(createJtI())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                // 设置签名方式 即签名用什么方式加密
                .signWith(privateKey, SignatureAlgorithm.ES256)
                .compact();
    }

    /**
     *公钥解析token
     * @param token 用户请求中的token
     * @param publicKey 公钥
     * @return Jws<Claims>
     */
    private static Jws<Claims> parserToken(String token,PublicKey publicKey){
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    private static String createJtI(){
        return new String (Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    /**
     * 获取token中的用户信息
     *
     * @param token 用户请求中的令牌
     * @param publicKey 公钥
     * @param userType 返回的用户类型
     * @param <T>
     * @return 用户信息
     */
    public static <T> Payload<T> getInfoFromToken(String token, PublicKey publicKey,Class<T> userType){
        Jws<Claims> claimsJws = parserToken(token,publicKey);
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        claims.setId(body.getId());
        claims.setUserInfo(JsonUtils.toBean(body.get(JWT_PAYLOAD_USER_KEY).toString(),userType));
        claims.setExpiration(body.getExpiration());
        return claims;
    }

    /**
     * 获取token中的载荷信息
     * @param token 用户请求的令牌
     * @param publicKey 公钥
     * @param <T>
     * @return 用户信息
     */
    public static <T> Payload<T> getInfoFromToken(String token,PublicKey publicKey){
        Jws<Claims> claimsJws = parserToken(token,publicKey);
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        claims.setId(body.getId());
        claims.setExpiration(body.getExpiration());
        return claims;
    }
}
