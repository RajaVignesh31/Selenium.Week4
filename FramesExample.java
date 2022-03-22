package week4.assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesExample 
{
	public static void main(String[] args) throws InterruptedException 
	{
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		String text = driver.findElement(By.xpath("//h1[@class='breadcrumb-item']")).getText();
		System.out.println(text);
		
		driver.switchTo().frame("frame1");
		
		driver.findElement(By.xpath("//b[text()='Topic :']/following-sibling::input")).sendKeys("This is frame 1");
		
		driver.switchTo().frame("frame3");
		
		driver.findElement(By.xpath("//input [@id='a']")).click();
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("frame2");		
		
		Select dd = new Select(driver.findElement(By.xpath("//select[@id='animals']")));
		dd.selectByIndex(1);
		
		System.out.println("All frames completed");
		
		Thread.sleep(5000);
		driver.close();
	}
}
