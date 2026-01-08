package ge.tbc.testautomation.Util;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import java.lang.reflect.Method;
public class RetryAnalyzer implements IRetryAnalyzer {
    int count;
    @Override
    public boolean retry(ITestResult iTestResult) {
        Method method = iTestResult.getMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(RetryCount.class)) {
            RetryCount retryAnnotation = method.getAnnotation(RetryCount.class);
            if(count < retryAnnotation.count()) {
                count++;
                return true;
            }
        }
        System.out.println("right before max retries are reached");
        return false;
    }
}