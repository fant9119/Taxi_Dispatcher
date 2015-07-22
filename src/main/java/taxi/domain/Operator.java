package taxi.domain;

import javax.persistence.*;

@Entity
@Table(name = "OPERATORS")
public class Operator {

    @Id
    @Column(name = "OPERATOR_ID")
    private Long id;

    @Column(name = "OPERATOR_LOGIN", unique = true, length = 20, nullable = false)
    private String login;

    @Column(name = "OPERATOR_PASSWORD", length = 25, nullable = false)
    private String password;

    @Column(name = "OPERATOR_BLOCK")
    private boolean blocking;

    @Column(name = "ROLE")
    private Role role;


    public Operator() {
        role = Role.OPERATOR;
    }

    public Operator(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
        role = Role.OPERATOR;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBlocking(boolean blocking) {
        this.blocking = blocking;
    }

    public boolean isBlocked() {
        return blocking;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Operator)) return false;

        Operator operator = (Operator) obj;

        if (!this.login.equals(operator.login)) return false;
        if (!this.password.equals(operator.password)) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Operator [id=" + id + ", login=" + login
                + ", password=" + password + "]";
    }
}
