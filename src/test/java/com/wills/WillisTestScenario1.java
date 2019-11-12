package com.wills;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
public class WillisTestScenario1 {

	public static void main(String[] args) throws InterruptedException {


		//Lauch the driver

		System.setProperty("webdriver.chrome.driver", "\\Selenium\\Drivers\\chromedriver.exe");	
		WebDriver driver = new ChromeDriver(); 
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		//1.	Open the following URL: http://www.willistowerswatson.com/ICT 
		driver.get("http://www.willistowerswatson.com/ICT ");
		Thread.sleep(5000);

		//Switch to the frame to test
		driver.switchTo().frame(0);
		Thread.sleep(2000);

		// Close the cookies frame
		driver.findElement(By.xpath("//*[@class='call']")).click();
		//Thread.sleep(2000);

		//2.	Change the language and region from top left corner to United States English

		driver.findElement(By.xpath("//button[@data-menu='country-selector--is-visible']/parent::div/parent::section/parent::nav")).click();
		Thread.sleep(2000);

		//3.	Search for the word “IFRS 17” using the search box
		driver.findElement(By.xpath("//button[@class='btn btn-lg site-nav__btn site-nav__btn--search-menu']")).click();

		driver.findElement(By.xpath("//*[@class='magic-box-input']//input")).sendKeys("IFRS 17");
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@aria-label='Search']")).click();


		//4.	Validate if you have arrived on the result page
	

		WebElement str = driver.findElement(By.xpath("//*[@class='CoveoQuerySummary']"));
		str.getText();

		System.out.println("The text found is..." + str.getText());
		Assert.assertTrue(true, str.getText());
		Thread.sleep(0);



		//5.	Check if the result is sorted by “Date”. If not, sort by “Date”
		
		WebElement resultlink = driver.findElement(By.xpath("//*[@id='coveo2a434694']"));
		boolean chcklink = resultlink.isDisplayed();
		System.out.println(" the result link is...." + resultlink.isDisplayed());
		boolean reslink = driver.findElement(By.xpath("//*[@id='coveo2a434694']")).getAttribute("class").contains("selected");

		System.out.println("the activelink is .. " + resultlink.getText());

		
		try {
			if(driver.findElement(By.xpath("//*[@id='coveo2a434694']")).getAttribute("class").contains("selected"))
			{
				driver.findElement(By.xpath("//*[@id='coveo9de96e90']")).click();
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}

		//6.	Use the “Filter by” functionality and set Content Type to “Article”

		
		driver.findElement(By.xpath("//*[@title='Article']")).click();

		//7.	Validate that each article in the list displays a link that starts with 
		//“https://www.willistowerswatson.com/en-US/”


		WebElement noOfNavTabs = driver.findElement(By.xpath("//*[@class='CoveoFieldValue wtw-listing-result-uri']"));
		System.out.println(driver.findElement(By.tagName("span")).getText());
		java.util.List<WebElement> allElements = driver.findElements(By.xpath("//*[@class='CoveoFieldValue wtw-listing-result-uri']"));

		for (WebElement element: allElements) {

			System.out.println("Linktext is ... " + element.getText());
			String linktext = driver.findElement(By.xpath("//*[@class='CoveoFieldValue wtw-listing-result-uri']/span[start-with(text(),'https://www.willistowerswatson.com/en-US/')]")).getText();

			String partiallink = "https://www.willistowerswatson.com/en-US/";
			WebElement parlinktext = driver.findElement(By.partialLinkText(partiallink));
			System.out.println("partial link ..." + parlinktext);
			

		}
	}





}
