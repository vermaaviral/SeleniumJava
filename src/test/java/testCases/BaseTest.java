package testCases;

import com.Pages.BasePage;
import com.utils.*;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {

    protected static YamlReader data=new YamlReader("src/resources/TestData.yaml");
    protected static String env=ConfigReader.getInstance().getProp("env");
    public BasePage page;

    @BeforeMethod(alwaysRun=true)
    @Parameters(value={"browser"})
    public void setUp(@Optional("chrome") String browser) {
        DriverFactory.getInstance().setDriver(BrowserFactory.getInstance().getBrowser(browser));
        WebDriver driver = DriverFactory.getInstance().getDriver();
        page=new BasePage(driver);
        driver.get(data.getValue(env+".url"));
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun=true)
    public void tearDown(ITestResult result) {
        ExtentFactory.getInstance().removeExtent();
        DriverFactory.getInstance().getDriver().quit();
        DriverFactory.getInstance().removeDriver();
    }

}
