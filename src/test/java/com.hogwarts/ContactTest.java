package com.hogwarts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import com.hogwarts.po.ContactPage;
import com.hogwarts.po.MainPage;
import com.hogwarts.po.contact.Member;
import net.bytebuddy.implementation.bind.annotation.Argument;
import org.junit.After;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 通讯录测试
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContactTest {

    private static MainPage homePage ;

    private static ContactPage contactPage;

    private static String MEMBERS_YAML = "src\\test\\resources\\members.yaml";


    @BeforeAll
    static void init() throws Exception{
        //登录-》进入首页
        homePage = new MainPage();

        //首页-》进入通讯录
        contactPage = homePage.contactClick();
    }

    /**
     * 创建部门
     */
    @ParameterizedTest
    @ValueSource(strings={"硬糖101"})
    @Order(1)
    void newDepartment(String department) throws Exception{

        contactPage.addDepartment(department);//新建部门

        //断言
        Thread.sleep(3000);
        String result = contactPage.searchDepartment(department);
        assertTrue(result.contains(department));
    }

    /**
     * 添加成员
     * @param members
     * @throws Exception
     */
    @ParameterizedTest
    @MethodSource("membersData")
    @Order(2)
    void addMembers(List<Member> members) throws Exception{

        //添加成员
        contactPage.addMember(members);

    }

    /**
     * 成员信息参数
     * @return
     * @throws Exception
     */
    static Stream<List<Member>> membersData() throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference typeReference = new TypeReference<List<Member>>() {};
        List<Member> members = (List<Member>)mapper.readValue(new File(MEMBERS_YAML), typeReference);
        return Stream.of(members);
    }

}
