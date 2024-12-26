package View;


import javax.swing.*;

public class GestionInterface extends JFrame{



        private JTabbedPane tabbedPane;
        private employerView employerTab;
        private HolidayView holidayTab;

        public GestionInterface() {
            setTitle("Gestion des Employés et Congés");
            setSize(800, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Initialisation du JTabbedPane
            tabbedPane = new JTabbedPane();

            // Ajouter les deux vues comme des onglets
            employerTab = new employerView();
            holidayTab = new HolidayView();

            tabbedPane.addTab("Gestion des Employés", employerTab.getContentPane());
            tabbedPane.addTab("Gestion des Congés", holidayTab.getContentPane());

            // Ajouter le JTabbedPane à la fenêtre principale
            add(tabbedPane);
            setVisible(true);
        }



}
