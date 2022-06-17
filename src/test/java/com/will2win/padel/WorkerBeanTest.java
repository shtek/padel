package com.will2win.padel;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WorkerBeanTest {
    @Autowired
    WorkerBean workerBean;
    @Test
    public void doTest() {
       //this means 2 weeks
       workerBean.doWork(13,12);
    }
}
