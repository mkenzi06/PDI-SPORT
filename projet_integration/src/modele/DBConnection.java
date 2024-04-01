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
 * @author lounis
 */
/**
 * Cette classe représente une connexion à la base de données.
 */
public class DBConnection {
	private static SessionFactory sessionFactory;
	private static AnnotationConfiguration config;
	
	/**
	 * Récupère la configuration de l'annotation.
	 * @return La configuration de l'annotation.
	 */
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
			config.addAnnotatedClass(Performances.class);
			
			String packageName = DBConnection.class.getPackage().getName();
			config.configure(packageName + "/connection.cfg.xml");
		}
		return config;
	}
	
	/**
	 * Récupère la session factory.
	 * @return La session factory.
	 */
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

	/**
	 * Récupère la session.
	 * @return La session.
	 */
	public static Session getSession() {
		return getSessionFactory().openSession();
	}
}
