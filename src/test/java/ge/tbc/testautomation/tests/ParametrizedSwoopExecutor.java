package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import org.testng.annotations.Factory;

public class ParametrizedSwoopExecutor{
        @Factory
        public Object[] createInstances() {
            return new Object[]{
                    new ParametrizedSwoopTests2(Constants.COURSES_CATEGORY),
                    new ParametrizedSwoopTests2(Constants.DIGITAL_CATEGORY),
                    new ParametrizedSwoopTests2(Constants.DIFF_CATEGORY)
            };
        }
}