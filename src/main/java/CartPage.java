import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class CartPage {

    private static WebDriver driver;
    private static WebDriverWait wait;

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        driver.navigate().to("https://web-playground.ultralesson.com/cart");
    }

    public int cartValue() {
        WebElement cartMessage = driver.findElement(By.xpath("//h1[contains(text(),'Your cart is empty')]"));
        if (cartMessage.isDisplayed()) {
            driver.navigate().back();
            return 0;
        }
        WebElement cartValue = driver.findElement(By.xpath("//div[@class='cart-count-bubble']/span[@aria-hidden='true']"));
        int value = Integer.parseInt(cartValue.getText());
        driver.navigate().back();
        return value;
    }

}
