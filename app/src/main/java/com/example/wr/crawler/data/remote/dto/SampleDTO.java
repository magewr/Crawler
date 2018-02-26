package com.example.wr.crawler.data.remote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by WR on 2017-11-27.
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SampleDTO {
    private String volume;
    private String last;
    private String timestamp;
    private String high;
    private String low;
    private String currency;
    private String result;
    private String errorCode;
}
