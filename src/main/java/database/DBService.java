package database;

import domain.UserProfile;

/**
 * Created by Yekaterina Savelyeva
 * on 31.10.2018
 */

public interface DBService {

    long addUser(String login, String password) throws DBException;

    void addSession(long userId, String sessionId) throws DBException;

    UserProfile getUser(long id) throws DBException;

    long getUserIdBySessionId(String sessionId) throws DBException;

    UserProfile getUser(String login) throws DBException;

}
