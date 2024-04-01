package modele;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * Cette classe est responsable de l'initialisation des données dans la base de données en utilisant Hibernate.
 */
public class DataInit {

	/**
	 * Cette méthode crée les tables dans la base de données en utilisant la configuration de l'annotation et l'exportation de schéma.
	 */
	public static void createTables() {
		AnnotationConfiguration config = DBConnection.getConfig();
		SchemaExport schemaExport = new SchemaExport(config);
		schemaExport.create(true, true);
	}
	
	/**
	 * Méthode principale qui appelle la méthode createTables pour initialiser les tables dans la base de données.
	 * @param args les arguments de la ligne de commande
	 */
	public static void main(String[] args) {
		createTables();
	}
}
