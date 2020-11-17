package com.hogwarts.po;

import com.hogwarts.po.contact.Member;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ContactPage extends BasePage{

    //选中的通讯录页面
    private By parterInfo=By.cssSelector(".js_party_info");

    private static String DEPARTMENT = "硬糖101";

    public ContactPage() {
    }

    public ContactPage(WebDriver driver) {
        //保存driver到自己的实例
        super(driver);
    }

    /**
     * 添加部门
     * @param department
     * @return
     */
    public ContactPage addDepartment(String department){
        click(By.linkText("添加"));
        click(By.linkText("添加部门"));
        sendKeys(By.name("name"),department);
        click(By.linkText("选择所属部门"));
        driver.findElements(By.linkText("火星测试部")).get(1).click();
        click(By.linkText("确定"));
        return this;
    }

    /**
     * 查找部门
     * @param department
     * @return
     */
    public String  searchDepartment(String department) {

        sendKeys(By.id("memberSearchInput"), department);
        String content = getText(parterInfo);
        return content;
    }

    /**
     * 添加成员
     * @param members
     */
    public void addMember(List<Member> members) throws Exception {

        for (Member member:members) {
            Thread.sleep(3000);
            click(By.linkText("添加成员"));
            //填写信息保存
            sendKeys(By.name("username"),member.getName());
            sendKeys(By.name("acctid"),member.getAccount());
            sendKeys(By.name("mobile"),member.getPhone());
            click(By.linkText("保存"));

        }
    }
}
