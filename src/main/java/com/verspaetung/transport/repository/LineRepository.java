package com.verspaetung.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.Optional;

import com.verspaetung.transport.domain.Line;

public interface LineRepository extends JpaRepository<Line, Integer> {
    @Query(
        value = "SELECT * FROM Line INNER JOIN (SELECT times.LINE_ID, times.STOP_ID FROM (SELECT Schedule.LINE_ID, Schedule.STOP_ID FROM Schedule WHERE Schedule.time = :time) times INNER JOIN (SELECT Stop.STOP_ID FROM Stop WHERE Stop.X = :posX AND Stop.Y = :posY) stops ON times.STOP_ID=stops.STOP_ID) time ON Line.LINE_ID=time.LINE_ID",
        nativeQuery = true)
    public Optional<Line> findByTimeAndPos(@Param("time") Time time, @Param("posX") int posX, @Param("posY") int posY);

    @Query(
        value = "SELECT * FROM Line INNER JOIN (SELECT TOP 1 Schedule.LINE_ID FROM Schedule WHERE Schedule.STOP_ID = :stopId AND Schedule.time > CURRENT_TIME ORDER BY Schedule.time ASC) time ON Line.LINE_ID=time.LINE_ID",
        nativeQuery = true)
    public Optional<Line> findByStop(@Param("stopId") int stopId);

    @Query(
        value = "SELECT * FROM Line WHERE Line.LINE_NAME = :lineName",
        nativeQuery = true)
    public Optional<Line> findLineByName(@Param("lineName") String lineName);

    @Query(
        value = "SELECT Delay.DELAY FROM Delay WHERE Delay.LINE_NAME = :lineName",
        nativeQuery = true)
    public Optional<Integer> findDelayByLineName(@Param("lineName") String lineName);
}