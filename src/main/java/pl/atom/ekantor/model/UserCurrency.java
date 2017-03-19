package pl.atom.ekantor.model;

import javax.persistence.*;

/**
 * Created by Artur on 18.03.2017.
 */
@Entity
@Table(name = "user_currency")
public class UserCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private User user;

    @OneToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Column(name = "quantity")
    private Long quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public UserCurrency(User user, Currency currency) {
        this.user = user;
        this.currency = currency;
        this.quantity=0L;
    }

    public UserCurrency() {
    }
}
