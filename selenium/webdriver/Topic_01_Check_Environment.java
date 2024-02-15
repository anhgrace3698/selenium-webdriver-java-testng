package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_01_Check_Environment {
    WebDriver driver;

    @Test
    public void TC_01_Run_On_Firefox() {
        driver = new FirefoxDriver();
        driver.get("https://sjc.com.vn/giavang/textContent.php");

        String gia_ban = driver.findElement(By.xpath("//td[normalize-space()='Vàng nhẫn SJC 99,99 1 chỉ, 2 chỉ, 5 chỉ']/following-sibling::td[2]/span")).getText();
        System.out.println(gia_ban);
        driver.quit();
    }

//    @Test
//    public void TC_02_Run_On_Chrome() {
//        driver = new ChromeDriver();
//        driver.get("https://sjc.com.vn/giavang/textContent.php");
//        driver.quit();
//    }

}
