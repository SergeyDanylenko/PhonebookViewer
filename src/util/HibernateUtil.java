package util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import property.EditConfig;

/**
 * Created by ASUP-9 on 21.07.2017.
 */
public class HibernateUtil {
    public static SessionFactory sessionFactory = null;
    private static EditConfig config = new EditConfig("config.ini");

    public static String URL = "jdbc:mysql://" + config.getDB_IP() + ":" +config.getDB_PORT() + "/" + config.getDB_NAME();
    public static String USERNAME = config.getDB_USER();
    public static String PASSWORD = config.getDB_PASS();

    public static void setURL(String URL) {
        HibernateUtil.URL = URL;
    }

    public static void setUSERNAME(String USERNAME) {
        HibernateUtil.USERNAME = USERNAME;
    }

    public static void setPASSWORD(String PASSWORD) {
        HibernateUtil.PASSWORD = PASSWORD;
    }

    private static SessionFactory configureSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", URL);
        configuration.setProperty("hibernate.connection.username", USERNAME);
        configuration.setProperty("hibernate.connection.password", PASSWORD);
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        return configuration.buildSessionFactory(serviceRegistry);
    }
    public static SessionFactory getSessionFactory() {
        sessionFactory = configureSessionFactory();
        return sessionFactory;
    }

    public static void reloadSessionFactory() {
        sessionFactory.close();
        sessionFactory = configureSessionFactory();
    }
}
