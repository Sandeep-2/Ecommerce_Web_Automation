import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @FindBy(css = ".price-item.price-item--regular")
    private WebElement productValue;

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public double productPrice() {
        String value = productValue.getText().trim();
        int start = value.indexOf(' ') + 1;
        return Double.parseDouble(value.substring(start));
    }

    public String productQuantityAddedToCart(WebElement product) {
        String value = product.getText().trim();
        return value.substring(14, value.length()-1);
    }

    public String productName() {
        return driver.findElement(By.tagName("h1")).getText().trim();
    }
}
