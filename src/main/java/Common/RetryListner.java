package Common;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListner implements IRetryAnalyzer {

	private int maxRetryCount = 0;
    private int currentRetryCount = 0;

    public boolean retry(ITestResult result) {
        if (currentRetryCount < maxRetryCount) {
        	System.out.println("Retry count : "+currentRetryCount);
            currentRetryCount++;
            return true;
        }
        return false;
    }
}
