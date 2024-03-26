package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic10CustomDropDownTest {
    WebDriver driver;
   // String FirstName = "An", LastName = "Nguyen", Email = getRandomMail(), Password = "123456@aA", day = "1", month = "May", year = "1980";

    //learn basic wait - for loop - ...
    WebDriverWait explicitWait; // wait tươờng minh: co trang thai cu the cho elemtn
    // visible/ invisible/ presence/number of element/ clickable/..: co trong html nhung ko hien thi


    @BeforeClass
    public void beforeClass(){
        driver =new FirefoxDriver();

        //khoi tao wait moi: truyen driver va thoi gian doi vao
        // Day la wait  cho elemetn thoa man 1 dieu kien nao do ( // visible/ invisible/ presence/number of element/ clickable/..)
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));

        // wait ngam dinh: ko ro rang cho 1 trang thai cu the nao cua element,
        //ma ngam dinh cho viec doi element. con elemtn hien thi hyay ko thi ko quan tam
        // Day la wwait de tim 1 element nao do
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

    }
    @Test
    public void TC01() {
        // 1. click drop down
        // 2.1. display all item
        // 2.2. display a part of items. when you scroll mouse, it is loading more
        // 3.1. if the item need is display -> select it
        // 3.2. if the needed item is not display -> scroll until see it.
        // 4. verify the value of items = needed value -> checked

        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        driver.findElement(By.cssSelector("span#number-button")).click();

        //Wait until all items are loaded
        // presence = display in html, dont care UI
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));

        // Thao tac voi items can chon vs item = 8
        // Thi se duyet qua all tung item -> so sanh voi item = 8 (so =text) > sai bo qua dung click -> dung for hoac loop tuong tu
        // can tim ra all item de thao tac

        // tao ra list luu lai all element
        List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));

        //Dung for each de duyet qua tung item: 1 bien a (type = webelement duyet qua allItems)
        for (WebElement a: allItems) {
            // voi TH sau khi click item can thi cac item khac mat -> ham get text se fail
            String textItem = a.getText();
            System.out.println("text = "+textItem);
            // so sanh - neu la string thi dung ham equal; neu la kieu nguyen thuy vd char, float, number thi co the dung ==
            // string dung == ko dc
            if (textItem.equals("8")){
                a.click();
                break; // = 8 la click luon. tu 9-19 thi thoat vong lap
                // neu ko break ma van in thi tu 9-19 se ko print ra text, vi sau khi click -> drop down dong roi.
                // man ban chat ham get text la lay ra vissible text -> ko get dc nua
            }
        }
    }


    @Test
    public void TC02() {

    }

    @Test
    public void TC03() {

    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }

//    public String getRandomMail() {
//        Random rand_number =new Random();
//        String email_address;
//        email_address = "automation" + rand_number.nextInt(99999)+"@gmail.net";
//        return email_address;
//    }

//    public void sleepInSeconds(long timeInSeconds){
//        try {
//            Thread.sleep(timeInSeconds * 1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
