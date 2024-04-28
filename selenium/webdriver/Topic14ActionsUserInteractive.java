package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Topic14ActionsUserInteractive {
    WebDriver driver;
    Actions action; // them action
    WebDriverWait explicitWait;// wait tươờng minh: co trang thai cu the cho elemtn
    // visible/ invisible/ presence/number of element/ clickable/..: co trong html nhung ko hien thi

    // Khai bao bien
    String fullName;

    //Khai bao ham get full name

    // gan ham set full name
    public void setFullName(String newValue){ // neu bien global va local cung ten co the dung this.
        fullName = newValue;
    }
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        action = new Actions(driver); // muon dung phai khoi tao len - tham so cua no la driver nen phai gan driver vao - kogan se bi loi null pointer

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        setFullName("Automation");

        // selinum 4 bo sung 1 so ham
    }
    @Test
    public void TC01_hoverTooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement textbox = driver.findElement(By.cssSelector("input#age"));

        action.moveToElement(textbox).perform(); // truyen vao tu kieu du lieu action, ket thuc sau cung phai co pform
        // dung f8 va chuyen sang tab bat element - hoac dung set time out trong JS xem lai bai hoc xpath css

        Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).isDisplayed());
    }

    @Test
    public void TC02_hoverMenuLogin() {
        driver.get("https://tainghe.com.vn/");
        WebElement kidMenu = driver.findElement(By.xpath("//a[text()='MÁY NGHE NHẠC']"));
        action.moveToElement(kidMenu).perform();
        sleepInSeconds(3);

        driver.findElement(By.xpath("//a[@class ='sub2' and text()='Máy Nghe Nhạc Thể Thao']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.getCurrentUrl(),"https://tainghe.com.vn/may-nghe-nhac-the-thao.html");


        // co the dung ham click cua action = hover roi moi clcik, con click cua element la click truc tipe luon
        // luu y ham get ttext -> get text tren UI
        // con ham text trong xpath latext trong html co the khac nhau -> dung khi chay is diasplay
    }

    @Test
    public void TC03_ClickAndHoldMulti() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        //list cac so chua chon
        List<WebElement> listNumber = driver.findElements(By.cssSelector("li.ui-state-default")); // trong day co 20 element

        //  chon theo block hang ngang hoac doc
        // chon 1-8

        action.clickAndHold(listNumber.get(0)) // click len so 1 va giu chuotpa
                .pause(2000)
                .moveToElement(listNumber.get(7)) // di chuot trai den so 15
                .release() // nha chuot trai
                .perform(); // thuc hien all cac buoc tren

        // list cac so da chon thanh cong
        List<WebElement> selectedNumber = driver.findElements(By.cssSelector("li.ui-selected")); // trong day co 20 element

        List<String> selectedText = new ArrayList<String>(); // lay text cac phan tu chon thanh cong
        for (WebElement element: selectedNumber ){
            selectedText.add(element.getText());
        }


    }

    @Test
    public void TC04_clickAndHold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> listNumber = driver.findElements(By.cssSelector("li.ui-state-default")); // trong day co 20 element

        //thao tac voi keyboard
        action.keyDown(Keys.CONTROL).perform(); // click giu control

        // chon 10-11-14-15
        action.click(listNumber.get(9))
                .click(listNumber.get(10))
                .click(listNumber.get(13))
                .click(listNumber.get(14))
                .keyUp(Keys.CONTROL) // nha control ra
                .perform();
        // veriyf chon thanh cong
        List<WebElement> selectedNumber = driver.findElements(By.cssSelector("li.ui-selected")); // trong day co 20 element
        List<String> selectedText = new ArrayList<String>(); // lay text cac phan tu chon thanh cong
        for (WebElement element: selectedNumber ){
            selectedText.add(element.getText());
        }
        System.out.println(selectedText);


    }
    @Test
    public void TC05_CDP() {

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
