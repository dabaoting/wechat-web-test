package com.hogwarts.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.hogwarts.v1.po.MainPage;
import com.hogwarts.v2.po.ContactPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * 通讯录测试
 */

public class ContactTest {

    private static MainPage homePage ;

    @BeforeAll
    static void init() throws Exception{
        //todo  首页数据驱动实现
        homePage = new MainPage();

    }

    @ParameterizedTest
    @MethodSource("contact")
    public void contact(ContactPage contactPage){

        contactPage.run();

    }

    static Stream<ContactPage> contact() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ContactPage contactCase = mapper.readValue(
                new File("src/test/resources/contact.yaml"),
                ContactPage.class);
        return Stream.of(contactCase);
    }


}
