package org.iit.mmp.patientmodule.pages;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ViewInformationPage {

	WebDriver driver;

	public ViewInformationPage(WebDriver d) {
		driver = d;
	}

	By viewInformationLink = By.xpath("//span[contains(text(),'Information')]");
	// h3[@class='panel-title']

	By informationText = By.xpath("//h3[@class='panel-title']");
	// h3[@class='page-header']

	By userDisplay = By.xpath("//h3[@class='page-header']");

	By pageText = By.xpath("//div[@class='panel-title']");

	public boolean informationLink() {
		driver.findElement(viewInformationLink).click();
		return true;
	}

	public boolean informationText() throws Exception {
		if (driver.findElement(informationText).isDisplayed()) {
			System.out.println("Information Text Displayed");
			return true;
		}
		return false;
	}

	public boolean usernameDisplay() {

		if (driver.findElement(userDisplay).isDisplayed()) {
			System.out.println("Username  Text Displayed");
			return true;
		}
		return false;
	}

	public boolean pageContains(String expText) {
		String actText = driver.findElement(pageText).getText();
		if (actText.contains(expText)) {
			// System.out.println(actText);
			return true;

		} else {
			return false;
		}

	}

	public boolean isUserCorrect(String expUsername) {

		if (driver.findElement(userDisplay).getText().equalsIgnoreCase(expUsername)) {
			System.out.println("Username  Matched");
			return true;
		}
		return false;
	}

	public boolean viewInfoPageText(String expFullText) {
		boolean results = true;
		String actText = driver.findElement(pageText).getText();
		String[] textList = actText.split("\n");
		System.out.println(textList.length);
		String actFullText = "";
		for (int i = 0; i < textList.length; i++) {

			actFullText = actFullText + " " + textList[i].trim();
			actFullText = actFullText.trim();

		}
		String[] expFullTextList = expFullText.split(" ");
		String[] actFullTextList = actFullText.split(" ");
		System.out.println(expFullTextList.length);
		System.out.println(actFullTextList.length);
		results = Arrays.equals(expFullTextList, actFullTextList);
		System.out.println(results);
		return results;
	}

}
