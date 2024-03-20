package es.nemes.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@NamedQueries({
        @NamedQuery(name = "NUser.findAll", query = "SELECT u FROM NUser u")
})
public class NUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    String email;
    String username;
    String password;
    String phone;

    public NUser(){
        super();
    }

    public NUser(String email, String username, String password, String phone) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    @Transient
    public static final NUser NOT_FOUND = new NUser("Not found", "Unknown", "", "");

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NUser user = (NUser) o;
        return Objects.equals(email, user.email) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, username, password, phone);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public void update(NUser user) {
        email = user.email;
        username = user.username;
        password = user.password;
        phone = user.phone;
    }
}
