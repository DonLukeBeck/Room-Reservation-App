package nl.tudelft.oopp.demo.entities;

import java.util.Date;

public class Holidays {

    private int holidaysID;

    private Date startDate;

    private Date endDate;

    private String comments;

    /**
     * Constructor for holidays.
     * @param holidaysID Unique ID for each holiday
     * @param startDate Holiday starting date
     * @param endDate Holiday end date
     * @param comments Any comments regarding the holiday
     */
    public Holidays(int holidaysID, Date startDate, Date endDate, String comments) {
        this.holidaysID = holidaysID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.comments = comments;
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
