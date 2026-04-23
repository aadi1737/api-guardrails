package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Scheduler;

import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@EnableScheduling
public class NotificationScheduler {

    @Autowired
    private RedisService redisService;

    @Scheduled(fixedRate = 300000)
    public void clearPendingNotifications(){
        Set<String> keys = redisService.getKeys("user:*:pending_notifs");

        if(keys==null)
            return;

        for(String key : keys){
            List<String> messagesToSend = redisService.getList(key);

            if (messagesToSend==null || messagesToSend.isEmpty())
                continue;

            int count = messagesToSend.size();
            String firstMsg = messagesToSend.get(0);
            System.out.println("Summarized Push Notification: "+firstMsg+" and ["+(count-1)+"] other is pending.");
            redisService.delete(key);
        }
    }
}
