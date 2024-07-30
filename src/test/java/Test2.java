import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test2 {

    private static WebDriver driver;
    private static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @BeforeTest
    public static void initailizeBrowser(){
        driver = new ChromeDriver(new ChromeOptions().addArguments("start-maximized"));
        driver.get("https://web-playground.ultralesson.com/");
    }

    @Test
    public static void validateCart(){

    }

}
