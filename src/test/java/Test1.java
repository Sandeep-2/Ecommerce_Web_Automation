import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Test1 {
    @Test
    public void navigateToProduct() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        String searchTerm = "12 Ti Xelium Skis";
        String searchTerm = "88 BSLT Skis";
        driver.get("https://web-playground.ultralesson.com/");
        WebElement searchIcon = driver.findElement(By.cssSelector(".modal__toggle-open"));
        wait.until(ExpectedConditions.elementToBeClickable(searchIcon));
        searchIcon.click();
        WebElement searchBar = driver.findElement(By.cssSelector("#Search-In-Modal"));
        searchBar.sendKeys(searchTerm + Keys.ENTER);
        WebElement searchedProduct = driver.findElement(By.xpath("//a[contains(text(),'"+searchTerm+"')]"));
        searchedProduct.click();
        WebElement searchedProductTittle = driver.findElement(By.cssSelector(".product__title"));
        wait.until(ExpectedConditions.visibilityOf(searchedProductTittle));
        Assert.assertEquals(searchedProductTittle.getText(),searchTerm);
        WebElement addToCart = driver.findElement(By.xpath("//button[@type='submit' and @name='add']"));
        if (!addToCart.isEnabled()) {
            System.out.println("This product is sold out");
            driver.quit();
            return; // Exit the test if the product is sold out.
        }
        Assert.assertTrue(addToCart.isEnabled(),"This product is sold out");
        addToCart.click();
    }

}
