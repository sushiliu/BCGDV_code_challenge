package com.verspaetung.transport.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Stop {

    @Id
    private Integer stop_id;

    private Long x;

    private Long y;

    public Stop(Integer stopId, Long posX, Long posY) {
        this.stop_id = stopId;
        this.x = posX;
        this.y = posY;
    }
}

