package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service.Impl;

import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service.NotificationService;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private RedisService redisService;

    @Override
    public void handleBotNotification(Long userId, String botName) {
        String cooldownKey = "notif_cooldown:user_"+userId;
        String pendingKey = "user:"+userId+":pending_notifs";
        String message = botName + " replied to your Post";

        if(redisService.isExists(cooldownKey)){
            redisService.pushToList(pendingKey,message);
        }else{
            System.out.println("Push Notification Sent to User: "+userId);
            redisService.setWithTTL(cooldownKey,"1",900);
        }
    }
}
