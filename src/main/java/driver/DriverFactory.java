package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // Private constructor to prevent instantiation
    private DriverFactory() {}

    // Method to get the WebDriver instance
    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            synchronized (DriverFactory.class) {
                if (driverThreadLocal.get() == null) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("start-maximized");
                    driverThreadLocal.set(new ChromeDriver(options));
                }
            }
        }
        return driverThreadLocal.get();
    }

    // Method to quit the WebDriver instance
    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}