package com.will2win.padel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.awaitility.Duration;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
@SpringBootTest
//TODO: this was just for test purpose , remove
public class ScheduledTaskTest {

    @SpyBean
    ScheduledTasks tasks;

 //   @Test
  //  public void reportCurrentTime() {
   //     await().atMost(Duration.TEN_SECONDS).untilAsserted(() -> {
   //         verify(tasks, atLeast(2)).reportCurrentTime();
   //     });
   // }
}