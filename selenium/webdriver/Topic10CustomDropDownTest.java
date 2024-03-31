package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
    public void beforeClass() {
        driver = new FirefoxDriver();

        //khoi tao wait moi: truyen driver va thoi gian doi vao
        // Day la wait  cho elemetn thoa man 1 dieu kien nao do ( // visible/ invisible/ presence/number of element/ clickable/..)
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // wait ngam dinh: ko ro rang cho 1 trang thai cu the nao cua element,
        //ma ngam dinh cho viec doi element. con elemtn hien thi hyay ko thi ko quan tam
        // Day la wwait de tim 1 element nao do
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

    }

    @Test
    public void TC01_longcode() {
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
        for (WebElement a : allItems) {
            // voi TH sau khi click item can thi cac item khac mat -> ham get text se fail
            String textItem = a.getText();
            System.out.println("text = " + textItem);
            // so sanh - neu la string thi dung ham equal; neu la kieu nguyen thuy vd char, float, number thi co the dung ==
            // string dung == ko dc
            if (textItem.equals("8")) {
                a.click();
                break; // = 8 la click luon. tu 9-19 thi thoat vong lap
                // neu ko break ma van in thi tu 9-19 se ko print ra text, vi sau khi click -> drop down dong roi.
                // man ban chat ham get text la lay ra vissible text -> ko get dc nua
            }
        }
    }


    @Test
    public void TC02_shortCodeUsingFunction_jQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        // tao ham de linh dong chonj dc cac item khac nhau
        // giup chon dc nhieu dropdown khac nhau luo
        clickDropDown("span#number-button", "ul#number-menu div", "8");
        clickDropDown("span#speed-button", "ul#speed-menu div", "Fast");

        // verify click exact value: kiem tra xem sau khi chon thi element nao dc bat or thay doi, thi verify

        // text can lay o trong span nen phai dung get text() ko dung dc get atribute value
        Assert.assertEquals(driver.findElement(By.cssSelector("#speed-button>span.ui-selectmenu-text")).getText(),"Fast");
        Assert.assertEquals(driver.findElement(By.cssSelector("#number-button>span.ui-selectmenu-text")).getText(),"8");
    }

    @Test
    public void TC03_ReactJS() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        clickDropdown2("//div[@class='ui fluid selection dropdown']","div.item>span","Matt");

        //veriy sau khi click
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(),"Matt");
    }

    @Test
    public void TC04_VueJS() {

        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        clickDropDown("li.dropdown-toggle","ul.dropdown-menu a","Second Option");
        //veriy sau khi click
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Second Option");
    }
    @Test
    public void TC05_Editable() {

        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        clickDropdown3("input.search","span.text","Belgium");
        //veriy sau khi click
        String verifyItem = driver.findElement(By.cssSelector("div.divider")).getText();
        System.out.println(verifyItem);
        Assert.assertEquals(verifyItem,"Belgium");
    }

    @Test
    public void TC06_NopEcomerce() {

        driver.get("https://demo.nopcommerce.com/register");
        clickDropDown("select[name='DateOfBirthDay']","select[name='DateOfBirthDay']>option","25");
        //veriy sau khi click
        // do sau khi chon ko co element thay doi gia tri nhu cac bai tap tren -> bat bang cach khac
        // co the dung isSelected cho the option- co select de kiem tra co dc select hay chua
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']>option[value='25']")).isSelected());
    }

    // khi lam cac du an khac, ngon ngu khac thi can linh dong de tu build ham
    @AfterClass
    public void afterClass() {
        driver.quit();
    }


    public void clickDropDown(String parentCss, String allItemCss, String expectedValue) {
        driver.findElement(By.cssSelector(parentCss)).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
        List<WebElement> allItems = driver.findElements(By.cssSelector(allItemCss)); // co the bo doan nay vi presenceOfAllElementsLocatedBy cung da tra ra lisst roi
        for (WebElement a : allItems) {
            String textItem = a.getText();
            System.out.println("text = " + textItem);
            if (textItem.equals(expectedValue)) {
                a.click();
                break;
            }
        }
    }

    public void clickDropdown2(String parent, String child, String expect){
        driver.findElement(By.xpath(parent)).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(child)));
        List<WebElement> childItems = driver.findElements(By.cssSelector(child));
        for (WebElement a: childItems) {
            String textItem = a.getText();
            System.out.println("text = " + textItem);
            if(textItem.equals(expect)){
                a.click();
                break;
            }
        }
    }

    public void clickDropdown3(String parent, String child, String expect){
        driver.findElement(By.cssSelector(parent)).clear();
        driver.findElement(By.cssSelector(parent)).sendKeys(expect);

        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(child)));
        List<WebElement> childItems = driver.findElements(By.cssSelector(child));
        for (WebElement a: childItems) {
            String textItem = a.getText();
            System.out.println("text = " + textItem);
            if(textItem.equals(expect)){
                a.click();
                break;
            }
        }
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
