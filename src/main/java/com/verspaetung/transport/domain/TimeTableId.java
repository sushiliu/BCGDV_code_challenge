package com.verspaetung.transport.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class TimeTableId implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Integer line_id;
    private Integer stop_id;
}