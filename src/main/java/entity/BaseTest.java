package entity;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.internal.ConstructorOrMethod;

import util.ToolUt;



/**
 * 测试基类
 */
public class BaseTest implements IHookable {
	TakesScreenshot drivername;
	ChromeDriver driver = null;

 
 
	public ChromeDriver getDriver() {
		return driver;
	}
	public void setDriver(ChromeDriver driver) {
		this.driver = driver;
	}
	public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
        //从iTestResult中获取method    
        ConstructorOrMethod method = iTestResult.getMethod().getConstructorOrMethod();
    
        String name = method.getName();
        
        System.out.println("测试method是 "+name);
        System.out.println("开始执行~");
        iHookCallBack.runTestMethod(iTestResult); 
        System.out.println("结束~");

        
    }
    @Attachment(value = "Failure in method {0}", type = "image/png")
    private byte[] takeScreenShot(String methodName,TakesScreenshot drivername) throws IOException {

    	 return drivername.getScreenshotAs(OutputType.BYTES);    }
    public TakesScreenshot getDrivername() {
 		return drivername;
 	}
 	public void setDrivername(TakesScreenshot drivername) {
 		this.drivername = drivername;
 	}
 	
 	@Step("打开网站:{0}。")
	public void goToUrl(String url) throws IOException {
		try {
			driver.navigate().to(url);
			ToolUt.takeScreenShot("打开网站:" + url + "成功", driver);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot("打开网站:" + url + "失败", driver);
			AssertJUnit.fail("打开网站:" + url + "成功");
		}

	}	
 	
 	
 	
 	/*
 	 * 以下是steps
 	 */
 	@Step("点击输入框:{0},然后输入 {1} 。")
	public void enterKey(By by,String key) throws IOException{
		
		try {
			
			driver.findElement(by).click();
			driver.findElement(by).sendKeys(key);

			Thread.sleep(2000);
			ToolUt.takeScreenShot("输入:"+key, driver);

		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot(e.toString(), driver);
			Assert.fail("输入失败，找不到元素"+by);


		}
		
	}
 	
 	

	@Step("点击控件:{0} 。")
	public void click(By by) throws IOException{
		
		try {
			driver.findElement(by).click();
			Thread.sleep(2000);
			ToolUt.takeScreenShot("点击控件::"+by, driver);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot(e.toString(), driver);
			Assert.fail("点击失败，找不到元素"+by);

		}
		
	}
	


	@Step("验证元素:{0} 是否存在 。")
	public void verifyElement(By by) throws IOException {
		try {
			AssertJUnit.assertEquals(true, ToolUt.isElementExsit(driver, by));
			ToolUt.takeScreenShot("验证成功:元素" + by + "已找到", driver);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot("验证失败:元素" + by + "找不到", driver);
			AssertJUnit.fail("验证失败:元素" + by + "找不到");
		}
	}	
	
	
	
 	
}
