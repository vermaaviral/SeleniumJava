package testCases;

import com.Pages.AccountPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test(groups ={"Regression", "Smoke"})
    public void validateLogin() {
        page.getInstance(AccountPage.class).login(data.getValue(env+".username"),data.getValue(env+".password"));
        page.getInstance(AccountPage.class).logout();
    }

    @Test(groups ={"Regression", "Smoke"})
    public void invalidLogin() {
        page.getInstance(AccountPage.class).login(data.getValue(env+".username"),data.getValue(env+".invalidPassword"));
        page.getInstance(AccountPage.class).verifyInvalidLoginText("Warning: No match for E-Mail Address and/or Password.");
    }
}
