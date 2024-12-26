package Model;
import DAO.HolidayDAOImpl;
import java.sql.SQLException;
import java.util.List;

public class HolidayModel {
    private HolidayDAOImpl dao;
    private HolidayDAOImpl.Holiday Holiday;

    // Constructeur qui prend un objet HolidayDAOImpl
    public HolidayModel(HolidayDAOImpl dao) {
        this.dao = dao;
    }

    // Méthode pour ajouter un congé
    public void addHoliday(Model.Holiday holiday) {
        try {
            if (holiday != null && holiday.getType() != null && holiday.getStartDate() != null && holiday.getEndDate() != null) {
                dao.add(Holiday); // Ajouter le congé via le DAO
            } else {
                System.out.println("Les informations du congé sont incomplètes.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du congé : " + e.getMessage());
        }
    }

    // Méthode pour mettre à jour un congé
    public void updateHoliday(Model.Holiday holiday) {
        try {
            if (holiday != null) {
                dao.update(Holiday); // Mettre à jour le congé via le DAO
            } else {
                System.out.println("Le congé à mettre à jour est null.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du congé : " + e.getMessage());
        }
    }

    // Méthode pour trouver un congé par ID
    public HolidayDAOImpl.Holiday findHolidayById(int id) {
        try {
            return dao.findById(id);  // Trouver le congé par ID via le DAO
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche du congé : " + e.getMessage());
            return null;
        }
    }

    // Méthode pour obtenir tous les congés
    public List<HolidayDAOImpl.Holiday> getAllHolidays() {
        try {
            return dao.getAll();  // Récupérer tous les congés via le DAO
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des congés : " + e.getMessage());
            return null;
        }
    }

    // Méthode pour supprimer un congé
    public void deleteHoliday(int id) {
        try {
            dao.delete(id);  // Supprimer un congé via le DAO
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du congé : " + e.getMessage());
        }
    }
}
