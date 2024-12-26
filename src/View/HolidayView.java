package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HolidayView extends JFrame {
    // Déclarations des composants
    public JComboBox<String> employeeComboBox;
    public JTextField startDateField;
    public JTextField endDateField;
    public JComboBox<String> typeComboBox;
    public JTable holidayTable;
    public JButton AjoutBtn;
    public JButton DeleteButton;
    public JButton UpdateButton;

    public HolidayView() {
        // Titre de la fenêtre
        setTitle("Gestion Des Employés et Congés");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Panneau supérieur (Formulaire de congés)
        JPanel topPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Champs du formulaire
        topPanel.add(new JLabel("Nom de l'employé :"));
        employeeComboBox = new JComboBox<>();
        topPanel.add(employeeComboBox);

        topPanel.add(new JLabel("Type :"));
        typeComboBox = new JComboBox<>(new String[]{"Congé payé", "Congé maladie", "Congé sans solde"});
        topPanel.add(typeComboBox);

        topPanel.add(new JLabel("Date de début :"));
        startDateField = new JTextField();
        topPanel.add(startDateField);

        topPanel.add(new JLabel("Date de fin :"));
        endDateField = new JTextField();
        topPanel.add(endDateField);

        add(topPanel, BorderLayout.NORTH);

        // Panneau central (Tableau des congés)
        String[] columnNames = {"Id", "Employé", "Date de début", "Date de fin", "Type"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        holidayTable = new JTable(model);

        JScrollPane tableScrollPane = new JScrollPane(holidayTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Panneau inférieur (Boutons d'action)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        AjoutBtn = new JButton("Ajouter");
        DeleteButton = new JButton("Supprimer");
        UpdateButton = new JButton("Modifier");

        bottomPanel.add(AjoutBtn);
        bottomPanel.add(DeleteButton);
        bottomPanel.add(UpdateButton);

        add(bottomPanel, BorderLayout.SOUTH);


    }


}
