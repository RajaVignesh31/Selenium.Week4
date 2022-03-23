package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ToyWithTables 
{
	public static void main(String[] args) throws InterruptedException 
	{
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup(); 
		ChromeDriver driver=new ChromeDriver(); 
		driver.get("http://www.leafground.com/pages/table.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		WebElement elementTable = driver.findElement(By.id("table_id"));
		
		//Get the number of rows
		List<WebElement> rows = elementTable.findElements(By.tagName("tr"));
		System.out.println("The total number of rows in the table : " + rows.size());
		
		//Get the number of columns
		WebElement row = rows.get(1);
		List<WebElement> cols = row.findElements(By.tagName("td"));
		System.out.println("The total number of columns in the table : " + cols.size());
		
		//Print all the data of the Table	
		for(int i=1;i<rows.size();i++)
		{
			WebElement eachRow = rows.get(i);
			List<WebElement> allCols = eachRow.findElements(By.tagName("td"));
			for(int j=0;j<allCols.size();j++)
			{
				WebElement eachCol = allCols.get(j);
				System.out.print(eachCol.getText()+ " ");
			}
			System.out.println();
		}
		
		//Get the progress value of Learn to interact with Elements
		String progessValue = driver.findElement(By.xpath("//table[@id='table_id']/tbody/tr[3]/td[2]")).getText();
		System.out.println("Progress % = "+progessValue);
		
		//Check the column, store in list
		List<Integer> list1 = new ArrayList<Integer>();
		for(int i =1; i<rows.size();i++)
		{
			WebElement eachRow1 = rows.get(i);
			WebElement tdColData = eachRow1.findElements(By.tagName("td")).get(1);
			String text = tdColData.getText();
			String replace = text.replace("%", "");
			int parseInt = Integer.parseInt(replace);
			list1.add(parseInt);
		}	
		
		int min = Collections.min(list1);
			
		//Check the vital task for the least completed progress
		driver.findElement(By.xpath("//td[text()='"+min+"%']/following-sibling::td/input")).click();
		
		//Check the vital task for the least completed progress
		//driver.findElement(By.xpath("//table[@id='table_id']/tbody/tr[4]/td[2]/following::input")).click();
		
		//Close Driver
		Thread.sleep(5000);
	}
}
