package util;

import io.qameta.allure.Attachment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.TestListenerAdapter;

public class ToolUt extends TestListenerAdapter {

	@Attachment(value = "{0},截图", type = "image/png")
	public static byte[] takeScreenShot(String methodName,
			TakesScreenshot drivername) throws IOException {

		return drivername.getScreenshotAs(OutputType.BYTES);
	}

	@Attachment(value = "Failure in method {1}", type = "image/png")
	public static void snapshot(TakesScreenshot drivername, String filename) {
		// this method will take screen shot ,require two parameters ,one is
		// driver name, another is file name

		try {
			File scrFile = (File) drivername.getScreenshotAs(OutputType.FILE);

			String projectRoot = getProjectRoot();
			System.out.println("save snapshot path is:" + projectRoot
					+ "\\test-output\\html\\snapshot\\" + filename
					+ new Date().toString());
			FileUtils.copyFile(scrFile, new File(projectRoot
					+ "\\test-output\\html\\snapshot\\" + filename));

		} catch (IOException e) {
			System.out.println("Can't save screenshot");
			e.printStackTrace();
		} finally {
			System.out.println("screen shot finished");
		}
	}

	// 截图
	public static String getSnapshot(TakesScreenshot drivername, String path) {
		File scrFile = (File) drivername.getScreenshotAs(OutputType.FILE);
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String filename = path + "screenshot_" + formatter.format(currentTime)
				+ ".png";

		// Now you can do whatever you need to do with it, for example copy
		// somewhere
		try {
			System.out.println("save snapshot path is:" + filename);
			FileUtils.copyFile(scrFile, new File(filename));

		} catch (IOException e) {
			System.out.println("Can't save screenshot");
			e.printStackTrace();
		} finally {
			System.out.println("screen shot finished");
		}
		return filename;
	}

	public static void setChromeBrowser() {

		System.setProperty("webdriver.chrome.driver",
				"C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe");

	}

	public static String getProjectRoot() {
		File directory = new File("");//
		String courseFile = null;
		try {
			courseFile = directory.getCanonicalPath();
			System.out.println(courseFile);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return courseFile;

	}

	public static boolean isElementExsit(WebDriver driver, By locator) {
		boolean flag = false;
		try {
			WebElement element = driver.findElement(locator);
			flag = null != element;
		} catch (NoSuchElementException e) {
			System.out.println("Element:" + locator.toString()
					+ " is not exsit!");
		}
		return flag;
	}

}
