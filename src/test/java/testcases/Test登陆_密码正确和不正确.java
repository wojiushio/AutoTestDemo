package testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.Issue;
import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import entity.BaseTest;
import pages.LoginPageElements;
import util.ToolUt;

public class Test登陆_密码正确和不正确 extends BaseTest {
	private StringBuffer verificationErrors = new StringBuffer();
	String folder = "test/testLogin/";
	org.openqa.selenium.chrome.ChromeDriver driver = null;

    @BeforeClass(description = "测试初始化")
	@Feature("登陆退出")
	@Description("测试初始化")
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"D:/autotest/tool/chromedriver.exe");
		driver = new org.openqa.selenium.chrome.ChromeDriver();

		this.setDrivername(driver);
		this.setDriver(driver);
		goToUrl("https://www.zentao.net/user-login.html");

		ToolUt.takeScreenShot("启动成功", driver);

	}

	/*
	 * 测试正确登录
	 */
	@Test(priority = 1, description = "测试用例_账号密码正确登录")
	@Parameters({ "username", "password" })
	@Issue("AG-2759823")
	@Feature("登陆退出")
	@Story("登陆退出")
	@Description("测试步骤:                                                                                                                "
			+ "1.打开网站                                                                                                           "                                                                                                                                                                                                                                                                                                                                                                                            
			+ "2.输入用户名                                                                                                                                                                                                                                                                                                                                                                                                       "
			+ "3.输入正确的密码                                                                                                                                                                                                                                                                                                                                                                                                                                                                         "
			+ "4.点击登录按钮，验证是否登录成功                                                                                                                                                                                                                                                                                                                                                  "
			+ "5.点击退出登录按钮，验证是否退出成功        ")
	@Severity(SeverityLevel.TRIVIAL)
	public void test_AccountLoginAndLogout(String username, String password) throws IOException  {
		try {
			enterKey(By.name(LoginPageElements.username), username);
			enterKey(By.id(LoginPageElements.pwd), password);
			click(By.id(LoginPageElements.loginBtn));
			verifyElement(By.xpath("//a[.='退出']"));
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot("系统报错:" + e.toString(), this.getDrivername());
			AssertJUnit.fail("测试不通过");
		}
	}

	/*
	 * 测试密码错误登录
	 */
	@Test(priority = 2, description = "测试用例_账号密码错误登录")
	@Parameters({ "username", "Wpassword" })
	@Issue("AG-2759823")
	@Feature("登陆退出")
	@Story("登陆退出")
	@Description("测试步骤:                                                                                                                "
			+ "1.输入用户名                                                                                                                                                                                                                                                                                                                                                                                                     "
			+ "2.输入错误的密码                                                                                                                                                                                                                                                                                                                                                                                                                                                                         "
			+ "3.点击登录按钮，验证是否登录成功                                                                                                                                                                                                                                                                                                                                                  ")
	@Severity(SeverityLevel.TRIVIAL)
	public void test_AccountLoginWithWrongPassword(String username,
			String Wpassword) throws IOException {
		try {

			enterKey(By.name(LoginPageElements.username), username);
			enterKey(By.id(LoginPageElements.pwd), Wpassword);
			click(By.id(LoginPageElements.loginBtn));
			verifyElement(By.xpath(LoginPageElements.username));
			Thread.sleep(1000);

		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot("系统报错:" + e.toString(), driver);
			AssertJUnit.fail("测试不通过");
		}
	}

	@AfterClass(description = "tearDown")
	@Feature("tearDown")
	@Description("tearDown")
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			AssertJUnit.fail(verificationErrorString);
		}
	}
	

}
