package pl.kusiakk.weblibrary.domain.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @Column(name = "id_user_details")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userDetailsID;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToOne(mappedBy = "userDetails")
    @JoinColumn
    private User user;

    public UserDetails() {
    }

    public Integer getUserDetailsID() {
        return userDetailsID;
    }

    public void setUserDetailsID(Integer borrowerDetailsID) {
        this.userDetailsID = borrowerDetailsID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails that = (UserDetails) o;
        return Objects.equals(userDetailsID, that.userDetailsID) &&
                Objects.equals(address, that.address) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userDetailsID, address, email, phone, user);
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "borrowerDetailsID=" + userDetailsID +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", user=" + user +
                '}';
    }
}
