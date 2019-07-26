package comtest;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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


	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		
		/*try {
			String s="DX362_GFN_AR_0201_000315_20190709_010137";
			String []s1=s.split("AR");
			System.out.println(s1[1].toString().substring(1));
			
			
			Testclass.getEvalAmt(2, new BigDecimal("null"));
			
			int []large={5565,5,666666,56,82,6767676};
			int big=large[0];
			
			for(int i=0;i<large.length-1;i++){
				
				if(big<large[i+1]){
					
					big=large[i+1];
				}else{
					large[i]=big;
					
				}
				
			}
			System.out.println(big);
			
			int number=145; int digit=45;
			
			char c = Integer.toString(digit).charAt(0);
			 
	        //Decrementing number & checking whether it contains digit
	 
	        for (int i = number; i > 0; --i)
	        {
	            if(Integer.toString(i).indexOf(c) == -1)
	            {
	                //If 'i' doesn't contain 'c'
	 
	                System.out.println(i);
	            }
	        }
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}*/

		//System.setProperty("webdriver.chrome.driver", "D:\\eclipse\\Test\\driver\\chromedriver.exe");

		WebDriver driver = null;
		
		
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		
		String ipAddress = "172.29.10.131";
		
		driver = new RemoteWebDriver(new URL("http://"+ipAddress+":50795/wd/hub"), capability);
		
		driver.get("https://czprgepyxqa.epyx.co.uk");

	/*	ArrayList<String> w = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(w.get(1));*/

	System.out.println(driver.getTitle());
		
	
		
		//.bottom-btn.pq-sb-btn
		
		//pq-grid-table pq-td-border-right pq-td-border-bottom pq-wrap 

	}

}
