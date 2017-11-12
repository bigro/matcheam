package matcheam;

import matcheam.support.TestDataSource;
import org.junit.BeforeClass;

public abstract class AbstractTest {
    protected static TestDataSource testDataSource;

    @BeforeClass
    public static void setTestDataSource() throws Exception {
        testDataSource = new TestDataSource();
    }
}
