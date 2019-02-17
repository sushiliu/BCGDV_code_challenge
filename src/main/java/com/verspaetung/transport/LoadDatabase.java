package com.verspaetung.transport;

import java.io.File;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.verspaetung.transport.domain.Delay;
import com.verspaetung.transport.domain.Line;
import com.verspaetung.transport.domain.Stop;
import com.verspaetung.transport.domain.Schedule;
import com.verspaetung.transport.repository.DelayRepository;
import com.verspaetung.transport.repository.LineRepository;
import com.verspaetung.transport.repository.StopRepository;
import com.verspaetung.transport.repository.ScheduleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
class LoadDatabase{

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private DelayRepository delayRepository;

    /**
     * Initialize in memory database with test data from csv files
     */
    @Bean
	CommandLineRunner initDatabase() {
        return args -> {
            log.info("Preloading " + this.lineRepository.saveAll(loadObjectList(Line.class, "data/lines.csv")));
            log.info("Preloading " + this.stopRepository.saveAll(loadObjectList(Stop.class, "data/stops.csv")));
            log.info("Preloading " + this.scheduleRepository.saveAll(loadObjectList(Schedule.class, "data/times.csv")));
            log.info("Preloading " + this.delayRepository.saveAll(loadObjectList(Delay.class, "data/delays.csv")));
		};
    }
    
    /**
     * Use fasterxml library to process csv data to target Java Object
     * 
     * @param clazz Target Class of the data
     * @param fileName Target csv file name, including relative path to "resources" folder
     * @return
     */
    private <T> List<T> loadObjectList(Class<T> clazz, String fileName) {
        try {
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = mapper.schemaFor(clazz).withHeader().withColumnReordering(true);
            ObjectReader reader = mapper.readerFor(clazz).with(schema);
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<T> readValues = reader.readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            log.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }
}