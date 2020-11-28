package com.hogwarts.v1.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    WebDriver driver;

    public BasePage() {
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    void click(By by){
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(by)).click();
    }

    void sendKeys(By by,String text){
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(by)).sendKeys(text);
    }

    String getText(By by){
        String text = new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(by)).getText();
        return text;
    }
}
