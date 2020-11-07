package pages;

import base.TestBase;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.TestUtil;

public class GoogleSearchPage {
    private WebDriver driver;
    private int timeout = 15;

    private final String pageTitle = "Google";
    
    @FindBy(xpath = "//input[@aria-label='Google Search' and @name='btnK']")
    private WebElement pageLoadTextElement;
    
    @FindBy(xpath = "//input[@id='gbqfbb']")
    private WebElement googleSearchFeelingLuckyButton;

    @FindBy(xpath = "//img[@id='hplogo']")
    private WebElement googleSearchLogoImage;

    @FindBy(xpath = "//input[@name='q']")
    @CacheLookup
    private WebElement searchTextBox;
  
    @FindBy(xpath = "//*[@id='cnsw']/iframe")
    @CacheLookup
    private WebElement cookiesIframe;
    
    @FindBy(xpath = "//*[@id='introAgreeButton']/span/span")
    private WebElement acceptCookiesButton;
    
    @FindBy(xpath = "//a[@href='https://www.wikipedia.com/']")
    private WebElement googleSearchResultOptionToClick;

    //initialization constructor
    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        System.out.println("--INSIDE GOOGLE SEARCH PAGE----");
    }

    //return the page title text
    public String getPageTitle() {
    	return pageTitle;
    }
    
    /**
     * search the input data using google search page
     * text box.
     * @param wikiUrl : url of the wikipedia page to open
     */
    public void searchInputData(String wikiUrl) {
        //check for 'accept cookies' page, if present
        // switch to iFrame and accept the cookies
    	if (cookiesIframe.isDisplayed()){
    		driver.switchTo().frame(cookiesIframe);
    		acceptCookiesButton.click();
    		driver.switchTo().parentFrame();
        }
        searchTextBox.click();
        searchTextBox.sendKeys(wikiUrl);
    }

    /**
     * click on 'i am feeling lucky' button to directly
     * move to the first link(wikipedia).
     * click on the page after search text is input due to
     * suggestion screen masking the search button.
     */
    public void clickOnGoogleSearchResult() {
        try {
            TestUtil.waitForElementToBeClickable(googleSearchLogoImage, 5);
            googleSearchLogoImage.click();
            TestUtil.waitForElementToBeClickable(googleSearchFeelingLuckyButton, 5);
            googleSearchFeelingLuckyButton.click();
        }catch (ElementNotVisibleException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verify that current page titlw matches the expected URL.
     *
     * @return the GoogleSearchPage title text.
     */
    public String verifyPageTitle() {
             return driver.getTitle();
    }
}
