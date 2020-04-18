package com.bridgelabz.fundoonotes.utilis;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
 
public class HibernateUtil {
    private static SessionFactory sessionFactory;
     
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry
                = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
             
            // builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);           
        }
         
        return sessionFactory;
    }
}
/*
 * import org.hibernate.SessionFactory; import
 * org.hibernate.boot.registry.StandardServiceRegistryBuilder; import
 * org.hibernate.cfg.Configuration; import
 * org.hibernate.service.ServiceRegistry;
 * 
 * public class HibernateUtil {
 * 
 * private static SessionFactory sessionFactory;
 * 
 * private static SessionFactory buildSessionFactory() { try { // Create the
 * SessionFactory from hibernate.cfg.xml Configuration configuration = new
 * Configuration(); configuration.configure("hibernate.cfg.xml");
 * System.out.println("Hibernate Configuration loaded");
 * 
 * ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
 * .applySettings(configuration.getProperties()).build();
 * System.out.println("Hibernate serviceRegistry created");
 * 
 * SessionFactory sessionFactory =
 * configuration.buildSessionFactory(serviceRegistry);
 * 
 * return sessionFactory; } catch (Throwable ex) {
 * System.err.println("Initial SessionFactory creation failed." + ex);
 * ex.printStackTrace(); throw new ExceptionInInitializerError(ex); } }
 * 
 * public static SessionFactory getSessionFactory() { if (sessionFactory ==
 * null) sessionFactory = buildSessionFactory(); return sessionFactory; } }
 */