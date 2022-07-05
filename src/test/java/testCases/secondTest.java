package testCases;

import org.testng.annotations.Test;

public class secondTest extends Base{

    @Test(groups ={"Smoke"})
    public void first(){
        System.out.println("Test3");
    }
}
