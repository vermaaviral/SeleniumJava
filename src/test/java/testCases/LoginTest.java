package testCases;

import com.Pages.AccountPage;
import com.utils.DriverFactory;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test(groups ={"Regression", "Smoke"})
    public void validateLogin() {
        AccountPage accountPage = new AccountPage(DriverFactory.getInstance().getDriver());
        accountPage.login(data.getValue(env+".username"),data.getValue(env+".password"));
        accountPage.logout();
    }

    @Test(groups ={"Regression", "Smoke"})
    public void invalidLogin() {
        AccountPage accountPage = new AccountPage(DriverFactory.getInstance().getDriver());
        accountPage.login(data.getValue(env+".username"),data.getValue(env+".invalidPassword"));
        accountPage.verifyInvalidLoginText("Warning: No match for E-Mail Address and/or Password.");
    }
}
