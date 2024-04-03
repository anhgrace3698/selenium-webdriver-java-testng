package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic_01_Check_Environment {
    public static void main(String[] args) {
        Random rand_number =new Random();
        String email_address;
        email_address = "automation" + rand_number.nextInt(99999)+"gmail.net";



        System.out.println("automation" + rand_number.nextInt(99999)+"gmail.net");
        System.out.println("automation" + rand_number.nextInt(99999)+"gmail.net");
        System.out.println("automation" + rand_number.nextInt(99999)+"gmail.net");
        System.out.println("automation" + rand_number.nextInt(99999)+"gmail.net");


    }
}


//public class Topic_01_Check_Environment {
//    WebDriver driver;
//    @Test
//    public void TC_01_Run_On_Firefox() {
//        driver = new FirefoxDriver();
//        driver.get("https://sjc.com.vn/giavang/textContent.php");
//
//        String gia_ban = driver.findElement(By.xpath("//td[normalize-space()='Vàng nhẫn SJC 99,99 1 chỉ, 2 chỉ, 5 chỉ']/following-sibling::td[2]/span")).getText();
//        System.out.println(gia_ban);
//        driver.quit();
//    }
//}