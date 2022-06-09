package com.will2win.padel;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ChromeDriverHelper {
    public ChromeDriverHelper() {
    }

   // private static String chromeDriverLocation = System.getProperty("user.dir");//+ "\\classes\\drivers\\chromedriver.exe";
   //this is working while in IntelliJ but this will not work from jar file
    //will need to fall into driver folder

    private static String chromeDriverLocationWindows = "C:/Users/shtek/padel/chromedriver.exe";
    //something for linux
    public static boolean isWindowsOperatingSystem() {
        String os = System.getProperty("os.name");
        return os.contains("Windows");

    }
    public static ChromeDriver build(String chromeDriverLocation) {

      System.out.println("usr dirr!!!!!!!!!!!!!!!!11" + System.getProperty("user.dir"));

     //   if (isWindowsOperatingSystem()) {
            System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
    //    }
    //    else
     //   {
    //        System.setProperty("webdriver.chrome.driver", "classes/drivers/Linux/chromedriver");
      //  }

       // System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
      ChromeDriver driver = new ChromeDriver();
      //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
}
