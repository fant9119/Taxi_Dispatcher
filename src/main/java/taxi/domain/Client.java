package taxi.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CLIENTS")
@SequenceGenerator(name = "sequence", sequenceName = "CLIENT_SEQ", allocationSize = 1, initialValue = 1)
public class Client {

    @Id
    @Column(name = "CLIENT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    private Long id;

    @Column(name = "CLIENT_NAME")
    private String name;

    @Column(name = "CLIENT_SURNAME")
    private String surname;

    @Column(name = "CLIENT_PHONE")
    private String phone;

    @Column(name = "CLIENT_ADDRESS")
    private String address;

    @Temporal(TemporalType.DATE)
    @Column(name = "CLIENT_DATE_OF_LAST_ORDER")
    private Date lastOrderDate;

    @Column(name = "CLIENT_TOTAL_SUM")
    private Long sum;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private Set<Order> orders = new HashSet<>();

    public Client() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public Date getLastOrderDate() {
        return lastOrderDate;
    }

    public void setLastOrderDate(Date lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }

    public Long getSum() {
        return sum;
    }
    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}


