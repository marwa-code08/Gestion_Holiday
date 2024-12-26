package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Implémentation DAO pour gérer les congés
public class HolidayDAOImpl {

    private Connection connection;

    // Constructeur - initialisation de la connexion à la base de données
    public HolidayDAOImpl() {
        this.connection = connection;
    }

    // Ajouter un congé
    public void add(Holiday holiday) throws SQLException {
        String sql = "INSERT INTO holidays (employee, start_date, end_date, type) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, holiday.getEmployee());
            stmt.setString(2, holiday.getStartDate());
            stmt.setString(3, holiday.getEndDate());
            stmt.setString(4, holiday.getType());
            stmt.executeUpdate();
        }
    }

    // Supprimer un congé
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM holidays WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Mettre à jour un congé
    public void update(Holiday holiday) throws SQLException {
        String sql = "UPDATE holidays SET employee = ?, start_date = ?, end_date = ?, type = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, holiday.getEmployee());
            stmt.setString(2, holiday.getStartDate());
            stmt.setString(3, holiday.getEndDate());
            stmt.setString(4, holiday.getType());
            stmt.setInt(5, holiday.getId());
            stmt.executeUpdate();
        }
    }

    // Trouver un congé par ID
    public Holiday findById(int id) throws SQLException {
        String sql = "SELECT * FROM holidays WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Holiday(
                            rs.getInt("id"),
                            rs.getString("employee"),
                            rs.getString("start_date"),
                            rs.getString("end_date"),
                            rs.getString("type")
                    );
                }
            }
        }
        return null; // Retourne null si aucun congé n'est trouvé
    }

    // Récupérer tous les congés
    public List<Holiday> getAll() throws SQLException {
        List<Holiday> holidays = new ArrayList<>();
        String sql = "SELECT * FROM holidays";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                holidays.add(new Holiday(
                        rs.getInt("id"),
                        rs.getString("employee"),
                        rs.getString("start_date"),
                        rs.getString("end_date"),
                        rs.getString("type")
                ));
            }
        }
        return holidays;
    }

    // Classe interne représentant un congé
    public static class Holiday {
        private int id;
        private String employee;
        private String startDate;
        private String endDate;
        private String type;

        public Holiday(int id, String employee, String startDate, String endDate, String type) {
            this.id = id;
            this.employee = employee;
            this.startDate = startDate;
            this.endDate = endDate;
            this.type = type;
        }

        // Getters et setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmployee() {
            return employee;
        }

        public void setEmployee(String employee) {
            this.employee = employee;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
