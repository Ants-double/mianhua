package com.antsdouble;

import com.antsdouble.util.AntsWebUtil;
import org.junit.Test;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/11
 */
public class WebTest {
    @Test
    public void antsWeb(){
        AntsWebUtil.HttpResponse httpResponse = AntsWebUtil.get("https://www.baidu.com").defaultTimeOut().executeAntsWebUtil();

        String httpRet = httpResponse.body();
        System.out.println(httpRet);

        int code = httpResponse.code();
        System.out.println(code);

    }
}
