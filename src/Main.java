
import Model.*;
import View.*;
import DAO.*;
import Controller.*;
import Model.EmployerModel;
import View.employerView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        EmployerDAOimpl DAO = new EmployerDAOimpl();
        EmployerModel Model = new EmployerModel(DAO);
        employerView View = new employerView();
        EmployerController Controller = new EmployerController( Model,View);        // Initialisation des DAO pour les employés et les congés

        HolidayDAOImpl daoHoliday = new HolidayDAOImpl();
        HolidayModel holidayModel = new HolidayModel(daoHoliday);
         new HolidayView();
         new GestionInterface();





    }
}
