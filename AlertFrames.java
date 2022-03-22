package week4.assignments;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertFrames 
{
	public static void main(String[] args) throws InterruptedException 
	{
		// TODO Auto-generated method stub
		// Load https://www.irctc.co.in/
		WebDriverManager.chromedriver().setup(); 
		ChromeDriver driver=new ChromeDriver(); 
		driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_confirm");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		// click try it button in frame
		driver.switchTo().frame("iframeResult");
		driver.findElement(By.xpath("//button[text()='Try it']")).click();

		// click alert ok
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert message is - " + alert.getText());
		alert.accept();
		System.out.println("Alert accepted");

		// verify alert clicked
		System.out.println("After clicking Alert this message displays - " + driver.findElement(By.xpath("//p[@id='demo']")).getText());

		// close browser
		Thread.sleep(3000);
		driver.quit();		
	}
}
