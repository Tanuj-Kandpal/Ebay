package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

public class DriverHelper {

    WebDriver driver;
    String url, browserName;

    public void setup() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/environment.properties");
        properties.load(fileInputStream);
        browserName = properties.getProperty("browser");
        url = properties.getProperty("url");
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            System.out.println("Please provide the correct browserName");
        }
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void findElement(String xpath) {
        driver.findElement(By.xpath(xpath));
    }

    public WebElement findWebElement(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public void sendKeys(String xpath, String text) {
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }

    public void close() {
        driver.quit();
    }

    public void click(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public void waitElementToBeClickable(String xpath, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

    }

    public void waitForPageToLoad(int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public void waitForElementToBeVisible(WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void titleToContain(String title, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.titleContains(title));
        waitForPageToLoad(time);
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void switchToNewWindow() {
        String parentWindow = driver.getWindowHandle();

        Set<String> allWindows = driver.getWindowHandles();

        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        waitForPageToLoad(10);
    }

    public void getText(String xpath) {
        driver.findElement(By.xpath(xpath)).getText();
    }

    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }

    public String printText(String xpath) {
        return driver.findElement(By.xpath(xpath)).getText();
    }
}
