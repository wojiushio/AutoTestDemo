package util;

import java.io.IOException;

import io.qameta.allure.Attachment;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestFailListener extends TestListenerAdapter {
	   
    public void onTestFailure(ITestResult result,TakesScreenshot drivername) {
    
    }

    @Attachment(value = "Failure in method {0}", type = "image/png")
    private byte[] takeScreenShot(String methodName,TakesScreenshot drivername) throws IOException {

        return drivername.getScreenshotAs(OutputType.BYTES);
    }

}