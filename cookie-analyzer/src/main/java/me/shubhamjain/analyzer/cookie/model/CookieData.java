package me.shubhamjain.analyzer.cookie.model;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class CookieData {
    private String cookie;
    private ZonedDateTime timestamp;
}
