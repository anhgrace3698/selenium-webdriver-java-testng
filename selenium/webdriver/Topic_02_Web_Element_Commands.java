package webdriver;

import graphql.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Topic_02_Web_Element_Commands {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() { }

    @Test
    public void TC_01_Element() {
       // HTML element: text, dropdown, textarea, image, link, button
        // tim tuong tac len element do
        driver.findElement(By.id(" "));

        // tim va tuong tac len
        driver.findElement(By.id("")).click();
        driver.findElement(By.id("")).sendKeys("hello");

        // tim va luu no vao  1 bien chua tuong tac. Ap dung khi sd bien nay nhieu lan
        WebElement fullName = driver.findElement(By.id(""));
        fullName.sendKeys("hello from the other sides");
        fullName.click();
    }

    @Test
    public void TC_02_Element() {
        //clear - dung de xoa du lieu trong 1 truong cho phep nhap. Thuong dungf truoc ham sendkey
        //textbox / textarea / dropdown edittable
        driver.findElement(By.id(" ")).clear();

        // ham nhap lieu
        driver.findElement(By.id(" ")).sendKeys("Hello Lan anh");

        //click element
        driver.findElement(By.id(" ")).click();

        //Tim tu node cha vao node con
        driver.findElement(By.id("login-form")).findElement(By.cssSelector("input")); // ko nen vet ntn
        driver.findElements(By.xpath(" /bookstore/book/price[text()] ")); // nen viet ntn


        //Phan biet ham find element và find elements
        //find element: tìm tất ca thỏa maãn dkien va lay ra cai dau tien. neu tim ko dc thi chay het implicit wait time
        // -> anh huong implicit wait
        //find elements: tim nhieu , tra ra nhieu hoac rong.

        List<WebElement> textbox = driver.findElements(By.cssSelector("")); // Java Generic

        //Dung de verify 1 checkbox, radio, dropdown da duoc chon hay chua
        Assert.assertTrue(driver.findElement(By.id(" ")).isSelected());
        Assert.assertFalse(driver.findElement(By.id(" ")).isSelected());


        //verify 1 element co hien thi k - bat ki loai elemee=nt nao
        Assert.assertTrue(driver.findElement(By.id(" ")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.id(" ")).isDisplayed());

        //verify 1 element co thao tac dc k
        Assert.assertTrue(driver.findElement(By.id(" ")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id(" ")).isEnabled());

        //HTLM Element - lay ra gia tri cua attribute
        //<input id="search" autocapitalize="none" autocomplete="off" autocorrect="off" name="search_query"
        driver.findElement(By.id("")).getAttribute("id");
        driver.findElement((By.id(" "))).getAttribute("name");

        // Lien quan tab accesebility va property o f12
        driver.findElement(By.id("")).getAccessibleName();
        driver.findElement(By.id("")).getDomAttribute("baseURI");
        driver.findElement(By.id(" ")).getDomProperty("outerText");

        // tab style - font, size, text, ...
        driver.findElement(By.id(" ")).getCssValue("font-family");

        //Lay ra vi tri element so voi do phan giai man hinh
        Point textboxLocation;
        textboxLocation = driver.findElement(By.id(" ")).getLocation();

        // lay ra kich thuoc size cua element
        driver.findElement(By.id("")).getSize();

        // vua tra ra location vua tra ra size
        Rectangle rectanglesize  = driver.findElement(By.id(" ")).getRect();
        rectanglesize.getPoint();         // location
        rectanglesize.getDimension(); //size
        rectanglesize.getHeight() ; // chieu rong
        rectanglesize.getWidth(); // chieu cao

        // lay ra shadow root - hoc trong bai java excuter
        driver.findElement(By.id("")).getShadowRoot();

        // tu id, class, name, css, xpath co the suy ra duoc tag name html cua no
        // dung trong TH biet locater nhung k biet ten the, hoac dau ra cua cai nay la dau vao cai kia
        driver.findElement(By.cssSelector("#email")).getTagName();
        driver.findElement(By.id("email")).getTagName();
        driver.findElement(By.xpath("//*[@id='email']")).getTagName(); // dung dau * vi k biet ten the

        //element A -> dau ra la tag name element A
        String elementA = driver.findElement(By.cssSelector("//*[text()='Samsung Galaxy']")).getTagName();  //input

        // dau vao cua element B nhan tagname cua element A lam tham so
        String elementB = driver.findElement(By.xpath("//" + elementA + "[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/button")).getTagName();

        // lay ra text
        driver.findElement(By.id("")).getText();

        //chup hinh giai doan bi fail va luu lai duoi cac dang
        driver.findElement(By.id("")).getScreenshotAs(OutputType.FILE);
        driver.findElement(By.id("")).getScreenshotAs(OutputType.BASE64);
        driver.findElement(By.id("")).getScreenshotAs(OutputType.BYTES);

        // the form hoac nam trong the form - hanh vi giong nhu phim enter tren ban phim
        driver.findElement(By.id("")).submit();






    }

    @Test
    public void TC_03_Run_On_Firefox() {
        driver = new FirefoxDriver();
       // driver.get("http://live.techpanda.org/index.php/customer/account/login/");
        //System.out.println(driver.findElement(By.xpath("//*[@id='email']")).getTagName());

        driver.get("http://live.techpanda.org/index.php/mobile.html");

        //element A -> dau ra la tag name element A
        String elementA = driver.findElement(By.xpath("//*[text()='Samsung Galaxy']")).getTagName();  //input

        // dau vao cua element B nhan tagname cua element A lam tham so
        String elementB = driver.findElement(By.xpath("//" + elementA + "[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/button")).getTagName();
        System.out.println(elementB);

        driver.quit();
    }

    @AfterClass
    public void afterClass(){ driver.quit();}

}
