package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.TestUtil;

public class GoogleMapsSearchPage {
    private WebDriver driver;
    private final String pageTitleText = "52°24'00.0\"N 13°48'00.0\"E - Google Maps";

    @FindBy(id = "searchboxinput")
    @CacheLookup
    private WebElement searchTextBox;

    @FindBy(id = "searchbox-searchbutton")
    @CacheLookup
    private WebElement searchTextSubmitButton;

    @FindBy(xpath = "//span[text()='Grünheide, 15537 Grünheide (Mark)']")
    @CacheLookup
    private WebElement searchResultNameText;


    public GoogleMapsSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        System.out.println("--INSIDE GOOGLE MAPS PAGE----");
        System.out.println("title of the window : "+ driver.getTitle());
    }

    public String getPageTitleText() {
        return pageTitleText;
    }

    public WebElement getSearchTextBox() {
        return searchTextBox;
    }

    public WebElement getSearchTextSubmitButton() {
        return searchTextSubmitButton;
    }

    public WebElement getSearchResultNameText() {
        return searchResultNameText;
    }

    /**
     * Verify that the page loaded completely.
     *
     * @return the GoogleMapsSearchPage class instance.
     */
    public String verifyPageTitle() {
        return driver.getTitle();
    }

    /**
     * In google maps, search for the specified coordinates
     * by clicking on the search text box and waiting for the result
     * to load.
     * @param coordinates : coordinates of the place as String
     */
    public void searchForCoordinates(String coordinates) {
        searchTextBox.click();
        searchTextBox.sendKeys(coordinates);
        searchTextSubmitButton.click();
        TestUtil.waitForElementTobeLoaded(searchResultNameText,5);
    }
}
