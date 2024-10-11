package Ebay.PageObjects;

import Helpers.DriverHelper;

import java.io.IOException;

public class HomePage {

    DriverHelper dh = new DriverHelper();
    String searchBar = "//*[@id='gh-ac']",
    firstBook = "(//*[contains(@class, 's-item__info')]//a)[3]",
    searchButton = "//*[@value='Search']",
    addToCartButton = "//span[contains(text(),'Add to cart')]",
    cartItemCount = "//*[@id='gh-cart-n']";


    public HomePage() throws IOException {
        dh.setup();
        dh.waitForPageToLoad(10);
    }

    public void clickSearchBar() {
        dh.findElement("//*[@id='gh-ac']");
    }

    public void inputItemInSearchBar(String details) {
        clickSearchBar();
        dh.sendKeys(searchBar, details);
    }

    public void searchItem(String itemName) {
        inputItemInSearchBar(itemName);
        String itemXpath = "//*[@aria-label='" + itemName + "']";
        dh.waitElementToBeClickable(itemXpath, 10);
        dh.click(searchButton);
    }

    public void waitForItemListToDisplay() {
        dh.titleToContain("Book for sale", 5);
        dh.click(firstBook);
        dh.getText(firstBook);
        dh.switchToNewWindow();

    }

    public void verifyAddToCart() {
        dh.waitForElementToBeVisible(dh.findWebElement(addToCartButton), 5);
        dh.scrollToElement(dh.findWebElement(addToCartButton));
        dh.click(addToCartButton);
    }

    public int getCartItemsCount() {
        dh.waitForPageToLoad(10);
        dh.scrollToTop();
        return Integer.parseInt(dh.printText(cartItemCount));
    }


    public void quit() {
        dh.close();
    }
}
