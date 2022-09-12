package testCases;

import com.Pages.AccountPage;
import com.Pages.HomePage;
import com.Pages.SearchProductPage;
import com.utils.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchProductTest extends BaseTest {

    @Test(groups ={"Regression"})
    public void validateSearchedProductList() {
        HomePage homePage =new HomePage(DriverFactory.getInstance().getDriver());
        SearchProductPage searchProductPage =homePage.searchProduct(data.getValue(env+".searchProduct"));
        Assert.assertEquals(searchProductPage.getSearchedProductName(), data.getValue(env+".searchProduct"));
        searchProductPage.matchSearchedProduct(data.getValue(env+".searchProduct"));
    }
}
