package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WikipediaSearchPage {
    private final String pageLoadedText = "Save your favorite articles to read offline, sync your reading lists across devices and customize your reading experience with the official Wikipedia app";
    private final String pageTitle = "Wikipedia";
    private WebDriver driver;
    private int timeout = 15;
    @FindBy(xpath = "//input[@id='searchInput']")
    @CacheLookup
    private WebElement searchTextBox;

    @FindBy(xpath = "//*[@id='search-form']/fieldset/button")
    @CacheLookup
    private WebElement searchTextButton;

    //initialization constructor
    public WikipediaSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        System.out.println("--INSIDE WIKIPEDIA SEARCH PAGE----");
    }

    //return the page title text stored as string
    public String verifyPageTitleText() {
        return pageTitle;
    }

    /**
     * search the input text using the search text box on
     * wikipedia search page.
     *
     * @param wikiSearch : search text as input
     */
    public void searchWikipedia(String wikiSearch) {
        searchTextBox.click();
        searchTextBox.sendKeys(wikiSearch);
        searchTextButton.click();
    }

    /**
     * get the title of the page using the getTitle() method
     *
     * @return the WikipediaSearchPage class instance.
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
}
