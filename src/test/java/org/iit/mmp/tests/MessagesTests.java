package org.iit.mmp.tests;

import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.MessagesPage;
import org.iit.util.TestBase;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MessagesTests extends TestBase{

	MessagesPage messagesPageObj;
	HelperClass helperObj;

	@Parameters({"patientUrl","reason","subject","adminUrl","uName","pwd","uNameAdmin","pwdAdmin"})
	@Test(description="US_005, View Information",groups={"sanity","regression","UI","patientmodule","US_004"})
	public void validateMessages(String patientUrl,String reason,String subject,
			String adminUrl,String uName,String pwd, String uNameAdmin,String pwdAdmin) {
		boolean rflag = false;
		helperObj = new HelperClass(driver);
		helperObj.launchWebPage(patientUrl);
		helperObj.login(uName,pwd);
		helperObj.navigateToAModule("Messages");
		messagesPageObj = new MessagesPage(driver);
		SoftAssert sAssert = new SoftAssert();
		
		log.info("Entering into sendingMessages");
		rflag = messagesPageObj.sendingMessages(reason, subject);
		sAssert.assertTrue(rflag, "Unable To Send Messages");
		
		log.info("Clicking On Alert Messages");
		rflag = messagesPageObj.readSuccessMsg();
		sAssert.assertTrue(rflag, "Unable To Switch From Alert Window");
		
		helperObj.launchWebPage(adminUrl);
		helperObj.adminLogin(uNameAdmin, pwdAdmin);
		helperObj.navigateToAModule("Messages");
		
		log.info("Validating send message in admin module");
		rflag = messagesPageObj.checkMessagesinAdminModule(reason, subject, "");
		sAssert.assertTrue(rflag, "Send message is not matching");
		
		sAssert.assertAll();
		
	}

	
}
