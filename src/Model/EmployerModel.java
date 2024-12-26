package Model;
import java.util.ArrayList;
import enums.*;
import DAO.*;

public class EmployerModel {

    private EmployerDAOimpl DAO ;

    public EmployerModel(EmployerDAOimpl DAO){

        this.DAO = DAO;
    }
    public boolean ajouterEmployer (int Id, String Nom, String Prenom , String Email , String Telephone , double Salaire ,Role Role , Poste Poste ) {

        // Méthode pour vérifier si l'email contient '@'
        if (!Email.contains("@.gmail.com")) {  // Vérifie si '@' est manquant
            System.err.println("L'email que vous avez entré est invalide.");
            return false; // Email invalide
        }
        // Méthode pour vérifier NumeroTl
        if (Telephone.length() != 10) {
            System.err.println("The phone number must contain exactly 10 digits.");
            return false;
        }
        Employer NvEmployer = new Employer(0, Nom, Prenom, Email, Telephone, Salaire, Role, Poste);
        DAO.ajouterEmployer (NvEmployer);
        return true;
    }
    //meme chose pour fct de modification
    public boolean modifierEmployer (int Id , String Nom , String Prenom , String Email , String Telephone , double Salaire , Role Role , Poste Poste ) {
        if (!Email.contains("@")) {  // Vérifie si '@' est manquant
            System.err.println("L'email que vous avez entré est invalide.");
            return false; // Email invalide
        }
        if (Telephone.length() != 10) {
            System.err.println("The phone number must contain exactly 10 digits.");
            return false;
        }
        Employer NvEmployer = new Employer(Id, Nom, Prenom, Email, Telephone, Salaire, Role, Poste);
        DAO.modifierEmployer (NvEmployer);
        return true;
    }
    public boolean supprimerEmployer(int id) {
        DAO.supprimerEmployer(id);
        return true;
    }
        public ArrayList<Object[]> getAllEmployer() {
            ArrayList<Employer> employers = DAO.getAllEmployer();
            ArrayList<Object[]> employeeData = new ArrayList<>();

            for (Employer NvEmployer : employers) {
                Object[] empData = new Object[8];
                empData[0] = NvEmployer.getId();
                empData[1] = NvEmployer.getNom();
                empData[2] = NvEmployer.getPrenom();
                empData[3] = NvEmployer.getEmail();
                empData[4] = NvEmployer.getTelephone();
                empData[5] = NvEmployer.getSalaire();
                empData[6] = NvEmployer.getRole();
                empData[7] = NvEmployer.getPoste();
                employeeData.add(empData);
            }
            return employeeData;
    }
}
