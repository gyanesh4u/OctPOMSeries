package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import utils.Constants;
import utils.ExcelUtil;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void accPageTitleTest() {
		String title = accPage.getAccPageTitle();
		System.out.println("acc page title is:" + title);
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void accPageHeaderTest() {
		String header = accPage.getAccPageHeader();
		System.out.println("acc page header is:" + header);
		Assert.assertEquals(header, Constants.PAGE_HEADER);
	}

	@Test(priority = 3)
	public void accSectionsListTest() {
		List<String> actAccSecList = accPage.getAccountSectionsList();
		System.out.println("actual sections:" + actAccSecList);
		Assert.assertEquals(actAccSecList, Constants.EXPECTED_ACC_SEC_LIST);
	}

	@Test(priority = 4)
	public void logoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] { { "Macbook Pro" }, { "Macbook Air" }, { "Apple" } };
	}

	@Test(priority = 5, dataProvider = "getSearchData")
	public void searchTest(String productName) {
		resultsPage = accPage.doSearch(productName);
		String resultHeader = resultsPage.getSearchPageHeader();
		System.out.println("result header is :" + resultHeader);
		Assert.assertTrue(resultHeader.contains(productName));
	}

	@DataProvider
	public Object[][] getProductSelectData() {
		return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);

	}

	@Test(priority = 6, dataProvider = "getProductSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		resultsPage = accPage.doSearch(productName);
		productInfoPage = resultsPage.selectProduct(mainProductName);
		String header = productInfoPage.getProductHeaderTest();
		System.out.println("product header:" + header);
		Assert.assertEquals(header, mainProductName);
	}
}
