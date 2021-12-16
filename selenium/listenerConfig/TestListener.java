package listenerConfig;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	@Override
	public void onFinish(ITestContext arg0) {
		System.out.println("Run after each class");
	}

	@Override
	public void onStart(ITestContext arg0) {
		System.out.println("Run before each class");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		System.out.println("Run after each testcase if failed with xx%");
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		//Mình sẽ học trong phần build framework: generate report and attach screenshot
		System.out.println("Take screenshot and attach to report");
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		System.out.println("Run before each testcase is skipped");
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("Run before each testcase is start");
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		System.out.println("Run before each testcase is end");
		
	}

}
