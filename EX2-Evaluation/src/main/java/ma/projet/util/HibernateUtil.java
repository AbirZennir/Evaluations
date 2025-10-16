package ma.projet.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.InputStream;
import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Properties props = new Properties();
            try (InputStream in = HibernateUtil.class
                    .getClassLoader().getResourceAsStream("application.properties")) {
                props.load(in);
            }

            Configuration cfg = new Configuration();

            cfg.setProperty("hibernate.connection.url", props.getProperty("db.url"));
            cfg.setProperty("hibernate.connection.username", props.getProperty("db.user"));
            cfg.setProperty("hibernate.connection.password", props.getProperty("db.pass"));
            cfg.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            cfg.setProperty("hibernate.dialect", props.getProperty("hibernate.dialect"));
            cfg.setProperty("hibernate.hbm2ddl.auto", props.getProperty("hibernate.hbm2ddl.auto"));
            cfg.setProperty("hibernate.show_sql", props.getProperty("hibernate.show_sql"));
            cfg.setProperty("hibernate.format_sql", props.getProperty("hibernate.format_sql"));

            // Déclarer les entités
            cfg.addAnnotatedClass(ma.projet.classes.Employe.class);
            cfg.addAnnotatedClass(ma.projet.classes.Projet.class);
            cfg.addAnnotatedClass(ma.projet.classes.Tache.class);
            cfg.addAnnotatedClass(ma.projet.classes.EmployeTache.class);
            cfg.addAnnotatedClass(ma.projet.classes.EmployeTacheId.class);

            StandardServiceRegistryBuilder builder =
                    new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());

            return cfg.buildSessionFactory(builder.build());
        } catch (Exception ex) {
            throw new RuntimeException("Erreur init Hibernate : " + ex.getMessage(), ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
