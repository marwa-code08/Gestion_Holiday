package Controller;


import Model.*;
import View.employerView;
import enums.Role;
import enums.Poste;

import javax.swing.*;
import java.util.ArrayList;

public class EmployerController {

    // Attributs
    private EmployerModel Model; // Modèle qui gère les données et la logique métier des employés
    private employerView View;   // Vue qui gère l'interface utilisateur

    // Constructeur
    public EmployerController(EmployerModel Model, employerView View) {
        this.Model = Model; // Initialisation du modèle
        this.View = View;   // Initialisation de la vue

        // Association des boutons de la vue aux méthodes de contrôle
        this.View.AjoutBtn.addActionListener(e -> ajouterEmployer());  // Bouton Ajouter
        this.View.ModifBtn.addActionListener(e -> modifierEmployer()); // Bouton Modifier
        this.View.SuppBtn.addActionListener(e -> supprimerEmployer());// Bouton Supprimer
        this.View.AffichBtn.addActionListener (e -> afficherEmployer()); // button Affichage
    }

    // Méthode pour ajouter un employé
    private void ajouterEmployer() {
        // Récupération des données depuis les champs de la vue
        String Nom = View.getNom();
        String Prenom = View.getPrenom();
        String Email;
        String Telephone;
        double Salaire;

        // Validation de l'email
        try {
            Email = View.getEmail(); // Récupération de l'email
        } catch (Exception e) {
            View.afficheMessageErreur("Email invalide."); // Message d'erreur si invalide
            return; // Arrêter l'exécution de la méthode
        }

        // Validation du téléphone
        try {
            Telephone = View.getTelephone(); // Récupération du téléphone
        } catch (NumberFormatException e) {
            View.afficheMessageErreur("Numéro de téléphone invalide."); // Message d'erreur
            return;
        }

        // Validation du salaire
        try {
            Salaire = View.getSalaire(); // Récupération et conversion du salaire
        } catch (NumberFormatException e) {
            View.afficheMessageErreur("Salaire invalide."); // Message d'erreur
            return;
        }

        // Récupération des rôles et postes sélectionnés depuis les ComboBox
        Role Role = (Role) View.RoleComboBox.getSelectedItem();   // Sélection du rôle
        Poste Poste = (Poste) View.PosteComboBox.getSelectedItem(); // Sélection du poste

        // Ajout de l'employé via le modèle
        boolean ajoutReussi = Model.ajouterEmployer(0, Nom, Prenom, Email, Telephone, Salaire, Role, Poste);

        if (ajoutReussi) {
            View.afficheMessageSucces("Employé ajouté avec succès !"); // Confirmation de réussite
            // actualiserTableau(); Actualiser le tableau des employés
        } else {
            View.afficheMessageErreur("Erreur lors de l'ajout de l'employé."); // Message d'erreur
        }
    }

    // Méthode pour modifier un employé
    private void modifierEmployer() {
        // Vérification qu'une ligne est sélectionnée dans le tableau
        int ligneSelectionnee = View.getTableauEmployer().getSelectedRow();

        if (ligneSelectionnee >= 0) { // Si une ligne est bien sélectionnée
            try {
                // Récupération de l'ID de l'employé depuis la ligne sélectionnée
                int id = (int) View.getTableauEmployer().getValueAt(ligneSelectionnee, 0);

                // Récupération des données depuis la vue
                String Nom = View.getNom();
                String Prenom = View.getPrenom();
                String Email = View.getEmail();
                String Telephone = View.getTelephone();
                double Salaire = View.getSalaire();
                Role role = (Role) View.getRole(); // Rôle sélectionné
                Poste poste = (Poste) View.getPoste(); // Poste sélectionné

                // Modification via le modèle
                boolean modificationReussie = Model.modifierEmployer(id, Nom, Prenom, Email, Telephone, Salaire, role, poste);

                if (modificationReussie) {
                    View.afficheMessageSucces("Employé modifié avec succès !"); // Message de confirmation
                    actualiserTableau(); // Actualiser le tableau après modification
                } else {
                    View.afficheMessageErreur("Erreur lors de la modification de l'employé."); // Message d'erreur
                }

            } catch (Exception e) {
                View.afficheMessageErreur("Données invalides. Veuillez vérifier les informations."); // Erreur générale
            }
        } else {
            View.afficheMessageErreur("Veuillez sélectionner une ligne à modifier."); // Si aucune ligne n'est sélectionnée
        }
    }

    // Méthode pour supprimer un employé
    private void supprimerEmployer() {
        // Vérification qu'une ligne est sélectionnée dans le tableau
        int ligneSelectionnee = View.getTableauEmployer().getSelectedRow();

        if (ligneSelectionnee >= 0) { // Si une ligne est bien sélectionnée
            // Afficher une boîte de dialogue de confirmation
            int confirmation = JOptionPane.showConfirmDialog(
                    null,
                    "Êtes-vous sûr de vouloir supprimer cet employé ?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmation == JOptionPane.YES_OPTION) { // Si l'utilisateur confirme
                try {
                    // Récupération de l'ID de l'employé à supprimer
                    int id = (int) View.getTableauEmployer().getValueAt(ligneSelectionnee, 0);

                    // Suppression via le modèle
                    boolean suppressionReussie = Model.supprimerEmployer(id);

                    if (suppressionReussie) {
                        View.afficheMessageSucces("Employé supprimé avec succès !"); // Confirmation de suppression
                        actualiserTableau(); // Actualiser le tableau
                    } else {
                        View.afficheMessageErreur("Erreur lors de la suppression de l'employé."); // Message d'erreur
                    }

                } catch (Exception e) {
                    View.afficheMessageErreur("Erreur inattendue lors de la suppression."); // Erreur générale
                }
            }
        } else {
            View.afficheMessageErreur("Veuillez sélectionner une ligne à supprimer."); // Aucune ligne sélectionnée
        }
    }
    private void afficherEmployer() {
        ArrayList<Object[]> employers = Model.getAllEmployer();
        View.model.setRowCount(0);
        for (Object[] employee : employers) {
            View.model.addRow(employee);
        }
    }

    // Méthode pour actualiser le tableau des employés dans la vue
    private void actualiserTableau() {
        View.getModelTableau().setRowCount(0); // Effacer les anciennes données du tableau
        for (Object[] employer : Model.getAllEmployer()) { // Parcourir la liste des employés
            View.getModelTableau().addRow(employer); // Ajouter chaque employé au tableau
        }
    }
}
