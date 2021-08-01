package org.compain.service;

import org.compain.consumer.BorrowingProxy;
import org.compain.model.UserLateBorrowing;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BorrowingService {

    private final BorrowingProxy borrowingProxy;

    public BorrowingService(BorrowingProxy borrowingProxy) {
        this.borrowingProxy = borrowingProxy;
    }
    @Scheduled(fixedDelay = 60000)
    //@Scheduled(cron = "0 0 9 * * ?")
    public void sendMailForLateBorrowing(){
        LocalDateTime today = LocalDateTime.now();
        List<UserLateBorrowing> userLateBorrowingList = borrowingProxy.getLateBorrowing(today);
        for(UserLateBorrowing u : userLateBorrowingList){
            borrowingProxy.sendMailForLateBorrowing(u);
        }
    }
}
