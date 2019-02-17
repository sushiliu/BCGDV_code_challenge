package com.verspaetung.transport.response;

import lombok.Data;

@Data
public class FindDelayResponseBody {
    private int line_id;
    private String line_name;
    private Boolean isDelayed;
    private int delayMinutes;

    public FindDelayResponseBody(int id, String name, Boolean isDelayed, int min) {
        this.line_name = name;
        this.isDelayed = isDelayed;
        this.delayMinutes = min;
    }
}