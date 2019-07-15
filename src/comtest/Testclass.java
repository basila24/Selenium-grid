package comtest;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class Testclass {
	
	public static BigDecimal getEvalAmt(int inputExp,BigDecimal inputAmt){
	    /* Function to convert inputAmt(integral value) to an equivalent decimal amount 
	                with inputExp as the exponent */
		
		/*inputExp=2;
		inputAmt=null;*/
	                Integer Exp  = inputExp;
	                BigDecimal Amt = inputAmt;
	                BigDecimal EvalAmt = Amt.divide(new BigDecimal(10).pow(Exp));
	                
	                return EvalAmt;
	                }


	public static void main(String[] args) throws InterruptedException {
		
		try {
			String s="DX362_GFN_AR_0201_000315_20190709_010137";
			String []s1=s.split("AR");
			System.out.println(s1[1].toString().substring(1));
			
			
			Testclass.getEvalAmt(2, new BigDecimal("null"));
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		/*System.setProperty("webdriver.chrome.driver", "D:\\eclipse\\Test\\driver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://czprgepyxqa.epyx.co.uk");

		ArrayList<String> w = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(w.get(1));

	
		
		int size=driver.findElements(By.xpath("//table[@class='pq-grid-table pq-td-border-right pq-td-border-bottom pq-wrap ']//tr")).size();
		
		
		for(int i=1;i<=size;i++){
			
			
			if(driver.findElement(By.xpath("//table[@class='pq-grid-table pq-td-border-right pq-td-border-bottom pq-wrap ']//tr["+i+"]/td")).getText().equalsIgnoreCase("Automated Supplier Tariff")){
				
				driver.findElement(By.xpath("//table[@class='pq-grid-table pq-td-border-right pq-td-border-bottom pq-wrap ']//tr["+i+"]/td")).click();
			}else{
				
				driver.findElement(By.cssSelector(".bottom-btn.pq-sb-btn")).click();
			}
		}
		

		driver.findElement(By.xpath("(//*[text()='Automated Supplier Tariff'])[1]")).click();
		
		//.bottom-btn.pq-sb-btn
		
		//pq-grid-table pq-td-border-right pq-td-border-bottom pq-wrap 
*/
	}

}
