package steps;

import base.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import pages.GoogleMapsSearchPage;
import pages.GoogleSearchPage;
import pages.WikipediaSearchPage;
import pages.WikipediaSearchResultPage;
import utilities.TestUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GoogleWikiSearchStep extends TestBase {
	public static WebDriver driver;
	GoogleSearchPage googleSearchPage;
	WikipediaSearchPage wikiSearchPage;
	WikipediaSearchResultPage wikipediaSearchResultPage;
	GoogleMapsSearchPage googleMapsSearchPage;
	TestUtil testUtil;

	@Before
	public void setUp() {
		TestUtil.cleanScreenshotDirectory();
		this.driver = initialization();
		testUtil = new TestUtil(driver);

	}

	//Cucumber hook : executes after each step
	@AfterStep
	public void executeAfterStep() throws IOException {
		TestUtil.takeScreenshot();
	}

	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}

	@Given("user opens the google search home page with {string}")
	public void user_opens_the_google_search_home_page_with(String googleURL) {
		driver.navigate().to(googleURL);
		googleSearchPage = new GoogleSearchPage(driver);
		Assert.assertEquals(googleSearchPage.verifyPageTitle(), googleSearchPage.getPageTitle());
	}

	@Given("search {string} to navigate to wikipedia search page")
	public void search_to_navigate_to_wikipedia_search_page(String wikiUrl) throws InterruptedException {
		googleSearchPage.searchInputData(wikiUrl);
		googleSearchPage.clickOnGoogleSearchResult();
		wikiSearchPage = new WikipediaSearchPage(driver);
		Assert.assertEquals(wikiSearchPage.verifyPageTitleText(), wikiSearchPage.getPageTitle());
	}

	@When("user searches for {string} page in wikipedia")
	public void user_searches_for_page_in_wikipedia(String wikiSearch) {
		wikiSearchPage.searchWikipedia(wikiSearch);
		wikipediaSearchResultPage = new WikipediaSearchResultPage(driver);
		Assert.assertEquals(wikipediaSearchResultPage.getPageTitle(),wikipediaSearchResultPage.verifyPageTitle());
	}

	@When("wiki search page is displayed with relevant information")
	public void wiki_search_page_is_displayed_with_relevant_information() throws IOException {
		HashMap<String, String> dataForFile = wikipediaSearchResultPage.addHeaderAndParagraphDataToMap();
		TestUtil.addDataToFile(prop.getProperty("actual.data.filepath"), dataForFile);
		Assert.assertTrue(TestUtil.compareFiles(prop.getProperty("actual.data.filepath"),prop.getProperty("base.data.filepath")));
	}

	@Then("verify that coordinates information is correct")
	public void verify_that_coordinates_information_is_correct() {
		Assert.assertEquals(wikipediaSearchResultPage.getCoordinateValue(), wikipediaSearchResultPage.getCoordinateElementText());
	}

	@Then("verify that the logistics data is displayed correctly")
	public void verify_that_the_location_information_displayed_is_correct() {
		Assert.assertEquals(wikipediaSearchResultPage.getLogisticsHeaderText().getText(),"Logistics");
	}

	@Then("verify that the site concerns data displayed is correctly")
	public void verify_that_the_site_concerns_information_displayed_is_correct() {
		Assert.assertEquals(wikipediaSearchResultPage.getSiteConcernsHeaderText().getText(),"Site concerns");
	}

	@Then("open new tab for google maps {string} and verify the coordinates")
	public void open_new_tab_for_google_maps_and_verify_the_coordinates(String mapsUrl) {
		String mainWindow = driver.getWindowHandle();
		//action class is used to perform Ctrl+click on the coordinate link
		Actions action= new Actions(driver);
		action.moveToElement(wikipediaSearchResultPage.getCoordinateElementValue()).keyDown(Keys.CONTROL).click().keyUp(Keys.CONTROL).build().perform();
		//open the google map url
		googleMapsSearchPage = new GoogleMapsSearchPage(driver);
		//get all the window handles active currently
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		for (String win : tabs) {
			if (!mainWindow.equals(win)) {
				//focus to the new tab(window handle)
				driver.switchTo().window(win);
				driver.navigate().to(mapsUrl);
				googleMapsSearchPage.searchForCoordinates(wikipediaSearchResultPage.getCoordinateElementText());
				Assert.assertTrue(googleMapsSearchPage.verifyPageTitle().contains("- Google Maps"));
				Assert.assertEquals(googleMapsSearchPage.getSearchResultNameText().getText(),"Grünheide, 15537 Grünheide (Mark)");

			}
		}
	}

}
