package model;

public class Book {
    private String id;
    private String bDate;
    private String rDate;
    private double fullcost;
    private String custId;
    private String packId;

    public Book() {
    }

    public Book(String id, String bDate, String rDate, double fullcost, String custId, String packId) {
        this.id = id;
        this.bDate = bDate;
        this.rDate = rDate;
        this.fullcost = fullcost;
        this.custId = custId;
        this.packId = packId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getbDate() {
        return bDate;
    }

    public void setbDate(String bDate) {
        this.bDate = bDate;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public double getFullcost() {
        return fullcost;
    }

    public void setFullcost(double fullcost) {
        this.fullcost = fullcost;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getPackId() {
        return packId;
    }

    public void setPackId(String packId) {
        this.packId = packId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", bDate='" + bDate + '\'' +
                ", rDate='" + rDate + '\'' +
                ", fullcost=" + fullcost +
                ", custId='" + custId + '\'' +
                ", packId='" + packId + '\'' +
                '}';
    }
}
