package org.iit.mmp.patientmodule.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MessagesPage {
	WebDriver driver;
	
	
	By messageTab = By.xpath("//span[contains(text(),'Messages')]");
	By contactTextBox = By.xpath("//input[@id='subject']");
	By subjTextBox = By.xpath("//textarea[@id='message']");
	By sendBtn = By.xpath("//input[@value='Send']");
	By logoutTab = By.xpath("//span[contains(text(),'Logout')]");
	
	//http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/login.php
	By patientNameText = By.xpath("//tr[2]//td[1]//b[1]");
	By reasonText = By.xpath("//tr[2]//td[2]//b[1]");
	By dateText = By.xpath("//tr[2]//td[3]//b[1]");
	By subjectText = By.xpath("//tr[3]//td[2]");
	
	
	public MessagesPage(WebDriver driver)
	{
		this.driver = driver;
		
	}
	
	public boolean sendingMessages(String symp,String subject) {
		
		driver.findElement(contactTextBox).sendKeys(symp);
		driver.findElement(subjTextBox).sendKeys(subject);
		driver.findElement(sendBtn).click();
		return true;
	}
	public boolean readSuccessMsg()
	{
		Alert alrt = driver.switchTo().alert();
		String actual = alrt.getText();
		System.out.println(actual);
		alrt.accept();
		return true;

	}
	public boolean checkMessagesinAdminModule(String reason, String subject, String date){
		
		driver.findElement(reasonText).getText();
		driver.findElement(subjectText).getText();
		
		if(reason.equalsIgnoreCase(driver.findElement(reasonText).getText()) &&
				subject.equalsIgnoreCase(driver.findElement(subjectText).getText())) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
	
}
