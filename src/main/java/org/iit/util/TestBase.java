
package org.iit.util;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	protected WebDriver driver;
	public Logger log;
	public String currDirectory = new File(System.getProperty("user.dir")).getAbsolutePath();
	
	@BeforeTest
	public void instantiateDriver()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		 
		
	}
	@BeforeClass
	public void setupLogger() throws Exception {
		System.out.println(currDirectory);
		String logpath = currDirectory + File.separator + "logs" + File.separator;

		log = Logger.getLogger(logpath  + "MMP_Testing_result.txt");

		FileHandler handler = new FileHandler(logpath + "MMP_Testing_result.txt");
		log.addHandler(handler);
		log.setLevel(Level.ALL);
		log.setUseParentHandlers(false);
		handler.setFormatter(new SimpleFormatter());
	}
	@AfterClass
	public void finalMethod() {
		driver.quit();
		log.getHandlers()[0].close();
		
	}
}
