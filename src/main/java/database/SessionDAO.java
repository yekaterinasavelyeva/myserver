package database;

import domain.Sessions;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Created by Yekaterina Savelyeva
 * on 29.10.2018
 */

public class SessionDAO {

    private Session session;

    public SessionDAO(Session session) {
        this.session = session;
    }

    public Sessions get(String sessionId) throws HibernateException {
       return (Sessions) session.createCriteria(Sessions.class).add(Restrictions.eq("session", sessionId)).uniqueResult();
    }

    public Sessions getByUserId(long id) throws HibernateException {
        return (Sessions) session.createCriteria(Sessions.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    public long getIdBySessionsId(String sessionId) throws HibernateException {
        Sessions sessions = (Sessions) session.get(Sessions.class, sessionId);
                          /* Sessions sessions = (Sessions)session
                      .createCriteria(Sessions.class)
                      .add(Restrictions.eq("sessionId", sessionId)).uniqueResult();;*/
                         return sessions.getId();
    }

    public void insertSession(long userId, String sessionId) throws HibernateException {
        session.save(new Sessions(userId, sessionId));
    }

}
