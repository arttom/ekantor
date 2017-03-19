package pl.atom.ekantor.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * User entity class for Spring-Security and holding user money value
 * Created by Artur on 18.03.2017.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "plns")
    private BigDecimal plns = new BigDecimal(2500);

    @Transient
    private String passwordConfirm;

    @ManyToMany
    @JoinTable(name = "userRole", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<UserCurrency> userCurrencies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }


    public Set<UserCurrency> getUserCurrencies() {
        return userCurrencies;
    }

    public void setUserCurrencies(Set<UserCurrency> userCurrencies) {
        this.userCurrencies = userCurrencies;
    }

    public BigDecimal getPlns() {
        return plns;
    }

    public void setPlns(BigDecimal plns) {
        this.plns = plns;
    }

    public User(String username, String password, BigDecimal plns) {
        this.username = username;
        this.password = password;
        this.plns = plns;
    }

    public User() {
    }
}
