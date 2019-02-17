package com.verspaetung.transport.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@NoArgsConstructor
@Entity
public class Delay {

    @Id
    private String line_name;

    private Integer delay;

    public Delay(String line_name, Integer delay) {
        this.line_name = line_name;
        this.delay = delay;
    }
}