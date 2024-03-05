package webdriver;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.time.Duration;

public class WebElementsExerciseTest {
    WebDriver driver;
    @BeforeClass
    public void beforeClass(){
        driver =new FirefoxDriver();
        // chi ap dung cho element, doi 30s neu tim thay thi di luon. k ap dung cho trang browser
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
//Browser
    @Test
    public void TC01_verifyUrl() {
        driver.get("http://live.techpanda.org/");
        //vao trang wweb > vao link my acoount o footer > verify url cua login page > click create account button > verify url trang dang ki
        ////div[@class='footer']//a[@title='My Account']
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        //Sau khi click xong phai bao web doi de load sang trang moi. Neu ko co the xay ra nguy co chua kip get trang moi -> lay urltrang cu

        sleepInSeconds(3); //goi ham sleep fix 3s doi web
        String urlGet = driver.getCurrentUrl();
        String urlExpect = "http://live.techpanda.org/index.php/customer/account/login/";
        Assert.assertEquals(urlGet, urlExpect);

        sleepInSeconds(3); //goi ham sleep fix 3s doi web
        driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        String urlGet2 = driver.getCurrentUrl();
        String urlExpect2 = "http://live.techpanda.org/index.php/customer/account/create/";
        Assert.assertEquals(urlGet2,urlExpect2);
    }


    @Test
    public void TC02_verifyTitle() {
        driver.get("http://live.techpanda.org/");
         driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        sleepInSeconds(3); //goi ham sleep fix 3s doi web
        String titleGet = driver.getTitle();
        String titleExpect = "Customer Login";
        Assert.assertEquals(titleGet, titleExpect);


        sleepInSeconds(3); //goi ham sleep fix 3s doi web
        driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        String titleGet2 = driver.getTitle();
        String titleExpect2 = "Create New Customer Account";
        Assert.assertEquals(titleGet2,titleExpect2);
    }

    @Test
    public void TC03_navigateFunction() {

        driver.get("http://live.techpanda.org/"); // go to website
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(3);
        driver.findElement(By.xpath("//span[text()='Create an Account']")).click();

        //verify url
        String urlGet2 = driver.getCurrentUrl();
        String urlExpect2 = "http://live.techpanda.org/index.php/customer/account/create/";
        Assert.assertEquals(urlGet2,urlExpect2);

        //get back page Login
        driver.navigate().back();
        sleepInSeconds(3);

        //verify url
        String urlGet = driver.getCurrentUrl();
        String urlExpect = "http://live.techpanda.org/index.php/customer/account/login/";
        Assert.assertEquals(urlGet, urlExpect);

        //forward to page Register
        driver.navigate().forward();
        sleepInSeconds(3);

        // verify title
        String titleGet2 = driver.getTitle();
        String titleExpect2 = "Create New Customer Account";
        Assert.assertEquals(titleGet2,titleExpect2);
    }

    @Test
    public void TC04_getPageSourceCode(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        // get page source, compare text in login page
        System.out.println(driver.getPageSource().contains("Login or Create an Account"));

        // get page source, compare text in account page
        driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        System.out.println(driver.getPageSource().contains("Create an Account"));
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
