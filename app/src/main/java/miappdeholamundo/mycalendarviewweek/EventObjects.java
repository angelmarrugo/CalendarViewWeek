package miappdeholamundo.mycalendarviewweek;

import java.util.Date;

/**
 * Created by angel on 14/10/18.
 */

public class EventObjects {


    private String id;
    private String message;
    private Date date;
    private Date end;
    private String day;

    public EventObjects(String id, String message, Date date, Date end, String day) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.end = end;
        this.day = day;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
