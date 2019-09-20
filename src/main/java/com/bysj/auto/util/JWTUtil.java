package com.bysj.auto.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bysj.auto.bean.User;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    private static final String SECRET = "com.cdsh.jwt";
    private static final String ISSUER = "user";

    /**
     * 生成token
     *
     * @param claims
     * @return
     */
    public static String createToken(Map<String, String> claims) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTCreator.Builder builder = JWT.create()
                    .withIssuer(ISSUER)
                    //设置过期时间为 36 小时
                    .withExpiresAt(DateUtils.addHours(new Date(), 36));
            claims.forEach(builder::withClaim);
            return builder.sign(algorithm);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("生成token失败");
        }
    }

    /**
     * 生成token
     *
     * @param user
     * @return
     */
    public static String createToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTCreator.Builder builder = JWT.create()
                    .withIssuer(ISSUER)
                    .withClaim("id", user.getUserId().toString())
                    //设置过期时间为 36 小时
                    .withExpiresAt(DateUtils.addHours(new Date(), 36));
            return builder.sign(algorithm);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("生成token失败");
        }
    }

    /**
     * 验证jwt，并返回数据
     */
    public static Map<String, String> verifyToken(String token) {
        Algorithm algorithm;
        Map<String, Claim> map;
        try {
            algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT jwt = verifier.verify(token);
            map = jwt.getClaims();
        } catch (Exception e) {
//            throw new RuntimeException("鉴权失败");
            return null;
        }
        Map<String, String> resultMap = new HashMap<>(map.size());
        map.forEach((k, v) -> resultMap.put(k, v.asString()));
        return resultMap;
    }


    public static void main(String[] args) {
        User user = new User() {{
            setUserId(1);
            setPhone("phone");
            setPassword("password");
            setCreateTime("createTime");
        }};
        String token = createToken(user);
        System.out.println(token);
        Map<String, String> res = verifyToken(token);
        System.out.println(JSON.toJSONString(res));
    }


}
