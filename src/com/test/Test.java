package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
	
	
	public static void main(String[] args) {
		
		
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("http://extmbasith:Basila@03@czprgtcbpt.efn.ccs.cz/gfnlaunch/");
		driver.findElement(By.id("MainContent_grdServers_imgContent_0ooooo")).click();
		
		System.out.println("Passvcbvcsdbvjfbvn");
	}

}
