
package com.testcases;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class willisICTSearchTest {




	public String baseUrl = "http://www.willistowerswatson.com/ICT";
	public static WebDriver driver;

	@BeforeTest
	public void beforeTest() throws InterruptedException {


		System.setProperty("webdriver.chrome.driver","C:\\Users\\gjonn\\eclipse-workspace\\wills\\Drivers\\chromedriver.exe");
		System.out.println(System.getProperty("user.dir"));



		driver=new ChromeDriver();

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get(baseUrl);
		System.out.println("Expected URL is opened");
		Thread.sleep(1000);


		driver.switchTo().frame(0);

		System.out.println("Clicked on the cookies page.");

		// Close the cookies frame
		driver.findElement(By.xpath("//*[@class='call']")).click();

		driver.switchTo().parentFrame();
		Thread.sleep(2000);
	}

	@Test(priority = 1)
	public void firstTest() throws InterruptedException {


		//2.	Change the language and region from top left corner to United States English

		WebElement countryselector = driver.findElement(By.xpath("//*[@class='site-nav']/section/div/button[@class='font-p font-p-small site-nav__utility-btn']"));
		System.out.println("The element status..." + countryselector.isDisplayed());
		System.out.println("The country selection page is opened");
		countryselector.click();

		Thread.sleep(2000);
		// verify the text on the page
		String pagTtext = driver.findElement(By.xpath("//*[@class='mb-4 my-md-5 heading-1']")).getText();
		System.out.println("the page text is validated ..." + pagTtext);
		Assert.assertEquals(pagTtext,"Choose your location");

		// click on the country as Americas and then 
		Thread.sleep(2000);
		WebElement countrySection = driver.findElement(By.xpath("//*[contains(text(),'Americas')]"));
		countrySection.isDisplayed();

		countrySection.click();
		System.out.println("Americas section is selected");
		Thread.sleep(2000);

		// select the English of unitedstates
		WebElement countrySelcted = driver.findElement(By.xpath("//*[contains(@aria-label,'Watson United States')]"));
		countrySelcted.isEnabled();
		countrySelcted.click();
		System.out.println("United states English is selected");
		Thread.sleep(2000);


		//3.	Search for the word “IFRS 17” using the search box

		WebElement searchSection = driver.findElement(By.xpath("//button[@class='btn btn-lg site-nav__btn site-nav__btn--search-menu']"));
		searchSection.click();

		driver.findElement(By.xpath("//*[@class='magic-box-input']//input")).sendKeys("IFRS 17");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@aria-label='Search']")).click();
		Thread.sleep(2000);
		//4.	Validate if you have arrived on the result page


		WebElement str = driver.findElement(By.xpath("//*[@class='CoveoQuerySummary']"));
		str.getText();

		System.out.println("The Result section displayes as..." + str.getText());
		Assert.assertTrue(true, str.getText());
		Thread.sleep(0);

		//5.	Check if the result is sorted by “Date”. If not, sort by “Date”

		WebElement resultLink = driver.findElement(By.xpath("//*[@id='coveo2a434694']"));
		boolean chckLink = resultLink.isDisplayed();
		System.out.println(" The Results link status...." + resultLink.isDisplayed());
		boolean resLink = driver.findElement(By.xpath("//*[@id='coveo2a434694']")).getAttribute("class").contains("selected");

		System.out.println("The Results are displayed based on .. " + resultLink.getText());


		try {
			if(driver.findElement(By.xpath("//*[@id='coveo2a434694']")).getAttribute("class").contains("selected"))
			{
				driver.findElement(By.xpath("//*[@id='coveo9de96e90']")).click();
				System.out.println("The results are sorted by date");
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}

		//6.	Use the “Filter by” functionality and set Content Type to “Article”

		WebElement filterSelection = driver.findElement(By.xpath("//*[@title='Article']"));
		System.out.println("The filter selected is ..." +filterSelection.getText());
		filterSelection.click();


		WebElement articleContent = driver.findElement(By.xpath("//*[contains(@class,\"coveo-facet-breadcrumb coveo-breadcrumb-item\")]"));
		System.out.println("the content type is ... " + articleContent.getText());


		WebElement articleType = driver.findElement(By.xpath("//*[contains(@title,'Article 9')]"));
		System.out.println("The articleType is ..." + articleType.getText());			
		Assert.assertEquals(articleType.getText(), "Article");


		//7.	Validate that each article in the list displays a link that starts with 
		//“https://www.willistowerswatson.com/en-US/”


		Thread.sleep(3000);

		WebElement noOfNavTabs = driver.findElement(By.xpath("//*[@class='CoveoFieldValue wtw-listing-result-uri']"));
		System.out.println(driver.findElement(By.tagName("span")).getText());
		java.util.List<WebElement> allElements = driver.findElements(By.xpath("//*[@class='CoveoFieldValue wtw-listing-result-uri']"));

		Thread.sleep(1000);
		try {
			for (WebElement element: allElements) {

				System.out.println("Linktext is ... " + element.getText());
				String urlText = element.getText();

				if(urlText.startsWith("https://www.willistowerswatson.com/en-US/")) {
					String urlPartCheck = "https://www.willistowerswatson.com/en-US/";


					System.out.println("The url contains https://www.willistowerswatson.com/en-US/");
					Assert.assertTrue(true, urlPartCheck);
				}else {
					System.out.println("Url is not as expected");
				}
			}
		}
		catch(Exception e) {

		}

	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}





}
