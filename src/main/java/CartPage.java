import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
/**
 * Page Object encapsulating the cart page functionality.
 */
public class CartPage {

    private static WebDriver driver;
    private static WebDriverWait wait;

    /**
     * Constructs the Cart Page with the given driver and explicit wait.
     *
     * @param driver the WebDriver used for this page
     * @param wait   the WebDriverWait used for this page
     */
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
        return value;
    }

    public void validateCart(String productName, int productQuantity, double productPrice) {
        WebElement productNameInCart = driver.findElement(By.xpath("//td[@class='cart-item__details']/a"));
        wait.until(ExpectedConditions.visibilityOf(productNameInCart));
        WebElement productQuantityInCart = driver.findElement(By.cssSelector(".quantity__input"));
        WebElement productPriceInCartElement = driver.findElement(By.xpath("//tbody/tr/td[@class='cart-item__totals right small-hide']//span"));
//        double productPriceInCart = Double.parseDouble(productPriceInCartElement.getText().trim()
        Assert.assertTrue(productName.equalsIgnoreCase(productNameInCart.getText().trim()), productName + " is not added to cart");
        Assert.assertEquals(productQuantity, Integer.parseInt(productQuantityInCart.getAttribute("value")));
        Assert.assertTrue(productPriceInCartElement.getText().trim().contains(Double.toString(productPrice * productQuantity)));
    }

    public void emptyCart() {
        List<WebElement> deleteProducts = driver.findElements(By.tagName("cart-remove-button"));
        for (WebElement element : deleteProducts) {
            element.click();
        }
        WebElement cartMessage = driver.findElement(By.xpath("//h1[contains(text(),'Your cart is empty')]"));
        wait.until(ExpectedConditions.visibilityOf(cartMessage));
        Assert.assertTrue(cartMessage.isDisplayed(),"Cart is not empty after removing products.");

    }

    public void removePoductFromCart(String productname) {
        List<WebElement> deleteProducts = driver.findElements(By.tagName("cart-remove-button"));
        List<WebElement> productNameInCart = driver.findElements(By.xpath("//td[@class='cart-item__details']/a"));
        int productAt = 0;
        for (WebElement element : productNameInCart) {
            if (element.getText().trim().contains(productname)) {
                deleteProducts.get(productAt).click();
                break;
            }
            productAt++;
        }
    }
}
