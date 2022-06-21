package com.will2win.padel;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import static com.will2win.padel.ChromeDriverHelper.isWindowsOperatingSystem;


@Service
@Configuration
@PropertySource("classpath:password.properties")
@PropertySource("classpath:card.properties")
public class WorkerBean {
    private static final Logger log = LoggerFactory.getLogger(WorkerBean.class);

    public WorkerBean() {
    }
    @Value( "${password}" )
    private String password;
    @Value( "${chromeDriverLocationWindows}" )
    private   String chromeDriverLocationWindows;

    @Value( "${cardnumber}" )
    private String cardnumber;
    @Value( "${expiry}" )
    private String expiry;
    @Value( "${cvcprop}" )
    private String cvcprop;

    //subroutines that will navigate through the page:
    private void loginFunc(ChromeDriver chromeDriver) {
        try {
            WebDriverWait t = new WebDriverWait(chromeDriver, Duration.ofSeconds(1));

            chromeDriver.get("https://www.openplay.co.uk/embed-my-account/947");
            chromeDriver.switchTo().frame(0);

            WebElement element = chromeDriver.findElement(By.xpath("//*[ text() = 'Existing User']"));
            element.click();

            WebElement email = chromeDriver.findElement(By.id("loginEmail"));

            t.until(ExpectedConditions.visibilityOf(email));
            t.until(ExpectedConditions.elementToBeClickable(email));
            email.sendKeys(new CharSequence[]{"shtek@yahoo.com"});
            WebElement pass = chromeDriver.findElement(By.id("loginPassword"));
            t.until(ExpectedConditions.visibilityOf(pass));
            t.until(ExpectedConditions.elementToBeClickable(pass));
            pass.sendKeys(password);
            WebElement login = chromeDriver.findElement(By.id("loginBtn"));
            t.until(ExpectedConditions.visibilityOf(login));
            t.until(ExpectedConditions.elementToBeClickable(login));
            login.click();
        } catch (Exception e) {
            log.info(e.getStackTrace().toString());
        }


    }
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
            loginFunc(chromeDriver);
            chromeDriver.get("https://www.openplay.co.uk/booking/place/154/select-membership?select=181853");
            //select appropriate date , index in the drop down
            Select dateDropDown = new Select(chromeDriver.findElement(By.id("change-date")));
            dateDropDown.selectByIndex(dateIndex);
            //select padel out of the options
            Select useDropDown = new Select(chromeDriver.findElement(By.name("use")));
            useDropDown.selectByIndex(1);
            //now will be deciding the time
            endTime = time + 1;
            LocalDate date = LocalDate.now().plusDays((long)dateIndex);
            System.out.println("will book for" + date);
            //only book for weekdays:
            if (date.getDayOfWeek().getValue() != 6 || date.getDayOfWeek().getValue() != 7) {
                dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                System.out.println(dateString + "jjjjjjjjjjjjjjjjj");
                //here if the time is less than 10 need to add 0

                String timeString = String.valueOf(time);
                if (time < 10) {
                    timeString = "0" + timeString;
                }
                String endTimeString = String.valueOf(endTime);
                if (endTime < 10) {
                    endTimeString = "0" + endTimeString;
                }

                String searchString = "//*[contains(@href,'/booking/place/154/pricing?start=" + timeString + ":00&end=" + endTimeString + ":00&date=" + dateString + "&resource_id=3530&use_id=83')]";

                chromeDriver.findElement(By.xpath(searchString));
                //when there are 2 courts we choose first court
                String bookingUrlFirstColumn = "https://www.openplay.co.uk/booking/place/154/pricing?start=" + timeString + ":00&end=" + endTimeString + ":00&date=" + dateString + "&resource_id=3530&use_id=83";
                chromeDriver.get(bookingUrlFirstColumn);
              //this is where the work is really happening.("how much time do I loose by this time ??)
                System.out.println("booking first column " + bookingUrlFirstColumn);
                chromeDriver.executeScript("javascript:selectPrice('11')", new Object[0]);
                WebElement cardContinue = chromeDriver.findElement(By.id("cart-continue"));
                t.until(ExpectedConditions.visibilityOf(cardContinue));
                t.until(ExpectedConditions.elementToBeClickable(cardContinue));
                cardContinue.click();
                WebElement cont = chromeDriver.findElement(By.id("continue"));
                t.until(ExpectedConditions.visibilityOf(cont));
                t.until(ExpectedConditions.elementToBeClickable(cont));
                cont.click();
                WebElement checkbox = chromeDriver.findElement(By.id("confirm-checkbox"));
                t.until(ExpectedConditions.elementToBeClickable(checkbox));
                checkbox.click();
                WebElement completeOrder = chromeDriver.findElement(By.id("complete-order"));
                t.until(ExpectedConditions.elementToBeClickable(completeOrder));
                completeOrder.click();
                //need to jump to a new window frame
                chromeDriver.switchTo().frame("smallModal-frame");

            List  <WebElement> frames = chromeDriver.findElements(By.xpath("//iframe[contains(@name,'__privateStripeFrame')]"));
            chromeDriver.switchTo().frame(frames.get(0));


            //card does not appear for some time, so I need to slow down
                Thread.sleep(500);
                String xpath = "//input[@name='cardnumber']";
                List<WebElement> cardnumbers = chromeDriver.findElements(By.xpath(xpath));
                //there shall be 2 elements , not sure which I need to populate , so will try to populate 1 and then if it does not work another
                WebElement firstLocation = cardnumbers.get(0) ;
                t.until(ExpectedConditions.visibilityOf(firstLocation));
                t.until(ExpectedConditions.elementToBeClickable(firstLocation));
                firstLocation.sendKeys(cardnumber);

            //get the exp date
            chromeDriver.switchTo().parentFrame();

            chromeDriver.switchTo().frame(frames.get(1));
            //populate exp date
                String expDate = "//input[@name='exp-date']";
                WebElement expDateElement = chromeDriver.findElement(By.xpath(expDate));
                t.until(ExpectedConditions.visibilityOf(expDateElement));
                t.until(ExpectedConditions.elementToBeClickable(expDateElement));
                expDateElement.sendKeys("0123");

            chromeDriver.switchTo().parentFrame();

            chromeDriver.switchTo().frame(frames.get(2));
            //populating cvc
            String cvcStr = "//input[@name='cvc']";
            WebElement cvc = chromeDriver.findElement(By.xpath(cvcStr));
            t.until(ExpectedConditions.visibilityOf(cvc));
            t.until(ExpectedConditions.elementToBeClickable(cvc));
             cvc.sendKeys(cvcprop);

            chromeDriver.switchTo().parentFrame();
            WebElement submitButton = chromeDriver.findElement(By.id("submit-card"));
            t.until(ExpectedConditions.visibilityOf(submitButton));
            t.until(ExpectedConditions.elementToBeClickable(submitButton));
            submitButton.click();


           System.out.println("successfully booked" + dateString);


            Thread.sleep(20000L);
            }
        }
        catch (Exception e)
        {
            System.out.println("ppppppppppppppppp" +e);
        }
        chromeDriver.close();

    }
}
