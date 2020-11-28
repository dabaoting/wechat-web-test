package com.hogwarts.v2.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ContactPage extends BasePage{

    public List<HashMap<String,Object>> steps;

    private WebDriver driver;

    private WebElement element;

    public void run(){

        steps.forEach(step -> {

            if(step.keySet().contains("browser")){
                driver = new ChromeDriver();

            }else if(step.keySet().contains("get")){
                driver.get(step.get("get").toString());

            }else if(step.keySet().contains("implicitlyWait")){
                driver.manage().timeouts().implicitlyWait(Integer.valueOf(step.get("implicitlyWait").toString()), TimeUnit.SECONDS);
            }else if (step.keySet().contains("elements")){
                ArrayList<By> element_list = new ArrayList<>();

                ((HashMap<String,String>)step.get("elements")).entrySet().forEach(str ->{
                    if (str.getKey().contains("id")){
                        element_list.add(By.id(str.getValue()));
                    }
                    if (str.getKey().contains("xpath")){
                        element_list.add(By.xpath(str.getValue()));
                    }
                    if (str.getKey().contains("cssSelector")){
                        element_list.add(By.cssSelector(str.getValue()));
                    }
                    element = driver.findElement(element_list.get(0));
                });
            }

            if (step.keySet().contains("click")){
                element.click();
            }
            if (step.keySet().contains("sendkeys")){
                element.sendKeys("search demo");
            }

        });
    }

    public ContactPage() {
    }

    public ContactPage(WebDriver driver) {
        this.driver = driver;
    }
}
