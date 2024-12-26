
package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    // établir la Connexion et traiter les données

    static Connection conn = null;

    private static final String URL = "jdbc:postgresql://localhost:5432/Gestion_Holiday";
    private static final String USER = "postgres";
    private static final String PASSWORD="maroua";

    //méthode pour Obtenir une connection
    public static Connection getconnexion(){
        try {
            conn = DriverManager.getConnection (URL , USER , PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
