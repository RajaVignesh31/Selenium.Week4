package week4.assignments;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonActions 
{
	public static void main(String[] args) throws InterruptedException 
	{
		//1.Load the uRL https://www.amazon.in/
		WebDriverManager.chromedriver().setup(); 
		ChromeDriver driver=new ChromeDriver(); 
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				
		//2.search as oneplus 9 pro 
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro",Keys.ENTER);
		
		//3.Get the price of the first product
		String firstProductPrice = driver.findElement(By.xpath("//span [@class = 'a-price-whole']")).getText();
		System.out.println("The Price of the first Product displayed is : " +firstProductPrice);	
		
		//4. Print the number of customer ratings for the first displayed product		
		String firstDisplayedCustomerRatings = driver.findElement(By.xpath("(//span [@class='a-size-base s-underline-text'])[1]")).getText();
		System.out.println("The Number of customer ratings for the first displayed product : "+firstDisplayedCustomerRatings);	

		//5. click on the stars 
		WebElement starRating = driver.findElement(By.xpath("//i/span[@class='a-icon-alt']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(starRating).click().perform();
		String ratingFirstProduct = driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-beside-button a-text-bold']")).getText();
		System.out.println("The Number of customer * ratings for the first displayed product out of 5 is : " +ratingFirstProduct);
		
		//6. Get the percentage of ratings for the 5 star.
		WebElement table = driver.findElement(By.xpath("//table [@id ='histogramTable']"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
			
		for(int i=0;i<rows.size();i++)
		{
			List<WebElement> cols = rows.get(0).findElements(By.tagName("td"));
			String colValue = cols.get(i).getText();
			if (colValue.contains("%"))
			{
				System.out.println("The % of rating for the 5 star is : "+colValue);
				break;
			}
		}
			
		//7. Click the first text link of the first image
		driver.findElement(By.xpath("(//a/span [@class = 'a-size-medium a-color-base a-text-normal'])[1]")).click();
		
		//Switching between the windows
		Set<String> windowHandles = driver.getWindowHandles();
		//List <String> allWindowsHandleList = new ArrayList <String>(windowHandles);
		//System.out.println(allWindowsHandleList.size());
		Iterator<String> iterator = windowHandles.iterator();
		String i1 = iterator.next();
		String i2 = iterator.next();
		driver.switchTo().window(i2);
		
		//8. Click 'Add to Cart' button
		driver.findElement(By.id("add-to-cart-button")).click();
		
		//9. Get the cart subtotal and verify if it is correct.
		//Switch to main Window
		driver.switchTo().window(i1);
		
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a [@id = 'nav-cart']"))));
		Thread.sleep(2000);
		
		//refresh the page by clicking the home logo to get cart updated
		driver.findElement(By.xpath("//a [@id = 'nav-logo-sprites']")).click();
		
		//Validate the cart amount
		driver.findElement(By.xpath("//a [@id = 'nav-cart']")).click();
		String cartValue = driver.findElement(By.xpath("(//span [@class = 'a-size-medium a-color-base sc-price sc-white-space-nowrap'])[1]")).getText();
		System.out.println("The Cart Value is : " +cartValue);
		if  (cartValue.contains(firstProductPrice)) 
		{
		  System.out.println("The cart value is matched and verified"); 
		}
			
		//Close the Browser
		Thread.sleep(5000);
		driver.quit();
	}
}