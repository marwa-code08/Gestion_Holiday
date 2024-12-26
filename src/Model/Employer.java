package Model;

import enums.*;

public class Employer {
    //Attributs
    private int Id;
    private String Nom ;
    private String Prenom ;
    private String Email ;
    private String Telephone;
    private double Salaire;
    private Role Role ;
    private Poste Poste;

    //Constructeur
    public Employer (Integer Id , String Nom , String Prenom , String Email , String Telephone , double Salaire , Role  Role , Poste Poste ){
        this.Id = Id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
        this.Telephone = Telephone;
        this.Salaire = Salaire;
        this.Role = Role;
        this.Poste= Poste ;
    }
        //getters
        public int getId() { return (Id);}
        public String getNom() { return (Nom);}
        public String getPrenom() {return (Prenom);}
        public String getEmail(){ return (Email);}
        public String getTelephone() {return (Telephone);}
        public double getSalaire() {return (Salaire);}

    public String getRole() {
        return Role.name();
    }

    public String getPoste() {
        return Poste.name();
    }

}
