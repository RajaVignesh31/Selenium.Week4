package week4.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal 
{
	public static void main(String[] args) throws InterruptedException, IOException  
	{
		//1.Load the uRL https://www.snapdeal.com/
		WebDriverManager.chromedriver().setup(); 
		ChromeDriver driver=new ChromeDriver(); 
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//2. Go to Mens Fashion
		WebElement element1 = driver.findElement(By.xpath("//span[@class='catText']"));
		Actions action = new Actions(driver);
		action.moveToElement(element1).perform();
		
		//3. Go to Sports Shoes
		driver.findElement(By.xpath("//span[@class='linkTest']")).click();
		
		//4. Get the count of the sports shoes
		String text = driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText();
		String count = text.replaceAll("[^0-9]", "");
		System.out.println(count);
		Thread.sleep(2000);
		
		//5. Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		Thread.sleep(3000);
		
		//6. Sort by Low to High
		driver.findElement(By.xpath("//div[@class='sort-selected']")).click();
		driver.findElement(By.xpath("//ul[@class='sort-value']/li[2]")).click();
		Thread.sleep(2000);
		
		//7. Check if the items displayed are sorted correctly
		List<WebElement> elements = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));	
		Thread.sleep(2000);
		
		List<String> list = new ArrayList<String>();
		
		for (WebElement element : elements) 
		{
			String cost = element.getText();
			list.add(cost);
		}

		System.out.println(list);
		System.out.println("The items are sorted");
		
		//8.Select the price range (900-1200)
		WebElement fromElement = driver.findElement(By.xpath("//input[@name='fromVal']"));
		fromElement.clear();
		fromElement.sendKeys("900");
		
		WebElement toElement = driver.findElement(By.xpath("//input[@name='toVal']"));
		toElement.clear();
		toElement.sendKeys("1200");
		
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();
		Thread.sleep(2000);
		
		//9.Filter with color Black 
		boolean enabled = driver.findElement(By.xpath("//label[@for='Color_s-Black']")).isEnabled();
		if(enabled)
		{
			driver.findElement(By.xpath("//label[@for='Color_s-Black']")).click();
			System.out.println("Black Checked");
		}
		else
		{
			driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();
			System.out.println("Navy Checked");
		}
		
		Thread.sleep(2000);
		
		//10 verify the all applied filters 
		String start = driver.findElement(By.xpath("//input[@name='fromVal']")).getText();
		String end = driver.findElement(By.xpath("//input[@name='toVal']")).getText();
		if(start.equals("900") && end.equals("1200"))
		{
			System.out.println("The start and end value are verified");
		}
		
		boolean selected = driver.findElement(By.xpath("//input[@name='fromVal']")).isSelected();
		System.out.println(selected);
		if(selected)
		{	
			System.out.println("The color is verified");
		}
		
		//11. Mouse Hover on first resulting Training shoes
		WebElement resultElement = driver.findElement(By.xpath("//img[@class='product-image wooble']"));
		action.moveToElement(resultElement).perform();
		Thread.sleep(2000);
		
		//12. click QuickView button
		driver.findElement(By.xpath("//div[contains(@class,'center quick-view-bar')]")).click();
		Thread.sleep(2000);
		
		//13. Print the cost and the discount percentage
		String cost = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		System.out.println("Total Cost : " + cost);
		
		String percentage = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("Total Percentage : " + percentage);
		Thread.sleep(2000);
		
		//14. Take the snapshot of the shoes. 
		WebElement shoeElement = driver.findElement(By.xpath("//img[@itemprop='image']"));
		
		File screenShot = shoeElement.getScreenshotAs(OutputType.FILE);
		File image = new File("./snaps/img2.jpg");
		FileUtils.copyFile(screenShot, image);
		
		//15. Close the current window
		Thread.sleep(3000);
		driver.close();
		
		//16. Close the main window
		Thread.sleep(2000);
		driver.quit();
	}
}
