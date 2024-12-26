package DAO; // Déclaration du package DAO (Data Access Object)

import java.sql.*; // Importation des classes pour les connexions et requêtes SQL
import java.util.ArrayList; // Importation de la classe ArrayList pour stocker des listes

import Model.Employer; // Importation de la classe Employer du package Model
import enums.*; // Importation de l'énumération

// Déclaration de la classe EmployerDAOimpl qui implémente l'interface EmployerDAOI
public class EmployerDAOimpl implements EmployerDAOI {
    private static Connexion conn; // Déclaration de la variable statique pour la connexion

    // Constructeur par défaut pour initialiser la connexion
    public EmployerDAOimpl() {
        this.conn = conn; // Initialisation de la connexion (pas réellement utilisé ici)
    }

    // Méthode pour ajouter un employé dans la base de données
    @Override
    public void ajouterEmployer(Employer NvEmployer) {
        // Requête SQL d'insertion pour ajouter un nouvel employé
        String sql = "INSERT INTO Employer (Nom, Prenom, Email, Telephone, Salaire, Role, Poste) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = Connexion.getconnexion().prepareStatement(sql)) {
            // Attribution des valeurs des paramètres dans la requête SQL
            st.setString(1, NvEmployer.getNom()); // Remplace le 1er "?" par le nom de l'employé
            st.setString(2, NvEmployer.getPrenom()); // Remplace le 2ème "?" par le prénom
            st.setString(3, NvEmployer.getEmail()); // Remplace le 3ème "?" par l'email
            st.setString(4, NvEmployer.getTelephone()); // Remplace le 4ème "?" par le téléphone
            st.setDouble(5, NvEmployer.getSalaire()); // Remplace le 5ème "?" par le salaire
            st.setString(6, NvEmployer.getRole()); // Remplace le 6ème "?" par le rôle
            st.setString(7, NvEmployer.getPoste()); // Remplace le 7ème "?" par le poste

            st.executeUpdate(); // Exécute la requête d'insertion
         } catch (SQLException e) { // Capture et gestion des erreurs SQL
            e.printStackTrace();
        }
    }

    // Méthode pour modifier les informations d'un employé existant
    @Override
    public void modifierEmployer(Employer NvEmployer) {
        // Requête SQL pour mettre à jour un employé existant en fonction de son ID
        String sql = "UPDATE Employer SET Nom = ?, Prenom = ?, Email = ?, Telephone = ?, Salaire = ?, Role = ?, Poste = ? WHERE Id = ?";
        try (PreparedStatement st = Connexion.getconnexion().prepareStatement(sql)) {
            // Remplissage des paramètres de la requête avec les nouvelles valeurs
            st.setString(1, NvEmployer.getNom()); // Modifie le nom
            st.setString(2, NvEmployer.getPrenom()); // Modifie le prénom
            st.setString(3, NvEmployer.getEmail()); // Modifie l'email
            st.setString(4, NvEmployer.getTelephone()); // Modifie le téléphone
            st.setDouble(5, NvEmployer.getSalaire()); // Modifie le salaire
            st.setString(6, NvEmployer.getRole()); // Modifie le rôle
            st.setString(7, NvEmployer.getPoste()); // Modifie le poste
            st.setInt(8, NvEmployer.getId()); // Condition WHERE sur l'ID

           st.executeUpdate(); // Exécute la requête et retourne le nombre de lignes affectées

        } catch (SQLException e) { // Capture et gestion des erreurs SQL
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un employé via son ID
    @Override
    public void supprimerEmployer(int id) {
        // Requête SQL pour supprimer un employé en fonction de son ID
        String sql = "DELETE FROM Employer WHERE Id = ?";
        try (PreparedStatement st = Connexion.getconnexion().prepareStatement(sql)) {
            st.setInt(1, id); // Remplace le "?" par l'ID de l'employé à supprimer

            st.executeUpdate(); // Exécute la requête et retourne le nombre de lignes affectées

        } catch (SQLException e) { // Capture des erreurs SQL
            e.printStackTrace();
        }
    }

    // Méthode pour récupérer tous les employés depuis la base de données
    @Override
    public ArrayList<Employer> getAllEmployer() {
        ArrayList<Employer> employers = new ArrayList<>(); // Crée une liste vide pour stocker les employés
        String sql = "SELECT * FROM Employer"; // Requête SQL pour sélectionner tous les employés

        try (PreparedStatement st = Connexion.getconnexion().prepareStatement(sql);
             ResultSet rs = st.executeQuery()) { // Exécute la requête SELECT et stocke le résultat dans un ResultSet

            while (rs.next()) { // Parcours chaque ligne du résultat
                // Crée un objet Employer avec les valeurs récupérées
                employers.add(new Employer(
                        rs.getInt("Id"), // Récupère l'ID
                        rs.getString("Nom"), // Récupère le nom
                        rs.getString("Prenom"), // Récupère le prénom
                        rs.getString("Telephone"), // Récupère le téléphone
                        rs.getString("Email"), // Récupère l'email
                        rs.getDouble("Salaire"), // Récupère le salaire
                        Role.valueOf(rs.getString("Role")), // Convertit la chaîne en enum Role
                        Poste.valueOf(rs.getString("Poste")) // Convertit la chaîne en enum Poste
                ));
            }
        } catch (SQLException e) {e.printStackTrace();
        }
        return employers; // Retourne la liste des employés récupérés
    }
}
