package DAO;
import Model.*;

import java.util.ArrayList;

public interface EmployerDAOI {
     void ajouterEmployer (Employer NvEmployer );
     void modifierEmployer(Employer NvEmployer );
     void supprimerEmployer(int Id);
     ArrayList<Employer> getAllEmployer();
}
