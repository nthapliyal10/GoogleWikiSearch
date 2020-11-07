package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public class TestUtil {
	public static WebDriver driver;
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	static WebDriverWait wait;

	/**
	 * constructor for the class with instance of WebDriver as input
	 *
	 * @param driver - instance of WebDriver as input
	 */
	public TestUtil(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Explicit wait : using this WebDriver will wait till the element is
	 * displayed and is in 'clickable' state
	 *
	 * @param webElement - element for which the expected condition will be
	 * checked
	 *
	 * @param waitTime - WebDriver will wait for 'waitTime' seconds
	 */
	public static void waitForElementToBeClickable(WebElement webElement, int waitTime) {
		wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}

	/**
	 * Explicit wait : using this WebDriver will wait till the element is
	 * displayed.
	 *
	 * @param webElement - element for which the expected condition will be
	 * checked
	 *
	 * @param waitTime - WebDriver will wait for 'waitTime' seconds
	 */
	public static void waitForElementTobeLoaded(WebElement webElement, int waitTime) {
		wait = new WebDriverWait(driver, waitTime);
		try{
			wait.until(ExpectedConditions.visibilityOf(webElement));
		} catch (Exception NoSuchElementException) {
			NoSuchElementException.printStackTrace();
		}
	}

	/**
	 * Explicit wait : using this WebDriver will wait till the element is
	 * visible using its locator.
	 *
	 * @param elementLocatorType - type of locator using which element is to be
	 * identified. Options : id, xpath, linkText, cssSelector
	 *
	 * @param elementLocatorValue - WebDriver will use this value to find the
	 * element.
	 *
	 * @param waitTime - WebDriver will wait for 'waitTime' seconds.
	 */
	public static void waitForVisibilityOfElementLocatedBy(String elementLocatorType, String elementLocatorValue,
														   int waitTime) {
		wait = new WebDriverWait(driver, waitTime);
		if (elementLocatorType.equalsIgnoreCase("id")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementLocatorValue)));
		} else if (elementLocatorType.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementLocatorValue)));
		} else if (elementLocatorType.equalsIgnoreCase("linkText")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(elementLocatorValue)));
		} else if (elementLocatorType.equalsIgnoreCase("cssSelector")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elementLocatorValue)));
		}
	}

	/**
	 * Adds data to a file mentioned in filepath. The data is provided in
	 * the form of a Map of Strings.
	 * @param filePath - path of the file to which the data should be added
	 *
	 * @param inputData - Map containing the input data in key-value
	 *  form to be written in output file
	 */
	public static void addDataToFile (String filePath, Map<String, String> inputData) throws IOException {
		BufferedWriter bw = Files.newBufferedWriter(Paths.get(System.getProperty("user.dir")+filePath),
				StandardOpenOption.TRUNCATE_EXISTING);
		for(Map.Entry<String, String> input : inputData.entrySet()) {
			// append text to file
			bw.write(input.getKey()+ "," + input.getValue());
			bw.newLine();
		}
		// close the writer
		bw.close();
	}

	/**
	 * Take screenshot of the visible screen as file and
	 * copy the files to the path mentioned
	 */
	public static void takeScreenshot() throws IOException {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Remove the existing screenshot files from
	 *  the screenshots directory before every execution
	 */
	public static void cleanScreenshotDirectory() {
		try {
			File screenshotFile = new File(System.getProperty("user.dir") + "/screenshots/");
			FileUtils.cleanDirectory(screenshotFile);
			System.out.println("screenshot directory has been cleaned....");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * compare two files containing data
	 * the screenshots directory before every execution
	 * @param baseFilePath : the file containing expected data
	 * @param newFilePath : file containing the actual retrieved data
	 * @return boolean : output of the file comparision
	 */
	public static boolean compareFiles(String baseFilePath, String newFilePath) throws IOException {
		boolean result = false;
		try {
			File baseFile = new File(System.getProperty("user.dir") + baseFilePath);
			File newFile = new File(System.getProperty("user.dir") + newFilePath);
			result = FileUtils.contentEqualsIgnoreEOL(baseFile, newFile, "utf-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

}
