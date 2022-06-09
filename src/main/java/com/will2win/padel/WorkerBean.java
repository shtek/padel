package com.will2win.padel;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import static com.will2win.padel.ChromeDriverHelper.isWindowsOperatingSystem;


@Service
@Configuration
@PropertySource("classpath:password.properties")
public class WorkerBean {

        public WorkerBean() {
        }
    @Value( "${password}" )
    private String password;
    @Value( "${chromeDriverLocationWindows}" )
    private   String chromeDriverLocationWindows;



        public void doWork(int dateIndex, int time) {


            String chromeDriverLocation = new String();
               if (isWindowsOperatingSystem()) {
                    chromeDriverLocation = chromeDriverLocationWindows;
               }
             ChromeDriver chromeDriver=      ChromeDriverHelper.build(chromeDriverLocation);



            int endTime = 0;
            String dateString = null;
           System.out.println("work!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + chromeDriverLocationWindows +"---");
            WebDriverWait t = new WebDriverWait(chromeDriver,Duration.ofSeconds(1));

            try {
                chromeDriver.get("https://www.openplay.co.uk/embed-my-account/947");
                chromeDriver.switchTo().frame(0);

                WebElement element = chromeDriver.findElement(By.xpath("//*[ text() = 'Existing User']"));
                element.click();

                WebElement email = chromeDriver.findElement(By.id("loginEmail"));

                t.until(ExpectedConditions.visibilityOf(email));
                t.until(ExpectedConditions.elementToBeClickable(email));
                email.sendKeys(new CharSequence[]{"shtek@yahoo.com"});

                Thread.sleep(10000L);
            }
            catch (Exception e)
            {
              System.out.println("ppppppppppppppppp" +e);
            }
            chromeDriver.close();

        }
    }
