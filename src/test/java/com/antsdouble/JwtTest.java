package com.antsdouble;

import com.antsdouble.util.AntsJwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/17
 */
public class JwtTest {

    @Test
    public void test(){

        String buildJWT = AntsJwtUtil.buildJWT("antsdouble");
        System.out.println(buildJWT);
        Boolean aBoolean = AntsJwtUtil.checkJWT(buildJWT);
        System.out.println(aBoolean);
        Jws<Claims> claimsJws = AntsJwtUtil.parseJWT( buildJWT);
        System.out.println(claimsJws);
        buildJWT = AntsJwtUtil.buildJWT("antsdouble",null,600);
        System.out.println(buildJWT);
        aBoolean = AntsJwtUtil.checkJWT(buildJWT);
        System.out.println(aBoolean);
         claimsJws = AntsJwtUtil.parseJWT( buildJWT);
        System.out.println(claimsJws);

    }
}
