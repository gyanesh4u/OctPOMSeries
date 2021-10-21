package tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import utils.Constants;
import utils.ExcelUtil;

public class RegisterationPageTest extends BaseTest{

	@BeforeClass
	public void regSetup() {
		regPage=loginPage.navigateToRegisterPage();
	}
	public String getRandomEmail() {
		Random random=new Random();
		String email="testautomation"+random.nextInt(5000)+"@gmail.com";
		System.out.println(email);
		return email;
	}
	@DataProvider
	public Object[][] getRegTestData(){
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}
	@Test(dataProvider = "getRegTestData")
	public void registerationTest(String firstName,String lastName,String telephone,String password,String subscribe) {
		Assert.assertTrue(regPage.accountRegisteration(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
	}
}
