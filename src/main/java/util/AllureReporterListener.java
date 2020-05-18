package util;

import io.qameta.allure.Attachment;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

public class AllureReporterListener implements IHookable {
	TakesScreenshot drivername;
    public void run(IHookCallBack callBack, ITestResult testResult) {

        callBack.runTestMethod(testResult);
            try {       
                takeScreenShot(testResult.getMethod().getMethodName(),drivername);
            } catch (IOException e) {
                e.printStackTrace();
            }
        
    }


    @Attachment(value = "Failure in method {0}", type = "image/png")
    private byte[] takeScreenShot(String methodName,TakesScreenshot drivername) throws IOException {

    	 return drivername.getScreenshotAs(OutputType.BYTES);    }
}