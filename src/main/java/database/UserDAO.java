package database;

import domain.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Created by Yekaterina Savelyeva
 * on 29.10.2018
 */

public class UserDAO {

    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public UserProfile get(long id) throws HibernateException {
       return (UserProfile) session.createCriteria(UserProfile.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    public UserProfile getByLogin(String login) throws HibernateException {
        return (UserProfile) session.createCriteria(UserProfile.class).add(Restrictions.eq("login", login)).uniqueResult();
    }

    public long getUserId(String login) throws HibernateException {
        Criteria criteria = session.createCriteria(UserProfile.class);
        return ((UserProfile) criteria.add(Restrictions.eq("login", login)).uniqueResult()).getId();
    }

    public long insertUser(String login) throws HibernateException {
        return (Long) session.save(new UserProfile(login));
    }

    public long insertUser(String login, String password) throws HibernateException {
        return (Long) session.save(new UserProfile(login, password));
    }
}
