package week4.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNowFrames 
{
	public static void main(String[] args) throws InterruptedException, IOException 
	{
		 // TODO Auto-generated method stub
		 // 1. Load ServiceNow application URL
		 WebDriverManager.chromedriver().setup(); 
		 ChromeDriver driver=new ChromeDriver(); 
		 driver.get("https://dev111399.service-now.com/navpage.do");
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		 
		 // 2. Check for frame
		 WebElement frame = driver.findElement(By.id("gsft_main"));
		 driver.switchTo().frame(frame);
		 
		 // 2. Enter username 
		 driver.findElement(By.id("user_name")).sendKeys("admin");
		 
		 // 3. Enter password 
		 driver.findElement(By.id("user_password")).sendKeys("Raja@1985");
		 
		 // 4. Enter login
		 driver.findElement(By.id("sysverb_login")).click();		 
		 Thread.sleep(3000);
		 
		 // 5. Search “incident “ Filter Navigator
		 driver.findElement(By.id("filter")).sendKeys("incident",Keys.TAB);
		 
		 // 6. Click “All”
		 driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
		 
		 // Switch to frame
		 driver.switchTo().frame(0);
		 
		 // 7. Click New button
		 driver.findElement(By.id("sysverb_new")).click();
		 
		 // 8. Select a value for Caller from another window list
		 driver.findElement(By.xpath("//span[@class='icon icon-search']")).click();
		 
		 Set<String> windowHandles = driver.getWindowHandles();
		 List<String> windowHandlesList = new ArrayList<String>(windowHandles);
		 
		 // Switch to new window
		 driver.switchTo().window(windowHandlesList.get(1));
		 Thread.sleep(2000);
		 
		 // 8. Select a value for Caller 
		 driver.findElement(By.xpath("//a[@class='glide_ref_item_link']")).click();
		 
		 // Switch back to old window and frame
		 driver.switchTo().window(windowHandlesList.get(0));
		 driver.switchTo().frame(0);
		 Thread.sleep(2000);
		 
		 // 8. Enter value for short_description
		 driver.findElement(By.xpath("//input[@aria-label = 'Short description']")).sendKeys("Issue with a web page");
		 
		 // 9. Read the incident number and save it a variable
		 String text = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		 System.out.println("Number: "+ text);
		 
		 // 10. Click on Submit button
		 driver.findElement(By.id("sysverb_insert_bottom")).click();
		 Thread.sleep(2000);
		 
		 // 11. Search the same incident number in the next search screen as below
		 driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(text,Keys.ENTER);
		 
		 // 12. Verify the incident is created successful
		 String text2 = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		 
		 if(text.equals(text2))
		 {
			 System.out.println("Incident is created successfully");
		 }
		 else
		 {
			 System.out.println("Incident is not created successfully");
		 }	 
		 
		 // 12. Take snapshot of the created incident
		 File screenshot = driver.getScreenshotAs(OutputType.FILE);
		 File image = new File("./snaps/img1.jpg");
		 FileUtils.copyFile(screenshot, image);		
		 
		 // To snapshot a particular element	
		 //WebElement findElement = driver.findElement(By.xpath("//td [@class='list_decoration_cell col-control col-small col-center ']")); 
		 //File screenshot1 = findElement.getScreenshotAs(OutputType.FILE); 
		 //File image1 = new File("./snaps/img2.jpg"); 
		 //FileUtils.copyFile(screenshot1, image1);			 
		 
		 // Close the Web Page
		 Thread.sleep(2000);
		 driver.close();
	}
}
