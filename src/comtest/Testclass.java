package comtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testclass {
	
	
	public static void main(String[] args) {
		
		
		System.setProperty("webdriver.chrome.driver", "D:\\eclipse\\Test\\driver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("http://extmbasith:Basila@03@czprgtcbpt.efn.ccs.cz/gfnlaunch/");
		driver.findElement(By.id("MainContent_grdServers_imgContent_0")).click();
		
		System.out.println("passed");
		
	
	}

}
	