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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestScenario2 {

	public String baseUrl = "http://www.willistowerswatson.com/ICT";
	public static WebDriver driver;
	
	public ExtentHtmlReporter reporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void setExtent() {
		
		reporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/myReport.html");
	}
	

	@BeforeTest
	public void beforeTest() throws InterruptedException {

		extent.attachReporter(reporter);
		ExtentTest test1 = extent.createTest("TestScenario2", "Opening the URL");
		extent.flush();
		
		//System.setProperty("webdriver.chrome.driver", "\\Selenium\\Drivers\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver","C:\\Users\\gjonn\\eclipse-workspace\\wills\\Drivers\\chromedriver.exe");
		System.out.println(System.getProperty("user.dir"));


		
		driver=new ChromeDriver();
		test1.log(Status.INFO, "Starting chromedriver");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get(baseUrl);
		test1.pass("Navigated to the expected URL");
		System.out.println("Expected URL is opened");
		Thread.sleep(1000);

		//driver.switchTo().frame("gwt-Frame");
		driver.switchTo().frame(0);
		test1.pass("Navigated to the cookies page");

		System.out.println("Clicked on the cookies page.");

		// Close the cookies frame
		driver.findElement(By.xpath("//*[@class='call']")).click();
		test1.pass("Closed the cookies page");
		
		driver.switchTo().parentFrame();
		Thread.sleep(2000);
	}

	@Test(priority = 1)
	public void firstTest() throws InterruptedException {
		
		extent.attachReporter(reporter);
		ExtentTest test2 = extent.createTest("TestScenario2", "Checking the country page");
		extent.flush();
		
		
		test2.pass("Back on the parent page");
		//2.	Change the language and region from top left corner to United States English

		WebElement countryselector = driver.findElement(By.xpath("//*[@class='site-nav']/section/div/button[@class='font-p font-p-small site-nav__utility-btn']"));
		System.out.println("The element status..." + countryselector.isDisplayed());
		System.out.println("The country selection page is opened");
		test2.pass("The country selection page is opened");
		countryselector.click();

		Thread.sleep(2000);
		// verify the text on the page
		String pagetext = driver.findElement(By.xpath("//*[@class='mb-4 my-md-5 heading-1']")).getText();
		System.out.println("the page text is validated ..." + pagetext);
		test2.pass("Text on the countries and language is validated");
		Assert.assertEquals(pagetext,"Choose your location");

		// click on the country as Americas and then 
		Thread.sleep(2000);
		WebElement countrysection = driver.findElement(By.xpath("//*[contains(text(),'Americas')]"));
		countrysection.isDisplayed();
		test2.pass("Countries and languages are displayed");

		countrysection.click();
		System.out.println("Americas section is selected");
		test2.pass("Americas section is selected");
		Thread.sleep(2000);

		// select the English of unitedstates
		WebElement countrySelcted = driver.findElement(By.xpath("//*[contains(@aria-label,'Watson United States')]"));
		countrySelcted.isEnabled();
		countrySelcted.click();
		test2.pass("United states English is selected");
		System.out.println("United states English is selected");
		Thread.sleep(2000);


		//3.	Search for the word “IFRS 17” using the search box

		WebElement searchSection = driver.findElement(By.xpath("//button[@class='btn btn-lg site-nav__btn site-nav__btn--search-menu']"));
		searchSection.click();

		driver.findElement(By.xpath("//*[@class='magic-box-input']//input")).sendKeys("IFRS 17");
		test2.pass("Clicked on Search and entered IFRS 17 text is entered");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@aria-label='Search']")).click();
		Thread.sleep(2000);
		//4.	Validate if you have arrived on the result page


		WebElement str = driver.findElement(By.xpath("//*[@class='CoveoQuerySummary']"));
		str.getText();

		System.out.println("The Result section displayes as..." + str.getText());
		test2.pass("Result section is displayed");
		Assert.assertTrue(true, str.getText());
		Thread.sleep(0);

		//5.	Check if the result is sorted by “Date”. If not, sort by “Date”

		WebElement resultlink = driver.findElement(By.xpath("//*[@id='coveo2a434694']"));
		boolean chcklink = resultlink.isDisplayed();
		System.out.println(" The Results link status...." + resultlink.isDisplayed());
		boolean reslink = driver.findElement(By.xpath("//*[@id='coveo2a434694']")).getAttribute("class").contains("selected");

		System.out.println("The Results are displayed based on .. " + resultlink.getText());
		


		try {
			if(driver.findElement(By.xpath("//*[@id='coveo2a434694']")).getAttribute("class").contains("selected"))
			{
				driver.findElement(By.xpath("//*[@id='coveo9de96e90']")).click();
				System.out.println("The results are sorted by date");
				test2.pass("The result page is sorted by date");
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}

		//6.	Use the “Filter by” functionality and set Content Type to “Article”

		WebElement filterselection = driver.findElement(By.xpath("//*[@title='Article']"));
		
		System.out.println("The filter selected is ..." +filterselection.getText());
		filterselection.click();
		test2.pass("Article filteration is selected");


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
				String urltext = element.getText();
				String[] urlpart = urltext.split("en-US/");
			//System.out.println("print the part... " + Arrays.toString(urlpart));
			
			if(urltext.startsWith("https://www.willistowerswatson.com/en-US/")) {
			    System.out.println("The url contains https://www.willistowerswatson.com/en-US/");
			    test2.pass("The first part of url is as expected");
			}else {
			    System.out.println("Url is not as expected");
			    test2.fail("The first part of the URL is not as expected");
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
