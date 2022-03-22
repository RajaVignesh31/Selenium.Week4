package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SortTable 
{
	public static void main(String[] args) throws InterruptedException 
	{
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup(); 
		ChromeDriver driver=new ChromeDriver(); 
		driver.get("http://www.leafground.com/pages/sorttable.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		WebElement elementTable = driver.findElement(By.id("table_id_wrapper"));
		
		//Get the rows
		List<WebElement> rows = elementTable.findElements(By.tagName("tr"));
		System.out.println("Total Rows: "+rows.size());
		
		//Get the columns
		WebElement row = rows.get(1);
		List<WebElement> cols = row.findElements(By.tagName("td"));
		System.out.println("Total Cols: "+cols.size());
		
		//Check the names, store in list and sort
		List<String> list1 = new ArrayList<String>();
		for(int i =1; i<rows.size();i++)
		{
			WebElement eachRow1 = rows.get(i);
			WebElement tdColData = eachRow1.findElements(By.tagName("td")).get(1);
			list1.add(tdColData.getText());
			list1.sort(null);
		}		
		
		System.out.println(list1);
		
		//Click on the name header
		driver.findElement(By.xpath("//th[text()='Name']")).click();
		
		//Again get the names and store in a list
		List<String> list2 = new ArrayList<String>();
		for(int i =1; i<rows.size();i++)
		{
			WebElement eachRow1 = rows.get(i);
			WebElement tdColData = eachRow1.findElements(By.tagName("td")).get(1);
			list2.add(tdColData.getText());
		}	
		
		System.out.println(list2);
		
		//Compare both the list
		if(list1.equals(list2))
		{
			System.out.println("Lists are equal");
		}
		else
		{
			System.out.println("Lists are not equal");
		}
		
		//Close Driver
		Thread.sleep(5000);
	}
}
