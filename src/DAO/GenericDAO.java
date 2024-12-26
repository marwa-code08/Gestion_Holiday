package DAO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class GenericDAO<T> {

    private Connection connection;
    private String tableName;
    private Class<T> entityClass;

    // Constructeur - initialisation de la connexion à la base de données et du nom de la table
    public GenericDAO(Connection connection, Class<T> entityClass, String tableName) {
        this.connection = connection;
        this.entityClass = entityClass;
        this.tableName = tableName;
    }

    // Ajouter une entité
    public void add(T entity) throws SQLException {
        // Logique pour insérer l'entité dans la base de données
        // Utiliser l'Entity's Fields et la réflexion (Reflection) pour construire la requête
        String sql = "INSERT INTO " + tableName + " VALUES (?, ?, ?, ?)"; // Exemple générique, à adapter

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Remplir les valeurs du PreparedStatement ici
            // stmt.setX(1, value);
            stmt.executeUpdate();
        }
    }

    // Supprimer une entité par son identifiant
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Mettre à jour une entité
    public void update(int id, T entity) throws SQLException {
        // Logique pour mettre à jour une entité
        // Exemple générique
        String sql = "UPDATE " + tableName + " SET column1 = ?, column2 = ? WHERE id = ?"; // Adapter selon l'entité

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Remplir les valeurs du PreparedStatement avec l'entité
            // stmt.setX(1, value);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

    // Récupérer une entité par son identifiant
    public T getById(int id) throws SQLException {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        T entity = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Remplir l'objet 'entity' avec les résultats de la requête
                // Par exemple, utiliser reflection pour créer l'objet
            }
        }

        return entity;
    }

    // Récupérer toutes les entités
    public List<T> getAll() throws SQLException {
        List<T> entities = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Créer l'objet 'entity' à partir du ResultSet
                T entity = null;
                // Remplir 'entity' avec les valeurs du ResultSet
                entities.add(entity);
            }
        }

        return entities;
    }
}
