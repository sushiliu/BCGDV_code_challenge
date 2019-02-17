package com.verspaetung.transport.domain;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Line
 */
@Data
@NoArgsConstructor
@Entity
@IdClass(TimeTableId.class)
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer line_id;

    @Id
    private Integer stop_id;
    
    private Time time;

    public Schedule(Integer lineId, Integer stopId, Time time) {
        this.line_id = lineId;
        this.stop_id = stopId;
        this.time = time;
    }
}

