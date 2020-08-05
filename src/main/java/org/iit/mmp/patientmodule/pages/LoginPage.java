package org.iit.mmp.patientmodule.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver d) {
		driver = d;
	}
	

	By patientLogin= By.xpath("//a[contains(text(),'Patient')]");	
	By login= By.xpath("//section[@id='testimonials']//a[@class='button button-alt'][contains(text(),'Login')]");
	By userName= By.xpath("//input[@name='username']");	
	By pwd= By.xpath("//input[@name='password']");
	By subBtn= By.xpath("//input[@name='submit']");
	
	By loginSection= By.xpath("//section[@id='testimonials']");
	
    public boolean patientLogin() throws Exception{
    	driver.findElement(patientLogin).click();
    	return true;
	
}
    public boolean loginbtn() throws Exception{
    	//driver.navigate();
    	driver.findElement(login).click();
    	
    	if (driver.findElement(userName).isDisplayed()) {
    	return true;
    	}else {
    		return false;
    	}
    	
    }
    public String login(String username,String passwd ) throws Exception {
    	
    	if (!driver.findElement(userName).isDisplayed()) {
			return "Couldn't able to login";

		}
    	driver.findElement(userName).sendKeys(username);
    	driver.findElement(pwd).sendKeys(passwd);
    	driver.findElement(subBtn).click();
		return "";
    	
    }
}
