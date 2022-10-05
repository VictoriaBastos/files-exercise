package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Participant {

    private String name;
    private String zone;
    private LocalDateTime date;

    public Participant(String name, String zone, LocalDateTime date) {
        this.name = name;
        this.zone = zone;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getZone() {
        return zone;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return name + ';' + zone + ';' + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
