package View;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import enums.*;
public class employerView extends JFrame {
    private JPanel pan = new JPanel();
    private JPanel pan1 = new JPanel();
    private JPanel pan2 = new JPanel();
    private JPanel pan3 = new JPanel();

    public JTextField nomField = new JTextField();
    public JTextField prenomField = new JTextField();
    public JTextField EmailField = new JTextField();
    public JTextField TLField = new JTextField();
    public JTextField SLField = new JTextField();
    public JComboBox<Role> RoleComboBox = new JComboBox<>(Role.values());
    public JComboBox<Poste> PosteComboBox = new JComboBox<>(Poste.values());

    private JTable table;  // Déclaration du JTable
    public DefaultTableModel model;  // Modèle de la table
    private JScrollPane scrollPane;  // Barre de défilement pour le JTable



    public JButton AjoutBtn = new JButton("Ajouter ");
    public JButton ModifBtn = new JButton("Modifier");
    public JButton SuppBtn = new JButton("Supprimer ");
    public JButton AffichBtn = new JButton("Afficher");

    public employerView(){
        setTitle("Gestion des employés ");
        setSize(700,500);
        add(pan);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pan.setLayout(new BorderLayout());
        pan.add(pan1,BorderLayout.NORTH);
        pan.add(pan2,BorderLayout.CENTER);
        pan.add(pan3,BorderLayout.SOUTH);

        pan1.setLayout(new GridLayout(7,2));
        pan1.add(new JLabel("Nom"));
        pan1.add(nomField);
        pan1.add(new JLabel("Prenom"));
        pan1.add(prenomField);
        pan1.add(new JLabel("Email"));
        pan1.add(EmailField);
        pan1.add(new JLabel("Telephone"));
        pan1.add(TLField);
        pan1.add(new JLabel("Salaire"));
        pan1.add(SLField);
        pan1.add(new JLabel("Role"));
        pan1.add(RoleComboBox);
        pan1.add(new JLabel("Poste"));
        pan1.add(PosteComboBox);

        pan2.setLayout(new BorderLayout());
        String[] columnNames = {"Id","Nom", "Prenom", "Telephone", "Email", "Salaire", "Role", "Poste"};
        model = new DefaultTableModel(columnNames,0);
        // Créer le JTable avec  le modèle
        table = new JTable(model);
        // Ajouter une barre de défilement au JTable
        scrollPane = new JScrollPane(table);
        pan2.add(scrollPane, BorderLayout.CENTER);

        pan3.setLayout(new FlowLayout());
        pan3.add(AjoutBtn);
        pan3.add(ModifBtn);
        pan3.add(SuppBtn);
        pan3.add(AffichBtn);



    }

    //methode pour récupérer le texte saisi dans les champs
    public String getNom() {return nomField.getText();}
    public String getPrenom() {return prenomField.getText();}
    public String  getEmail() { return EmailField.getText();};

    public String getTelephone(){ return TLField.getText();}
    public double getSalaire() {
        return Double.parseDouble(SLField.getText());
    }
    public Role getRole() {
        return (Role) RoleComboBox.getSelectedItem();
    }

    public Poste getPoste() {
        return (Poste) PosteComboBox.getSelectedItem();
    }



    // Méthode pour obtenir les noms des employés
    public ArrayList<String> getEmployeeNames() {
        ArrayList<String> employeeNames = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String nom = (String) model.getValueAt(i, 0); // Colonne 0 : Nom
            employeeNames.add(nom);
        }
        return employeeNames;
    }



    //methode pour afficher un message d'erreur dans une boite de dialogue
    public void afficheMessageErreur  (String message){
        JOptionPane.showMessageDialog(this,message, "erreur", JOptionPane.ERROR_MESSAGE);
    }
    public void afficheMessageSucces ( String message){
        JOptionPane.showMessageDialog(this,message,"Succes",JOptionPane.INFORMATION_MESSAGE);

    }


    public DefaultTableModel getModelTableau() {
        return model;
    }

    public JTable getTableauEmployer() {
        return table;
    }
}

