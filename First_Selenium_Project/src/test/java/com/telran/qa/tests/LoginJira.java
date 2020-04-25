package com.telran.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginJira {

  WebDriver wd;


  @BeforeClass
  public void setUp() {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wd.get("http://jira.tel-ran.net/secure/Dashboard.jspa");

  }

  @Test
  public void loginToJira() {
    type(By.id("login-form-username"), "egenyshqa");
    type(By.id("login-form-password"), "111111");
    click(By.id("login"));

    Assert.assertTrue(isElementPresent(By.id("usernameerror")));

    String errorMessage = wd.findElement(By.id("usernameerror")).getText();
    Assert.assertEquals(errorMessage, "Sorry, your username and password are incorrect - please try again.");

  }

  public void type(By locator, String text) {
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  public void click(By user) {
    wd.findElement(user).click();
  }

  public boolean isElementPresent(By locator) {
    return wd.findElements(locator).size() > 0;
  }

  @AfterClass
  public void tearDown() {
    wd.quit();

  }
}
