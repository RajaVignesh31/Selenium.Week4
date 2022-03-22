package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContactWindow
{
	public static void main(String[] args) throws InterruptedException 
	{
		 // 1. Launch URL "http://leaftaps.com/opentaps/control/login"
		 WebDriverManager.chromedriver().setup(); 
		 ChromeDriver driver=new ChromeDriver(); 
		 driver.get("http://leaftaps.com/opentaps/control/login");
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		 
		 //2. Enter UserName and Password Using Id Locator
		 driver.findElement(By.id("username")).sendKeys("Demosalesmanager");
		 driver.findElement(By.id("password")).sendKeys("crmsfa");
		 
		 //3. Click on Login Button using Class Locator
		 driver.findElement(By.className("decorativeSubmit")).click();
		 
		 //4. Click on CRM/SFA Link
		 driver.findElement(By.linkText("CRM/SFA")).click();
		 
		 // 5. Click on contacts Button
		 driver.findElement(By.xpath("//a [text()='Contacts']")).click();
		 
		 //6. Click on Merge Contacts using Xpath Locator
		 driver.findElement(By.xpath("//a [text()='Merge Contacts']")).click();
		 
		 //7. Click on Widget of From Contact
		 driver.findElement(By.xpath("(//img [@alt ='Lookup'])[1]")).click();
		 
		 //Explicit Wait
		 //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		 //wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		 
		 Thread.sleep(2000);
		 
		 //8. Click on First Resulting Contact
		 Set<String> windowHandles = driver.getWindowHandles();
		 Iterator<String> iterator = windowHandles.iterator();
		 String s1 = iterator.next();
		 String s2 = iterator.next();
		 //List<String> allWindowsHandleList = new ArrayList<String>(windowHandles);			 
		 
		 driver.switchTo().window(s2);
		 
		 System.out.println(driver.getCurrentUrl());
		 String text = driver.findElement(By.xpath("(//a [@class = 'linktext'])[1]")).getText();
		 driver.findElement(By.xpath("(//a [@class = 'linktext'])[1]")).click();
		 System.out.println("Clicked :" +text);
		 
		 //Return to the main Window
		 driver.switchTo().window(s1);		 
		 
		 //9. Click on Widget of To Contact
		 driver.findElement(By.xpath("(//img [@alt ='Lookup'])[2]")).click();
		 
		 //Explicit Wait
		 //wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		 Thread.sleep(2000);
		 
		 //10. Click on Second Resulting Contact

		 Set<String> windowHandles2 = driver.getWindowHandles();
		 List<String> allWindowsHandleList1 = new ArrayList<String>(windowHandles2);
	 
		 driver.switchTo().window(allWindowsHandleList1.get(1));
		 
		 System.out.println(driver.getCurrentUrl());
		 String text1 = driver.findElement(By.xpath("(//a [@class = 'linktext'])[6]")).getText();
		 driver.findElement(By.xpath("(//a [@class = 'linktext'])[6]")).click();
		 System.out.println("Clicked :" +text1);
		 
		 //Return to the main Window
		 driver.switchTo().window(allWindowsHandleList1.get(0));		 
		 
		 //11. Click on Merge button using Xpath Locator
		 driver.findElement(By.xpath("//a [@class = 'buttonDangerous']")).click();
		 
		 //12. Accept the Alert
		 Alert alert = driver.switchTo().alert();
		 alert.accept();
		 
		 //13. Verify the title of the page
		 System.out.println(driver.getTitle());
		 
		 Thread.sleep(2000);
		 driver.close();
	}
}
