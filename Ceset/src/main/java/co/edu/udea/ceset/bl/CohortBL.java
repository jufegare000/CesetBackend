package co.edu.udea.ceset.bl;

import co.edu.udea.ceset.dao.CohortDAO;
import co.edu.udea.ceset.dao.EstimatedbyexpenditureDAO;
import co.edu.udea.ceset.dto.CohortDTO;
import co.edu.udea.ceset.utilities.Utilities;
import co.udea.edu.co.dto.entities.Cohort;
import co.udea.edu.co.dto.entities.Estimatedbyexpenditure;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CohortBL {
    private static CohortBL singletonInstance = new CohortBL();
    private final String nombrePU = "ceset_PU";


    public static CohortBL getInstance() {
        if (singletonInstance == null) { // Single Checked
            synchronized (EstemExpendBL.class) {
                if (singletonInstance == null) { // Double checked
                    singletonInstance = new CohortBL();
                }
            }
        }
        return singletonInstance;
    }

    private CohortDAO obtenerDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        CohortDAO DAO = new CohortDAO(emf);
        return DAO;
    }

    public String crear(CohortDTO entry) {
        String retorno;
        Cohort crt;
        ModelMapper modelMapper = new ModelMapper();
        Cohort cort = modelMapper.map(entry, Cohort.class);
        crt = obtenerDAO().create(cort);
        retorno = Utilities.jasonizer(crt);
        return retorno;
    }

    public Cohort getById(int n){
        return obtenerDAO().findCohort(n);
    }

}
