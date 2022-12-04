package com.nopecommerce.utility;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.nopcommerce.base.Base;
import com.nopecommerce.actiondriver.Action;

public class Listener extends ExtentReportManager implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
		tests.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			getTest().log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " - Test Case PASSED", ExtentColor.GREEN));
		}
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
				getTest().log(Status.FAIL,
						MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
				getTest().log(Status.FAIL,
						MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
				String imgPath = Action.screenShot(Base.getDriver(), result.getName());
			
				getTest().fail("ScreenShot is Attached", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			getTest().log(Status.SKIP, "Skipped Test case is: " + result.getName());
		}
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		
	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		
		
	}

}
