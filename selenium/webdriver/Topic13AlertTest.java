package webdriver;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Topic13AlertTest {
    WebDriver driver;
    WebDriverWait explicitWait;// wait tươờng minh: co trang thai cu the cho elemtn
    // visible/ invisible/ presence/number of element/ clickable/..: co trong html nhung ko hien thi
    By result = By.cssSelector("p#result");
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    //Cac loai alert
    //1- alert
    //2- form/pop up / dialog
    //3- window
    @Test
    public void TC01_acceptAlert() {
        // ko cho canccel, chi dc eccept. Chay trong tab console:     alert("hello");
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        sleepInSeconds(2);

        // Khi alert bat len can wsitch driver qua alert/window/ frame tuong ung thi moi taho tac duoc
        // 1.switchTo().alert();
        // 2.driver.switchTo().window("");
        // 3.driver.switchTo().frame("");

        Alert checkalert = driver.switchTo().alert(); // khai bao bien alert de switch vao thao tac alert

        // co 4 ham
        // Cancel alert -void dismiss(); -- thuong chi ap dung cho confirm/ promt alert vi no la cancel

        // Accept alert
        //  void accept();

        // Get text trong alert ra
        //  String getText();

        // Nhap text vao alert
        //  void sendKeys(String keysToSend);

        // alert la cua JS -> chi can ktra hien thi trong code html, k can hien thi tren giao dien

        // verify bien trong alert
        Assert.assertEquals(checkalert.getText(),"I am a JS Alert");

        checkalert.accept();  // Khi accept/ cancel alert -> mat luon roi -> ko thao tac tiep dc se bao error
        Assert.assertEquals(driver.findElement(result).getText(),"You clicked an alert successfully");

    }

    @Test
    public void TC02_confirmAlert() {
        // yes/no question -> gui true/false len sever cofirm("hello")
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        // bo cach nay sleepInSeconds(2);

        // Wait cho alert present - Trong thoi gian wait xuat hien thi switch vao - het thoi gian k xuat hien thi moi fail
        Alert checkAlert2 = explicitWait.until(ExpectedConditions.alertIsPresent()); // wait alert trong thoi gian set trc
        Assert.assertEquals(checkAlert2.getText(),"I am a JS Confirm");

        checkAlert2.dismiss();
        Assert.assertEquals(driver.findElement(result).getText(),"You clicked: Cancel");
    }

    @Test
    public void TC03_PromptAlert() {
        // hien ra alert de nhap thong tin -> chon oke/cancel de gui len sever prompt("hello","my name is an");
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        Alert checkAlert2 = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(checkAlert2.getText(),"I am a JS prompt");

        checkAlert2.sendKeys("lan anh lan anh"); // send key
        checkAlert2.accept(); // accept
        Assert.assertEquals(driver.findElement(result).getText(),"You entered: lan anh lan anh");
    }

    @Test
    public void TC04_authenAlert() {
        // thu vien alert ko support case nay - len selenium 4.0 dung Chrome dev tool protocol - chi dung dc voi chrome va edge (chromnium)
        // cach 1 - Truyen thang username/ pass vao url
        //driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        //Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).getText(),"Congratulations! You must have the proper credentials.");

        // cach 2 - chi chay dc tren window - dung thu vien auto it de chay
        driver.get("http://the-internet.herokuapp.com/basic_auth");
    }
    @Test
    public void TC05_customCheckbox() {

    }

    @AfterClass
    public void afterClass() {

        //driver.quit();
    }


//    public String getRandomMail() {
//        Random rand_number =new Random();
//        String email_address;
//        email_address = "automation" + rand_number.nextInt(99999)+"@gmail.net";
//        return email_address;
//    }

    public void sleepInSeconds(long timeInSeconds){
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
