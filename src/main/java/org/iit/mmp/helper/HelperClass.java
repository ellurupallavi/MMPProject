package org.iit.mmp.helper;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperClass {
	WebDriver driver;
	public HelperClass(WebDriver driver)
	{
		this.driver = driver;
	}
	public WebDriver switchToAFrameAvailable(String frame,int timeInSecs)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeInSecs);
		driver =wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
		return driver;
	}
	public void launchWebPage(String url)
	{
		driver.get(url);
	}
	public void navigateToAModule(String moduleName)
	{
		driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]")).click();			
	}
	public void captureScreenshot(String tc_Name) throws IOException
	{
		System.out.println("Capturing the Screenshot for testcase" + tc_Name);
		TakesScreenshot tsh = (TakesScreenshot)driver;
		File srcFile = tsh.getScreenshotAs(OutputType.FILE);
		String destPath = System.getProperty("user.dir")+"\\screenshnots\\"+tc_Name+Calendar.getInstance().getTimeInMillis()+".jpg";
		File destFile = new File(destPath);
		FileUtils.copyFile(srcFile, destFile);
		System.out.println("Exiting the Screenshot method");
		
	}
	public void login(String uName,String pWord)
	{
		driver.findElement(By.id("username")).sendKeys(uName);
		driver.findElement(By.id("password")).sendKeys(pWord);
		driver.findElement(By.name("submit")).click();
	}
	public void adminLogin(String uName,String pWord)
	{
		  driver.findElement(By.id("username")).sendKeys(uName);
		  driver.findElement(By.id("password")).sendKeys(pWord);
		  driver.findElement(By.name("admin")).click();
	}
	
	
}
