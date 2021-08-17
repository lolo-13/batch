package org.compain.service;

import org.compain.consumer.BorrowingProxy;
import org.compain.model.UserLateBorrowing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BorrowingService {

    private final BorrowingProxy borrowingProxy;


    private String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiaWJsaW9AZ21haWwuY29tIiwiYWNjb3VudF9pZCI6Mywicm9sZSI6IkVtcGxveWVlIiwiaWF0IjoxNjI5MjIyMzQ2fQ.i4OtAv3GwhWhSt1IpQXZyaTWpFMH6iuJ2Li9uC8mKOGGzb4QG9XlH0yu2LOytmi_7zssI6D0iBxEKaN6XnsmZw";

    public BorrowingService(BorrowingProxy borrowingProxy) {
        this.borrowingProxy = borrowingProxy;
    }
    @Scheduled(fixedDelay = 60000)
    //@Scheduled(cron = "0 0 9 * * ?")
    public void sendMailForLateBorrowing(){
        LocalDateTime today = LocalDateTime.now();
        List<UserLateBorrowing> userLateBorrowingList = borrowingProxy.getLateBorrowing(today, token);
        for(UserLateBorrowing u : userLateBorrowingList){
            borrowingProxy.sendMailForLateBorrowing(u, token);
        }
    }
}
