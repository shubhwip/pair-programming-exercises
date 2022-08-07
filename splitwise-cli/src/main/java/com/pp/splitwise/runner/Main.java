package com.pp.splitwise.runner;

import com.pp.splitwise.splitter.*;
import com.systemdesign.splitwise.splitter.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
public class Main {
    public static void main(String[] args) {
        User user1 = User.builder().userId(1).userName("Shubham").build();
        User user2 = User.builder().userId(2).userName("Abhishek").build();
        User user3 = User.builder().userId(3).userName("Niharica").build();
        User user4 = User.builder().userId(4).userName("Kapil").build();
        User user5 = User.builder().userId(5).userName("Kavya").build();

        List<User> users = Arrays.asList(user1, user2, user3, user4, user5);

        if(args.length < 4) {
            log.error("Please input all the required paramters");
        }
        User expenseMaker = users.stream().filter(u->u.getUserId() == Integer.parseInt(args[0])).findFirst().get();
        BigDecimal expense = new BigDecimal(args[1]);
        String splitType = args[2];
        String[] userIdsToSplitAmount = args[3].split(",");
        String[] splitAmount = new String[userIdsToSplitAmount.length];
        if(splitType.equals("perc") || splitType.equals("abs"))
            splitAmount = args[4].split(",");
        List<Borrower> borrowers = new ArrayList<>();
        for(String userId : userIdsToSplitAmount) {
            Borrower b = Borrower.builder().lenderId(expenseMaker.getUserId()).borrowerId(Integer.parseInt(userId)).build();
            borrowers.add(b);
        }
        if(splitType.equals("perc")) {
            for(int i=0;i<splitAmount.length;i++) {
                borrowers.get(i).setPercentageSplit(Optional.of(new BigDecimal(splitAmount[i])));
            }
        } else if(splitType.equals("abs")) {
            for(int i=0;i<splitAmount.length;i++) {
                borrowers.get(i).setAbsoluteSplit(Optional.of(new BigDecimal(splitAmount[i])));
            }
        }
        Splitter s = new DefaultSplitter(expenseMaker, borrowers, expense, splitType);
        SplitTransaction transaction = s.splitAmount();
        log.info("Splitted Transaction Details {}", transaction);
    }
}
