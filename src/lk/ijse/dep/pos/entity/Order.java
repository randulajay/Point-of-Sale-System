package lk.ijse.dep.pos.entity;

import java.sql.Date;

public class Order implements SuperEntity {

    private int id;
    private Date date;
    private String customerId;

    public Order() {
    }

    public Order(int id, Date date, String customerId) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
