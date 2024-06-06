package com.mw.xcode.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateDto {

    @JsonProperty("no")
    private String number;
    @JsonProperty("effectiveDate")
    private String effectiveDate;
    @JsonProperty("mid")
    private double mid;
}
