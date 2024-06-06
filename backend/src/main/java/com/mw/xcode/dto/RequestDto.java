package com.mw.xcode.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RequestDto {
    private String currency;
    private String name;
    private LocalDateTime date;
    private Double value;
}
