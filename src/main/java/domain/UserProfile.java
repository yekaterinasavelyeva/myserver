package domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Yekaterina Savelyeva
 * on 24.10.2018
 */
@Entity
@Table(name = "users")
public class UserProfile implements Serializable {

    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", unique = true, updatable = false)
    private String login;

    @Column(name = "password")
    private String pass;

    public UserProfile() {
    }

    public UserProfile(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public UserProfile(String login) {
        this.setId(-1);
        this.setLogin(login);
        this.setPass(login);
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "UserProfile {" +
                "id=" + id +
                ", login='" + login + ", password=" + pass +'\'' +
                '}';
    }
}
