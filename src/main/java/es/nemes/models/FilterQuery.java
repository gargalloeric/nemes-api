package es.nemes.models;

import java.util.List;

public class FilterQuery {
    String initialDate;
    String finishDate;
    List<String> events;

    public String getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "FilterQuery{" +
                "initialDate='" + initialDate + '\'' +
                ", finishDate='" + finishDate + '\'' +
                ", events=" + events +
                '}';
    }
}
