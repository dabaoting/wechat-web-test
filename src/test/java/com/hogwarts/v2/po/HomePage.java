package com.hogwarts.v2.po;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.Cookie;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage{

    public HomePage() throws Exception{
        loginAuto();
    }

    /**
     * 扫码登录,保存cookie
     */
    void login() throws Exception{

        //第一次扫码登录，保存cookie
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        Thread.sleep(15000);

        //将cookie保存到yaml文件
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        yamlMapper.writeValue(new File("cookies.yaml"),cookies);
    }

    /**
     * cookie自动登录
     */
    void loginAuto() throws Exception{

        File cookieFile = new File("cookies.yaml");
        //文件不存在
        if(!cookieFile.exists()){
            //扫码登录
            login();
        }else {
            //打开登陆页
            driver.get("https://work.weixin.qq.com/wework_admin/frame");
            Thread.sleep(20);

            //yaml转cookie
            ObjectMapper cookieMapper = new ObjectMapper(new YAMLFactory());
            TypeReference typeReference = new TypeReference<List<HashMap<String, Object>>>() {};
            List<HashMap<String,Object>> cookiesList = (List<HashMap<String,Object>>)cookieMapper.readValue(cookieFile, typeReference);
            for (HashMap<String,Object> cookie:cookiesList) {
                driver.manage().addCookie(new Cookie(cookie.get("name").toString(),cookie.get("value").toString()));
            }

            //刷新页面
            driver.navigate().refresh();
        }

    }


}
