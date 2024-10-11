package Ebay;

import Ebay.PageObjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLOutput;

public class EbayTest {

    HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void setup() throws IOException {
        homePage = new HomePage();
    }

    @Test
    public void ebayVerifyCartUpdate() {
        homePage.searchItem("book");
        homePage.waitForItemListToDisplay();
        homePage.verifyAddToCart();
        int itemsCount = homePage.getCartItemsCount();
        Assert.assertEquals(1, itemsCount);
        System.out.println("Successfully validated cart items");
    }

    @AfterMethod
    public void closeBrowserSession() {
        homePage.quit();
    }
}
