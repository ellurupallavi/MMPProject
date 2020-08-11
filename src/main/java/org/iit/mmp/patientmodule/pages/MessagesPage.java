package org.iit.mmp.patientmodule.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MessagesPage {
	WebDriver driver;

	By messageTab = By.xpath("//span[contains(text(),'Messages')]");
	By contactTextBox = By.xpath("//input[@id='subject']");
	By subjTextBox = By.xpath("//textarea[@id='message']");
	By sendBtn = By.xpath("//input[@value='Send']");
	By logoutTab = By.xpath("//span[contains(text(),'Logout')]");

	// String xPathReason = "//b[normalize-space(text())='DYNAMICTEXT']";
	// String xPathSubject = "//td[normalize-space(text())='DYNAMICTEXT']";

	By reasonPath = By.xpath("//table[@class='table']/tbody/tr/td[2]");
	// By subjectPath = By.xpath("//table[@class='table']/thead/tr/td[1]");

	public MessagesPage(WebDriver driver) {
		this.driver = driver;

	}

	public boolean sendingMessages(String reason, String subject) {
		boolean results = false;
		driver.findElement(contactTextBox).sendKeys(reason);
		driver.findElement(subjTextBox).sendKeys(subject);
		driver.findElement(sendBtn).click();
		results = true;
		return results;
	}

	public boolean readSuccessMsg(String successMsg) {
		boolean results = false;
		Alert alrt = driver.switchTo().alert();
		String actual = alrt.getText();
		System.out.println(actual);
		if(successMsg.equalsIgnoreCase(actual))
		{
			results = true;
		}
		alrt.accept();
		return results;

	}

	public boolean checkMessagesinAdminModule(String exptReason, String exptSubject, String date) {
		System.out.println("reason " + exptReason);
		System.out.println("subject " + exptSubject);
		// By reasonText = By.xpath(xPathReason.replaceAll("DYNAMICTEXT", exptReason));
		// By subjectText = By.xpath(xPathSubject.replaceAll("DYNAMICTEXT",
		// exptSubject));
		boolean results = false;

		List<WebElement> reasonList = driver.findElements(reasonPath);
		// List<WebElement> subjectList = driver.findElements(subjectPath);

//		if (driver.findElement(reasonText).isDisplayed() && driver.findElement(subjectText).isDisplayed()) {
//			results = true;
//			// return results;
//		} else {
//			// return results;
//		}
		System.out.println("Size of Reason List is :- " + reasonList.size());
//		System.out.println("Size of Subject List is :- " + subjectList.size());

		for (int i = 1; i < reasonList.size(); i = i + 2) {
			System.out.println("Each Line ::" + reasonList.get(i).getText().trim() + " ,"
					+ reasonList.get(i + 1).getText().trim());
			if (exptReason.equalsIgnoreCase(reasonList.get(i).getText().trim())
					&& exptSubject.equalsIgnoreCase(reasonList.get(i + 1).getText().trim())) {
				results = true;
				return results;

			}

		}

		return results;

	}

}
