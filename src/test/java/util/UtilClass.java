package util;

import org.testng.annotations.DataProvider;

public class UtilClass {

    @DataProvider(name = "loginTest")
   public Object[][] dataProvider(){
      return new Object[][]{{"username1", "abc@123"},
              {"username2","bcd@123"}
      };
    }
}
