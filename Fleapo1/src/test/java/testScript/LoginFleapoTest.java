package testScript;

import org.testng.annotations.Test;

import com.pageobjects.FleapoLoginPage;
import com.pageobjects.FleapoHomePage;
import com.testbase.TestBase;
import org.testng.annotations.Test;


public class LoginFleapoTest extends TestBase
{
	FleapoHomePage home;

	static FleapoLoginPage login;
	@Test(priority=1)
	public void loginFleapo() throws InterruptedException 
	{
		login = new FleapoLoginPage();
		home = new FleapoHomePage();
//		
//		login.getLogin2(prop.getProperty("mobileNumber1"), prop.getProperty("num"));
//		
//		home.getPageName(prop.getProperty("pName"));
//
//		home.introMessage(prop.getProperty("intro"));
//		
//		home.addSocialLink(prop.getProperty("links"));
//		
//		home.enableMessaging();
//		
//		home.monetizationSetting();
		
		home.stripeExpress(prop.getProperty("otp1"));
	}
	
	
}