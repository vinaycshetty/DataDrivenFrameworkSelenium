package com.vs.listeners;

import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;
import com.vs.base.TestBase;
import com.vs.utility.TestUtil;

public class CustomListener extends TestBase implements ITestListener,ISuiteListener{

	public void onTestStart(ITestResult result) {
		
		test = report.startTest(result.getName().toUpperCase());
		//System.out.println(TestUtil.isTestRunable(result.getName(), excel));
		if(!TestUtil.isTestRunable(result.getName(), excel)) {
			throw new SkipException("Skipping the test "+result.getName()+" as runmode is No");
		}
		
		report.endTest(test);
		report.flush();
		
	}

	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, result.getName().toUpperCase() +" Pass");
		report.endTest(test);
		report.flush();
		
		
	}

	public void onTestFailure(ITestResult result) {

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log(" Click to see ScreenShot");
		Reporter.log(" <br>");
		try {
			TestUtil.captureScreenShot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.log(LogStatus.FAIL, result.getName().toUpperCase()+" Failed with exception : "+result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		
		
		
		Reporter.log("<a target = \"_blank\" href = "+TestUtil.screenshotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target = \\\"_blank\\\" href = "+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=100 width=100></img></a>");
		report.endTest(test);
		report.flush();
	}

	public void onTestSkipped(ITestResult result) {
		
		test.log(LogStatus.SKIP, "Test "+result.getName()+" Skipped as runmode is NO");
		report.endTest(test);
		report.flush();
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
		
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ISuite suite) {

		
		
	}

}
