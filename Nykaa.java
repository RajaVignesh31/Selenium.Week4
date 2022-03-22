package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa 
{
	public static void main(String[] args) throws InterruptedException 
	{
		//1) Go to https://www.nykaa.com/
		 WebDriverManager.chromedriver().setup(); 
		 ChromeDriver driver=new ChromeDriver(); 
		 driver.get("https://www.nykaa.com/");
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		 
		 //2) Mouseover on Brands and Search L'Oreal Paris				 
		 driver.findElement(By.xpath("//a [text() ='brands']"));
		 Actions action = new Actions(driver);
		 action.moveToElement(driver.findElement(By.xpath("(//ul [@class = 'HeaderNav css-f7ogli'])[2]"))).perform();
		 driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
		 
		 //3) Click L'Oreal Paris
		 driver.findElement(By.xpath("//div[@class='css-ov2o3v']//a")).click(); 	 
		 
		 //4) Check the title contains L'Oreal Paris(Hint-GetTitle)
		 String titleText = driver.getTitle();
		 if (titleText.contains("L'Oreal Paris"))
		 {
			 System.out.println("The title contains : " + titleText);
		 }
		 
		 //5) Click sort By and select customer top rated
		 driver.findElement(By.xpath("//button [@class = ' css-n0ptfk']")).click();
		 driver.findElement(By.xpath("(//div [@class ='control-indicator radio '])[3]")).click();	 
		 Thread.sleep(2000);
		 
		 //6) Click Category and click Hair->Click haircare->Shampoo
		 driver.findElement(By.xpath("//span [text()='Category']")).click();
		 driver.findElement(By.xpath("//span [text()='Hair']")).click();
		 driver.findElement(By.xpath("//span [text()='Hair Care']")).click();
		 driver.findElement(By.xpath("//span [text() = 'Shampoo']")).click();		 
		 Thread.sleep(2000);
		 
		 //7) Click->Concern->Color Protection
		 driver.findElement(By.xpath("//span [text()='Concern']")).click();
		 driver.findElement(By.xpath("//span [text()='Color Protection']")).click();	 
		 Thread.sleep(2000);
		 
		 //8) check whether the Filter is applied with Shampoo
		 boolean isDisplayed = driver.findElement(By.xpath("//span [text() = 'Filters Applied']")).isDisplayed();
		 boolean shampooFilter = driver.findElement(By.xpath("//span [text() = 'Shampoo']")).isDisplayed();
		 boolean colorProtectionFilter = driver.findElement(By.xpath("//span [text() = 'Color Protection']")).isDisplayed();
		 System.out.println("The Filters applied Visibility: " +isDisplayed); 
		 System.out.println("The Shampoo Filters applied Visibility: " +shampooFilter); 
		 System.out.println("The Color Protection Filters applied Visibility: " +colorProtectionFilter); 	 
		 Thread.sleep(2000);
		 
		 //9) Click on L'Oreal Paris Colour Protect Shampoo
		 driver.findElement(By.xpath("//div[@class ='css-43m2vm']")).click();
		 Thread.sleep(2000);
		 
		 //10) GO to the new window and select size as 175ml
		 Set<String> windowHandles = driver.getWindowHandles();
		 List<String> windowHandlesList = new ArrayList<String>(windowHandles);
		 
		 // switch window
		 driver.switchTo().window(windowHandlesList.get(1));
		 Thread.sleep(2000);
		 
		 WebElement element = driver.findElement(By.xpath("//select [@title ='SIZE']"));
		 Select size = new Select(element);
		 driver.findElement(By.xpath("//select [@title ='SIZE']")).click();
		 size.selectByIndex(0);
		 
		 //11) Print the MRP of the product
		 String mrp1 = driver.findElement(By.xpath("//span [@class ='css-1jczs19']")).getText();
		 String mrp = mrp1.replaceAll("[^0-9]", "");
		 System.out.println("The MRP of the Product is : "+mrp);
		 
		 //12) Click on ADD to BAG
		 driver.findElement(By.xpath("(//span [text() = 'ADD TO BAG'])[1]")).click();

		 //13) Go to Shopping Bag
		 driver.findElement(By.xpath("//span [@class = 'cart-count']")).click();
		 
		 //14) Print the Grand Total amount
		 //switch to frame
		 WebElement findElement5 = driver.findElement(By.xpath("//iframe [@class ='css-acpm4k']"));
		 driver.switchTo().frame(findElement5);
		 Thread.sleep(2000);
		 
		 WebElement findElement4 = driver.findElement(By.xpath("(//div [@class = 'value'])[4]"));
		 findElement4.click();
		 String grandTotal1 = findElement4.getText();
		 String grandTotal = grandTotal1.replaceAll("[^0-9]", "");
		 System.out.println("The Grand Total amount is :"+grandTotal);
		 
		 //15) Click Proceed
		 driver.findElement(By.xpath("//span [text()='PROCEED']")).click();
		 
		 //16) Click on Continue as Guest
		 //switch back to default
		 driver.switchTo().defaultContent();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//button[@class='btn full big']")).click();
		 
		 //17) Check if this grand total is the same in step 14
		 WebElement findElement6 = driver.findElement(By.xpath("(//div [@class ='value']/following::span)[3]"));
		 findElement6.click();
		 //action.moveToElement(findElement6).perform();
		 String finalAmount1 = findElement6.getText();
		 String finalAmount = finalAmount1.replaceAll("[^0-9]" ,"");
		 if (finalAmount.contains(grandTotal))
		 {
			 System.out.println("The grand total is same as the final amount in checkout page - Verified successfully!");
		 }
		 
		 //18) Close all windows
		 Thread.sleep(5000);
		 driver.quit();
	}
}
