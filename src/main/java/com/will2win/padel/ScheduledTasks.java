package com.will2win.padel;


import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@ConditionalOnProperty({"prod"})
    public class ScheduledTasks {
    @Autowired
    WorkerBean workerBean;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);


    @Scheduled(fixedRate = 500000) //every 5 seconds
    //this is just livebeat, to make sure the system is running
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));

      Integer bookingTime = Integer.valueOf(System.getProperty("bookingTime"));
       System.out.println("boooking time is  " + bookingTime.toString());
      //  this.workerBean.prepare();
    }

    //run at midnight once in 24 hrs
    @Scheduled(
            cron = "0 0 0 * * *"
    )
    public void doWork() throws Exception {
        System.out.println("Running the midnight task ");//of booking for 19");
        Integer bookingTime = Integer.valueOf(System.getProperty("bookingTime"));
        System.out.println("booking for " + bookingTime);
        this.workerBean.doWork(14, bookingTime);
    }


    @Scheduled(
            cron = "0 59 23 * * *"
    )
    public void prep() throws Exception {
        System.out.println("Running the midnight task of preparing");
        this.workerBean.prepare();
    }


}


