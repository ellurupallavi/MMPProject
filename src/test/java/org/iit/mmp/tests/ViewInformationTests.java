package org.iit.mmp.tests;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ViewInformationPage;
import org.iit.util.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class ViewInformationTests extends TestBase {

	HelperClass helper;

	String expFullText = "Manage My Patient (MMP) is a medical practice management solution that"
			+ " boosts productivity by automating the day-to-day tasks that can slow an office"
			+ " manager down. Central delivers much more than medical billing software. "
			+ "Sure, it has the tools to help generate cleaner claims and reduce denials, "
			+ "but our easy-to-use practice management software also streamlines your"
			+ " workflow to deliver seamless handoffs across departments."
			+ "Manage My Patient (MMP) becomes your practice’s command center,"
			+ "delivering robust, real-time analytics through customizable reports and "
			+ "dashboards to ensure you know how your business is performing on the metrics" 
			+ " that matter most.";

//	public Logger log;
//	public String currDirectory = new File(System.getProperty("user.dir")).getAbsolutePath();

	ViewInformationPage viewInfoPageObj;

	@BeforeClass
	public void setupLogger() throws Exception {
		System.out.println(currDirectory);
		String logpath = currDirectory + File.separator + "logs" + File.separator;

		log = Logger.getLogger(logpath + ViewInformationTests.class.getName() + "_result.txt");

		FileHandler handler = new FileHandler(logpath + ViewInformationTests.class.getName() + 
				"_result.txt");
		log.addHandler(handler);
		log.setLevel(Level.ALL);
		log.setUseParentHandlers(false);
		handler.setFormatter(new SimpleFormatter());
	}

	@Parameters({ "url", "uname", "pword", "expText" })
	@Test(description = "US_004, View Information", groups = { "sanity", "regression", "UI", 
			"patientmodule","US_004" })
	public void viewInformation(String url, String uname, String pword, String expText) {
		try {
			helper = new HelperClass(driver);
			helper.launchWebPage(url);
			helper.login(uname, pword);
			helper.navigateToAModule("Information");
			viewInfoPageObj = new ViewInformationPage(driver);

			log.info("Checking Information Link");
			boolean rflag = viewInfoPageObj.informationLink();
			Assert.assertTrue(rflag, "Link not Found");

			log.info("Checking Information Text");
			rflag = viewInfoPageObj.informationText();
			Assert.assertTrue(rflag, "Text Not Displayed");
			
			log.info("Looking for Username Display");
			rflag = viewInfoPageObj.usernameDisplay();
			Assert.assertTrue(rflag, "Username Not Displayed");

			log.info("Comparing the Pagetext with expected Text");
			rflag = viewInfoPageObj.viewInfoPageText(expFullText);
			Assert.assertTrue(rflag, "Expected text not matched with Actual Text");

			log.info("Looking for Actual Username Matched with Expected Username");
			rflag = viewInfoPageObj.isUserCorrect(uname);
			Assert.assertTrue(rflag, "Actual UserName is not matching with Expexted");

			log.info("Looking in the Pagetext for expected Text Display");
			rflag = viewInfoPageObj.pageContains(expText);
			log.info(rflag ? "PASS" : "FAIL");

			Assert.assertTrue(rflag, "Expected Text not displayed");
		} catch (Exception e) {
			log.severe(e.toString());
		}

	}

	
	@Test(description = "US_999, Closing Log Handler", groups = { "sanity", "regression", "UI", 
			"patientmodule","US_999" })
	public void finalMethod() {
		log.getHandlers()[0].close();
	}
}
