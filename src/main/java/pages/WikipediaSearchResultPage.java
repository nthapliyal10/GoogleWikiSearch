package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;

public class WikipediaSearchResultPage {
    private final HashMap<String, String> data = new HashMap<String, String>();
    private final WebDriver driver;

    @FindBy(xpath = "//*[@id='search-form']/fieldset/button")
    @CacheLookup
    private WebElement searchTextButton;

    @FindBy(xpath = "//table[@class='infobox vcard']/tbody/tr/th")
    @CacheLookup
    private List<WebElement> informationTableDataHeaders;

    @FindBy(xpath = "//table[@class='infobox vcard']/tbody/tr/td")
    @CacheLookup
    private List<WebElement> informationTableDataValue;

    @FindBy(xpath = "//th[contains(text(),'Coordinates')]")
    private WebElement coordinateText;

    @FindBy(xpath = "//*[@id='coordinates']/span/span/a/span[3]/span[1]")
    private WebElement coordinateValue;

    @FindBy(id = "Logistics")
    private WebElement logisticsHeaderText;

    @FindBy(xpath = "//p[starts-with(text(),'The current water supply')]")
    private WebElement logisticsDetailsParagraph1;

    @FindBy(xpath = "//p[starts-with(text(),'Some of the reasons for choosing')]")
    private WebElement logisticsDetailsParagraph2;

    @FindBy(id = "Site_concerns")
    private WebElement siteConcernsHeaderText;

    @FindBy(xpath = "//p[starts-with(text(),'The project is subject to a number')]")
    private WebElement siteConcernsDetailsParagraph1;

    @FindBy(xpath = "//p[starts-with(text(),'A group of neighbouring municipalities')]")
    private WebElement siteConcernsDetailsParagraph2;

    @FindBy(xpath = "//a[contains(text(),'Unexploded WWII-era bombs')]")
    private WebElement siteConcernsDetailsParagraph3;

    @FindBy(xpath = "//p[starts-with(text(),'On 9 April 2020')]")
    private WebElement siteConcernsDetailsParagraph4;

    @FindBy(xpath = "//p[starts-with(text(),'On 15 October 2020')]")
    private WebElement siteConcernsDetailsParagraph5;

    //initialization constructor
    public WikipediaSearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        System.out.println("--INSIDE WIKIPEDIA SEARCH RESULT PAGE----");
        System.out.println("title of the WikipediaSearchResultPage window : "+driver.getTitle());
    }

    //return the WebElement for header with name=logistics
    public WebElement getLogisticsHeaderText() {
        return logisticsHeaderText;
    }

    //return the WebElement for header with name=site concerns
    public WebElement getSiteConcernsHeaderText() {
        return siteConcernsHeaderText;
    }

    //return the coordinates of the place as text(string)
    public String getCoordinateElementText() {
        String gigaBerlincoordinates = "52.4°N 13.8°E";
        return gigaBerlincoordinates;
    }

    //return the WebElement for header with name=coordinates
    public WebElement getCoordinateElementValue() { return coordinateValue; }

    //return the output of the coordinates webElement
    public String getCoordinateValue() {
        return coordinateValue.getText();
    }

    /**
     * get the title of the page using the getTitle() method
     *
     * @return the WikipediaSearchResultPage title text.
     */
    public String getPageTitle() {
        String pageTitle = "Giga Berlin - Wikipedia";
        return pageTitle;
    }

    /**
     * add the logistics, site concerns and coordinates information
     * from the wiki page and store it into the Map as key value pair.
     *
     * @return Map : return the map containing all the data as key-value pair
     * as map
     */
    public HashMap<String,String> addHeaderAndParagraphDataToMap() {
        String logisticData = logisticsDetailsParagraph1.getText()+logisticsDetailsParagraph2.getText();
        String siteConcernsData = siteConcernsDetailsParagraph1.getText()+siteConcernsDetailsParagraph2.getText()+siteConcernsDetailsParagraph3.getText()+siteConcernsDetailsParagraph4.getText()+siteConcernsDetailsParagraph5.getText();
        data.put(coordinateText.getText(), coordinateValue.getText());
        data.put(logisticsHeaderText.getText(), logisticData);
        data.put(siteConcernsHeaderText.getText(), siteConcernsData);
        return data;
    }


    /**
     * Verify that current page title matches the expected URL.
     *
     * @return the page title String.
     */
    public String verifyPageTitle() {
        return driver.getTitle();
    }
}
