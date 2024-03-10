package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class WebElementsExerciseTest {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        // chi ap dung cho element, doi 30s neu tim thay thi di luon. k ap dung cho trang browser
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //Browser
    @Test
    public void TC01_verifyDisable() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        // is displayed

        if (driver.findElement(By.cssSelector("input#mail")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#mail")).sendKeys("Automation Testing");
            System.out.println("email is displayed");
        } else {
            System.out.println("email is not displayed");
        }
        ;

        if (driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()) {
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
            System.out.println("edu is displayed");
        } else {
            System.out.println("edu is not displayed");
        }
        ;

        if (driver.findElement(By.cssSelector("input#under_18")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("input#under_18 is displayed");
        } else {
            System.out.println("input#under_18 is not displayed");
        }
        ;

        // is not displayed
        if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
            System.out.println("user 5 text is displayed");
        } else {
            System.out.println("user 5 text is not displayed");
        }
        ;
        Assert.assertFalse(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed(), "user 5 is displayed");
    }


    @Test
    public void TC02_verifyEnable() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if (driver.findElement(By.cssSelector("input#mail")).isEnabled()) {
            System.out.println("email is enabled");
        } else {
            System.out.println("email is not enabled");
        }
        ;
        //enabled
        driver.findElement(By.cssSelector("select#job1"));
        driver.findElement(By.cssSelector("select#job2"));
        driver.findElement(By.cssSelector("input#development"));
        driver.findElement(By.cssSelector("input#slider-1"));

        //disabled
        if (driver.findElement(By.cssSelector("input#disable_password")).isEnabled()) {
            System.out.println("pass is enabled");
        } else {
            System.out.println("pass is not enabled");
        }
        ;
        driver.findElement(By.cssSelector("input#radio-disabled"));
        driver.findElement(By.cssSelector("textarea#bio"));
        driver.findElement(By.cssSelector("select#job3"));
        driver.findElement(By.cssSelector("input#check-disbaled"));
        driver.findElement(By.cssSelector("input#slider-2"));

    }

    @Test
    public void TC03_isSelected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //select under 18 and java
        driver.findElement(By.cssSelector("input#under_18")).click();
        driver.findElement(By.cssSelector("input#java")).click();

        if (driver.findElement(By.cssSelector("input#under_18")).isSelected()) {
            System.out.println("Under 18  radio is selected");
        } else {
            System.out.println("Under 18  radio is not selected");
        }
        ;

        if (driver.findElement(By.cssSelector("input#java")).isSelected()) {
            System.out.println("Java checkbox is selected");
        } else {
            System.out.println("Java checkbox is not selected");
        }
        ;
        // unselect java
        driver.findElement(By.cssSelector("input#java")).click();
        if (driver.findElement(By.cssSelector("input#java")).isSelected()) {
            System.out.println("Java checkbox is selected");
        } else {
            System.out.println("Java checkbox is not selected");
        }
        ;
    }

    @Test
    public void TC04_validatePassword() {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.cssSelector("input#email")).sendKeys("ngocduy@gmail.com");
        driver.findElement(By.cssSelector("input#new_password")).clear();
        sleepInSeconds(2);
        /*phim tat theo he eclipse → cltr + shft + f de fromat code
        alt + mui ten len xuong de di chuyen dong code*/

        //Case 1: only lowercase...
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("hiii");

        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='lowercase-char completed']")).isDisplayed());

        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());


        //Case 2. max 8 characters
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("123456789");

        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());

        //Case 7: both 8 conditions
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Automation@123");

        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='lowercase-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='uppercase-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='number-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='special-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSeconds(long timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
