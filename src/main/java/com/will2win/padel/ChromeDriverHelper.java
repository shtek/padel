package com.will2win.padel;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class ChromeDriverHelper {
    public ChromeDriverHelper() {
    }
   // private static String chromeDriverLocation = System.getProperty("user.dir");//+ "\\classes\\drivers\\chromedriver.exe";
   //this is working while in IntelliJ but this will not work from jar file
    //will need to fall into driver folder

  //  private static String chromeDriverLocation = "target/classes/drivers/Windows/chromedriver.exe";
    public static boolean isWindowsOperatingSystem() {
        String os = System.getProperty("os.name");
        return os.contains("Windows");

    }
    public static ChromeDriver build() {
       // System.out.println("usr dirr!!!!!!!!!!!!!!!!11" + System.getProperty("user.dir"));
        if (isWindowsOperatingSystem()) {
            System.setProperty("webdriver.chrome.driver", "target/classes/drivers/Windows/chromedriver.exe");
        }
        else
        {
            System.setProperty("webdriver.chrome.driver", "classes/drivers/Linux/chromedriver");
        }

       // System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
        ChromeDriver driver = new ChromeDriver();
        return driver;
    }
}
