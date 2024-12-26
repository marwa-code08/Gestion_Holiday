package Controller;

import Model.Holiday;
import View.HolidayView;
import Model.HolidayModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HolidayController {

    private HolidayView view;
    private HolidayModel model;

    // Constructeur du contrôleur
    public HolidayController(HolidayView view, HolidayModel model) {
        this.view = view;
        this.model = model;
        initialize();
    }

    // Initialisation des écouteurs d'événements pour les boutons et la sélection de ligne
    private void initialize() {
        // Action pour le bouton "Ajouter"
        view.AjoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addHoliday();
            }
        });

        // Action pour le bouton "Supprimer"
        view.DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteHoliday();
            }
        });

        // Action pour le bouton "Modifier"
        view.UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateHoliday();
            }
        });

        // Action pour la sélection d'une ligne dans la table
        view.holidayTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    populateFields();
                }
            }
        });
    }

    // Ajouter un congé
    private void addHoliday() {
        // Récupération des valeurs des champs de saisie
        String employee = (String) view.employeeComboBox.getSelectedItem();
        String leaveType = (String) view.typeComboBox.getSelectedItem();
        String startDateStr = view.startDateField.getText();
        String endDateStr = view.endDateField.getText();

        // Vérification de la validité des champs
        if (employee == null || leaveType == null || startDateStr.isEmpty() || endDateStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Conversion des dates au format approprié
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            // Création de l'objet Holiday
            Holiday holiday = new Holiday(0, employee, startDate, endDate, leaveType);

            // Ajout du congé via le modèle DAO
            model.addHoliday(holiday);

            // Mise à jour du tableau pour l'affichage
            DefaultTableModel tableModel = (DefaultTableModel) view.holidayTable.getModel();
            tableModel.addRow(new Object[]{
                    tableModel.getRowCount() + 1,
                    employee,
                    startDateStr,
                    endDateStr,
                    leaveType
            });

            // Effacement des champs de saisie après l'ajout
            view.startDateField.setText("");
            view.endDateField.setText("");

            // Confirmation d'ajout
            JOptionPane.showMessageDialog(view, "Congé ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(view, "Format de date invalide. Utilisez le format yyyy-MM-dd.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Supprimer un congé
    private void deleteHoliday() {
        int selectedRow = view.holidayTable.getSelectedRow();
        if (selectedRow != -1) {
            int holidayId = (int) view.holidayTable.getValueAt(selectedRow, 0); // Supposons que la première colonne est l'ID
            model.deleteHoliday(holidayId); // Supprimer via le modèle

            // Supprimer la ligne du tableau
            DefaultTableModel tableModel = (DefaultTableModel) view.holidayTable.getModel();
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un congé à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Mettre à jour un congé
    private void updateHoliday() {
        int selectedRow = view.holidayTable.getSelectedRow();
        if (selectedRow != -1) {
            int holidayId = (int) view.holidayTable.getValueAt(selectedRow, 0); // Supposons que la première colonne est l'ID
            String employee = (String) view.employeeComboBox.getSelectedItem();
            String leaveType = (String) view.typeComboBox.getSelectedItem();
            String startDateStr = view.startDateField.getText();
            String endDateStr = view.endDateField.getText();

            // Vérification de la validité des champs
            if (employee == null || leaveType == null || startDateStr.isEmpty() || endDateStr.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Convertir les chaînes de caractères en objets Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = dateFormat.parse(startDateStr);
                Date endDate = dateFormat.parse(endDateStr);

                // Créer un objet Holiday
                Holiday holiday = new Holiday(holidayId, employee, startDate, endDate, leaveType);

                model.updateHoliday(holiday); // Mettre à jour via le modèle

                // Mise à jour de la ligne dans le tableau
                DefaultTableModel tableModel = (DefaultTableModel) view.holidayTable.getModel();
                tableModel.setValueAt(employee, selectedRow, 1);
                tableModel.setValueAt(startDateStr, selectedRow, 2);
                tableModel.setValueAt(endDateStr, selectedRow, 3);
                tableModel.setValueAt(leaveType, selectedRow, 4);

                // Effacer les champs après la mise à jour
                view.startDateField.setText("");
                view.endDateField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Format de date invalide: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un congé à modifier.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Remplir les champs avec les informations du congé sélectionné
    private void populateFields() {
        int selectedRow = view.holidayTable.getSelectedRow();
        if (selectedRow != -1) {
            String employee = (String) view.holidayTable.getValueAt(selectedRow, 1);
            String startDate = (String) view.holidayTable.getValueAt(selectedRow, 2);
            String endDate = (String) view.holidayTable.getValueAt(selectedRow, 3);
            String leaveType = (String) view.holidayTable.getValueAt(selectedRow, 4);

            // Remplir les champs de saisie avec les données du congé sélectionné
            view.employeeComboBox.setSelectedItem(employee);
            view.startDateField.setText(startDate);
            view.endDateField.setText(endDate);
            view.typeComboBox.setSelectedItem(leaveType);
        }
    }
}
