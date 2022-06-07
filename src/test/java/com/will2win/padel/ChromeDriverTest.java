package com.will2win.padel;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ChromeDriverTest {
    @Test
    public void isDriverLoading() {

        ChromeDriver driver = ChromeDriverHelper.build();
       assertThat(driver != null);
       driver.close();
    }
}
