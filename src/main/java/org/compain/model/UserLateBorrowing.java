package org.compain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserLateBorrowing {

    private String name;
    private String firstname;
    private String email;
    private List<LateBorrowing> lateBorrowingList;

    @Data
    public static class LateBorrowing {
        private String title;
        private String author;
        private LocalDateTime borrowing_limit_date;
    }
}

