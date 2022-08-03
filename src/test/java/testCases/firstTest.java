package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class firstTest extends BaseTest{

    @Test(groups ={"Regression"})
    public void first(){
        System.out.println("Test1");
    }

    @Test(groups ={"Regression","Smoke"})
    public void second(){
        System.out.println("Test2");
    }

}
