package Software.Engineering.demo;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "`order`")
public class Order {

    public Order(int orderNo, User user, LocalDateTime timePlaced, RTable table, int seat, boolean refunded, LocalDateTime readyAt, double totalPrice) {
        this.orderNo = orderNo;
        this.user = user;
        this.timePlaced = timePlaced;
        this.table = table;
        this.seat = seat;
        this.refunded = refunded;
        this.readyAt = readyAt;
        this.totalPrice = totalPrice;
    }

    public Order(User user, LocalDateTime timePlaced, RTable table, int seat, boolean refunded, LocalDateTime readyAt, double totalPrice) {
        this.user = user;
        this.timePlaced = timePlaced;
        this.table = table;
        this.seat = seat;
        this.refunded = refunded;
        this.readyAt = readyAt;
        this.totalPrice = totalPrice;
    }

    public Order() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_NO")
    private int orderNo;

    @ManyToOne
    @JoinColumn(name = "Waiter_ID", nullable = false)
    private User user;

    @Column(name = "Time_Placed")
    private LocalDateTime timePlaced;

    @ManyToOne
    @JoinColumn(name = "`Table`", nullable = false)
    private RTable table;

    @Column(name = "`Seat#`")
    private int seat;

    @Column(name = "Refunded")
    private boolean refunded;

    @Column(name = "Ready_At_Time")
    private LocalDateTime readyAt;

    @Column(name = "Total_Price")
    private double totalPrice;


    ///Getters and setters
    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTimePlaced() {
        return timePlaced;
    }

    public void setTimePlaced(LocalDateTime timePlaced) {
        this.timePlaced = timePlaced;
    }

    public RTable getTable() {
        return table;
    }

    public void setTable(RTable table) {
        this.table = table;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public boolean isRefunded() {
        return refunded;
    }

    public void setRefunded(boolean refunded) {
        this.refunded = refunded;
    }

    public LocalDateTime getReadyAt() {
        return readyAt;
    }

    public void setReadyAt(LocalDateTime readyAt) {
        this.readyAt = readyAt;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
