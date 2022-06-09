package com.will2win.padel;


import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@ConditionalOnProperty({"prod"})
    public class ScheduledTasks {
    @Autowired
    WorkerBean workerBean;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);


    @Scheduled(fixedRate = 5000) //every 5 seconds
    //this is just livebeat, to make sure the system is running
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
   //     this.workerBean.doWork(7, 18);


    }

    //run at midnight once in 24 hrs
    @Scheduled(
            cron = "0 0 0 * * *"
    )
    public void doWork() throws Exception {
        System.out.println("Running the midnight task of booking");
           this.workerBean.doWork(7, 18);
    }


}


