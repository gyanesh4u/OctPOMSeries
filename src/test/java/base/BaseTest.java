package base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import factory.DriverFactory;
import pages.AccountsPage;
import pages.LoginPage;
import pages.ProductInfoPage;
import pages.RegisterationsPage;
import pages.ResultsPage;

public class BaseTest {

	public WebDriver driver;
	public Properties prop;
	public DriverFactory df;
	public LoginPage loginPage;
	public SoftAssert softAssert;
	public AccountsPage accPage;
	public ResultsPage resultsPage;
	public ProductInfoPage productInfoPage;
	public RegisterationsPage regPage;

	@Parameters({ "browser", "browserversion" })
	@BeforeTest
	public void setUp(String browserName, String browserVersion) {
		softAssert = new SoftAssert();
		df = new DriverFactory();
		prop = df.initProperties();
		if (browserName != null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserVersion", browserVersion);
		}
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
