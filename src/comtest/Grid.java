package comtest;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class Grid {
	WebDriver driver = null;
	
	@Test
	public void test1() throws MalformedURLException{
		
		

		
		
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		
		String ipAddress = "172.29.10.131";
		
		driver = new RemoteWebDriver(new URL("http://"+ipAddress+":4444/wd/hub"), capability);
		
		driver.get("https://czprgepyxqa.epyx.co.uk");

	

	System.out.println(driver.getTitle());
	}
	
	@Test
	public void test2() throws MalformedURLException{
		
		

		
		
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		
		String ipAddress = "172.29.10.131";
		
		driver = new RemoteWebDriver(new URL("http://"+ipAddress+":4444/wd/hub"), capability);
		
		driver.get("https://czprgepyxqa.epyx.co.uk");

	

	System.out.println(driver.getTitle());
	}
	
	@Test
	public void test3() throws MalformedURLException{
		
		

		
		
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		
		String ipAddress = "172.29.10.131";
		
		driver = new RemoteWebDriver(new URL("http://"+ipAddress+":4444/wd/hub"), capability);
		
		driver.get("https://czprgepyxqa.epyx.co.uk");

	

	System.out.println(driver.getTitle());
	}
}
