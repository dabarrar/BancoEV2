package com.bancoplatinum.Selenium;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSeleniumTest {

    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://localhost:8080/BancoPlatinumWeb"; 
    }

    @Test
    public void testLoginCorrecto() {
        driver.get(baseUrl + "/login.jsp");
        driver.findElement(By.name("username")).sendKeys("usuario_valido");
        driver.findElement(By.name("password")).sendKeys("contrasena_valida");
        driver.findElement(By.name("submit")).click();

        
        assertEquals(baseUrl + "/success.jsp", driver.getCurrentUrl());
    }

    @Test
    public void testLoginIncorrecto() {
        driver.get(baseUrl + "/login.jsp");
        driver.findElement(By.name("username")).sendKeys("usuario_invalido");
        driver.findElement(By.name("password")).sendKeys("contrasena_invalida");
        driver.findElement(By.name("submit")).click();

        
        assertEquals(baseUrl + "/login.jsp", driver.getCurrentUrl());
    }

    @Test
    public void testLoginValoresVacios() {
        driver.get(baseUrl + "/login.jsp");
        driver.findElement(By.name("submit")).click();


        assertEquals(baseUrl + "/login.jsp", driver.getCurrentUrl());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
