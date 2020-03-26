package nl.tudelft.oopp.demo.entities;

import java.util.Date;

public class Holidays {

    private int holidaysID;

    private Date startDate;

    private Date endDate;

    private String comments;

    public Holidays() {
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getHolidaysID() {
        return holidaysID;
    }

    public void setHolidaysID(int holidaysID) {
        this.holidaysID = holidaysID;
    }
}
