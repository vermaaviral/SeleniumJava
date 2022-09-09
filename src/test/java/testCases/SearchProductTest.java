package testCases;

import com.Pages.AccountPage;
import com.Pages.HomePage;
import com.Pages.SearchProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchProductTest extends BaseTest {

    @Test(groups ={"Regression"})
    public void validateSearchedProductList() {
        page.getInstance(HomePage.class).searchProduct(data.getValue(env+".searchProduct"));
        Assert.assertEquals(page.getInstance(SearchProductPage.class).getSearchedProductName(), data.getValue(env+".searchProduct"));
        page.getInstance(SearchProductPage.class).matchSearchedProduct(data.getValue(env+".searchProduct"));
    }
}
