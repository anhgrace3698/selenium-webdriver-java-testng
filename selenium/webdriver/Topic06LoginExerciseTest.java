package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic06LoginExerciseTest {
    WebDriver driver;
    @BeforeClass
    public void beforeClass(){
        driver =new FirefoxDriver();
        // chi ap dung cho element, doi 30s neu tim thay thi di luon. k ap dung cho trang browser
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
//Browser
    @Test
    public void TC01_loginEmpty() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#pass")).clear();
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-email")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-pass")).getText(),"This is a required field.");
    }
    @Test
    public void TC02_invalidEmail() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#pass")).clear();

        driver.findElement(By.cssSelector("#email")).sendKeys("123434234@12312.123123");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123456");

        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC03_passUnder6() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#pass")).clear();

        driver.findElement(By.cssSelector("#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123");

        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC04_incorrectPass(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#pass")).clear();

        driver.findElement(By.cssSelector("#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123123123");

        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText(),"Invalid login or password.");

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    public void sleepInSeconds(long timeInSeconds){
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
