package com.pp.splitwise.splitter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class User {
    Integer userId;
    String userName;
    // Split History for a User
    List<SplitTransaction> transactions;
}
