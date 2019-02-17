package com.verspaetung.transport.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Delay
 */
@Data
@NoArgsConstructor
@Entity
public class Line {

    @Id
    private Integer line_id;

    private String line_name;

    public Line(Integer id, String name) {
        this.line_id = id;
        this.line_name = name;
    }
}