package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utils.Constants;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title=loginPage.getLoginPageTitle();
		System.out.println("act page title is:"+title);
		Assert.assertEquals(title,Constants.LOGIN_PAGE_TITLE);
	}
	@Test(priority = 2)
	public void loginPageUrlTest(){
		String url=loginPage.getLoginPageUrl();
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL_VALUE));
	}
	@Test(priority = 3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	@Test(priority = 4)
	public void registerLinkTest() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	@Test(priority = 5)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password").trim());
	}
	
}
