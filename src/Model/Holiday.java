package Model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Holiday {

    private int id; // Identifiant du congé
    private String employeeName; // Nom de l'employé
    private Date startDate; // Date de début du congé
    private Date endDate; // Date de fin du congé
    private String type; // Type de congé (ex : Congé payé, Congé maladie, etc.)

    // Constructeur
    public Holiday(int id, String employeeName, Date startDate, Date endDate, String type) {
        this.id = id;
        this.employeeName = employeeName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", type='" + type + '\'' +
                '}';
    }

    // Méthode pour calculer le nombre de jours entre les dates de début et de fin
    public int getDays() {
        // Créer un formatteur de date pour convertir Date en String
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Convertir les dates en String
        String startDateString = sdf.format(this.startDate);
        String endDateString = sdf.format(this.endDate);

        // Créer un DateTimeFormatter pour analyser la chaîne de caractères
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Convertir les String en LocalDate
        LocalDate start = LocalDate.parse(startDateString, formatter);
        LocalDate end = LocalDate.parse(endDateString, formatter);

        // Retourner le nombre de jours entre les deux dates
        return (int) ChronoUnit.DAYS.between(start, end);
    }
}
