package domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Yekaterina Savelyeva
 * on 29.10.2018
 */

@Entity
@Table(name = "sessions")
public class Sessions implements Serializable {

    private static final long serialVersionUID = -5706689714326132798L;


    @Column(name = "id")
    private long id;

    @Id
    @Column(name = "session")
    private String sessionId;

    public Sessions(long id, String sessionId){
        this.id = id;
        this.sessionId = sessionId;
    }

    public Sessions(){}

    public long getId() {
        return id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
