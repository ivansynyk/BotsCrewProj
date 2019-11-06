package com.util;

import com.model.Department;
import com.model.Lector;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtil {

    private static final SessionFactory concreteSessionFactory;

    static {
        try {
            Configuration config = new Configuration()
                    .addAnnotatedClass(Department.class)
                    .addAnnotatedClass(Lector.class)
                    .setProperty("hibernate.dialect",
                            "org.hibernate.dialect.MySQL5InnoDBDialect")
                    .setProperty("hibernate.connection.driver_class",
                            "com.mysql.jdbc.Driver")
                    .setProperty("hibernate.connection.url",
                            "jdbc:mysql://127.0.0.1:3306/university?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
                    .setProperty("hibernate.connection.username", "root")
                    .setProperty("hibernate.connection.password", "ass123ass")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .setProperty("hibernate.connection.autocommit", "true");

            concreteSessionFactory = config.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return concreteSessionFactory.openSession();
    }
}