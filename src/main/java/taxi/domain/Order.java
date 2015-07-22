package taxi.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
@SequenceGenerator(name = "sequence", sequenceName = "ORDER_SEQ", allocationSize = 1, initialValue = 1)
public class Order {

    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    private Long id;

    @Column(name = "ORDER_DATE")
    @Temporal(TemporalType.DATE)
    private Date dateOfOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @Column(name = "ORDER_SUM")
    private Long amount;

    @Column(name = "ORDER_ADDRESS_FROM")
    private String addressFrom;

    @Column(name = "ORDER_ADDRESS_TO")
    private String addressTo;

/*    @Column(name = "ORDER_ACTIVITY")
    private boolean activity;*/

    public Order() {
    }

    public Order(Date dateOfOrder, Client client, Long amount, String addressFrom, String addressTo) {
        this.dateOfOrder = dateOfOrder;
        this.client = client;
        this.amount = amount;
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    @Override
    public String toString() {
        return  id + ", "+ amount + ", " + addressFrom + ", " + addressTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (client != null ? !client.equals(order.client) : order.client != null) return false;
        if (addressFrom != null ? !addressFrom.equals(order.addressFrom) : order.addressFrom != null)
            return false;
        if (addressTo != null ? !addressTo.equals(order.addressTo) : order.addressFrom != null)
            return false;
        if (amount != null ? !amount.equals(order.amount) : order.amount != null) return false;
        if (dateOfOrder != null ? !dateOfOrder.equals(order.dateOfOrder) : order.dateOfOrder != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (dateOfOrder != null ? dateOfOrder.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (addressFrom != null ? addressFrom.hashCode() : 0);
        result = 31 * result + (addressTo != null ? addressTo.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        return result;
    }
}
