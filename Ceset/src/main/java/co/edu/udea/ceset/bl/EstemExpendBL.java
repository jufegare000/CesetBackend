package co.edu.udea.ceset.bl;

import co.edu.udea.ceset.dao.EstimatedbyexpenditureDAO;
import co.udea.edu.co.dto.entities.Estimatedbyexpenditure;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EstemExpendBL {
    private static EstemExpendBL singletonInstance = new EstemExpendBL();
    private final String nombrePU = "ceset_PU";


    public static EstemExpendBL getInstance() {
        if (singletonInstance == null) { // Single Checked
            synchronized (EstemExpendBL.class) {
                if (singletonInstance == null) { // Double checked
                    singletonInstance = new EstemExpendBL();
                }
            }
        }
        return singletonInstance;
    }

    private EstimatedbyexpenditureDAO obtenerDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        EstimatedbyexpenditureDAO DAO = new EstimatedbyexpenditureDAO(emf);
        return DAO;
    }

    public Estimatedbyexpenditure crear(Estimatedbyexpenditure estd) {
        return obtenerDAO().create(estd);
    }

}
