package com.project.shop.utils;

import com.project.shop.pojo.role.Admin;
import com.project.shop.pojo.role.User;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

public class JwtUtils {
    //token有效期
    private static final long time=1000*60*60*24;
    //签名,通过此值进行加密和解密
    private static final JwtBuilder jwtBuilder= Jwts.builder();
    public static final String adminSignature= "admin";

    public static final String userSignature= "user";

    public static String createAdminToken(Admin admin){
        String jwtToken=jwtBuilder
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                .claim("username",admin.getUsername())
                .claim("avatar",admin.getAvatar())
                .claim("name",admin.getName())
                .claim("role","admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256,adminSignature)
                .compact();
        return jwtToken;
    }
    public static Admin checkAdminToken(String token){
        if (token==null){
            return null;
        }
        JwtParser jwtParser = Jwts.parser();
        Admin admin;
        try {
            Jws<Claims> claimsJws = jwtParser.setSigningKey(adminSignature).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            admin=new Admin();
            admin.setUsername((String) claims.get("username"));
            admin.setAvatar((String) claims.get("avatar"));
            admin.setName((String) claims.get("name"));
        } catch (Exception e){
            return null;
        }
        return admin;
    }

    public static String createUserToken(User user){
        String jwtToken=jwtBuilder
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                .claim("id",user.getId())
                .claim("username",user.getUsername())
                .claim("email",user.getEmail())
                .claim("level",user.getLevel())
                .claim("name",user.getName())
                .claim("avatar",user.getAvatar())
                .claim("role","user")
                .setSubject("user-test")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256,userSignature)
                .compact();
        return jwtToken;
    }

    public static User checkUserToken(String token){
        if (token==null){
            return null;
        }
        JwtParser jwtParser = Jwts.parser();
        User user;
        try {
            Jws<Claims> claimsJws = jwtParser.setSigningKey(userSignature).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            user = new User();
            user.setId((Integer) claims.get("id"));
            user.setUsername((String) claims.get("username"));
            user.setEmail((String) claims.get("email"));
            user.setLevel((Integer) claims.get("level")) ;
            user.setName((String) claims.get("name"));
            user.setAvatar((String) claims.get("avatar"));
        } catch (Exception e){
            return null;
        }
        return user;
    }

    public static Boolean checkToken(String token, String signature){
        if (token==null){
            return false;
        }
        JwtParser jwtParser = Jwts.parser();
        //只需要判断token是否解析成功,如果能成功解析,则就是合法的token
        try {
            Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(token);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
