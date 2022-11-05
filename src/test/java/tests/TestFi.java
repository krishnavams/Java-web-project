package tests;

import base.BaseClass;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestFi extends BaseClass {
    @Test
    public void testing(){
        Reporter.log("Opened");
    }
}
