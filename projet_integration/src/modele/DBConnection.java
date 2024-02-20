/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;
import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author HP
 */
public class DBConnection {
    private static SessionFactory sessionFactory;
    private static AnnotationConfiguration config;
    public static AnnotationConfiguration getConfig() {
		if (config == null) {
			config = new AnnotationConfiguration();
			config.addAnnotatedClass(User.class);
			config.addAnnotatedClass(Sport.class);
                        config.addAnnotatedClass(Natation.class);
                        config.addAnnotatedClass(Tennis.class);
                        config.addAnnotatedClass(WindSurf.class);
                        config.addAnnotatedClass(Halterophilie.class);
                        config.addAnnotatedClass(Cyclisme.class);
                        config.addAnnotatedClass(CourseAPied.class);
                        config.addAnnotatedClass(DemandeAmi.class);
                        
//		
			String packageName = DBConnection.class.getPackage().getName();
			config.configure(packageName + "/connection.cfg.xml");
		}
		return config;
	}
    public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				AnnotationConfiguration config = getConfig();
				sessionFactory = config.buildSessionFactory();
			} catch (Throwable ex) {
				System.err.println("Initial SessionFactory creation failed." + ex);
				throw new ExceptionInInitializerError(ex);
			}
		}
		return sessionFactory;
	}

	public static Session getSession() {
		return getSessionFactory().openSession();
	}

    
}
