package ma.projet.service;

import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Function;

public class BaseService {
    protected <R> R tx(Function<Session, R> work) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            R res = work.apply(s);
            t.commit();
            return res;
        } catch (Exception e) {
            if (t != null) t.rollback();
            throw e;
        } finally {
            s.close();
        }
    }
}
