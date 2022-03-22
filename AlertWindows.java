package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertWindows 
{
	public static void main(String[] args) throws InterruptedException 
	{
		// Load https://www.irctc.co.in/
		WebDriverManager.chromedriver().setup(); 
		ChromeDriver driver=new ChromeDriver(); 
		driver.get("https://www.irctc.co.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//Click on OK button in the dialog 
		driver.findElement(By.xpath("//button [text() = 'OK']")).click();
			
		//Click on FLIGHTS link
		driver.findElement(By.xpath("//span [@class = 'allcircle circleone']")).click();
		Set<String> allWindowsHandle = driver.getWindowHandles();
		List <String> allWindowsHandleList = new ArrayList <String>(allWindowsHandle);
			 
		//Switch to the Second Browser
		driver.switchTo().window(allWindowsHandleList.get(1));
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class ='izooto-optin--cta izooto-optin--cta-later']"))));
			 
		Thread.sleep(2000);		 
			 
		// handle pop up in new window
		driver.findElement(By.xpath("//button[text()='Later']")).click();
			
		//Print the customer care email id
		driver.findElement(By.xpath("//a [text() = 'Contact Us']")).click();
		String text = driver.findElement(By.xpath("(//a[@class='dropdown-item'])[3]")).getText();			
		//String text = driver.findElement((By.xpath("//a [contains (text() , '@irctc.co.in')]" ))).getText();
		System.out.println("The Customer care email id is : " + text);
			
		//Close the window
		WebDriver window = driver.switchTo().window(allWindowsHandleList.get(0));
		window.close();
					
		//Close all browser
		Thread.sleep(5000);
		driver.quit();
	}
}
