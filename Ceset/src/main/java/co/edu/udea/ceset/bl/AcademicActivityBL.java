package co.edu.udea.ceset.bl;

import co.edu.udea.ceset.dao.*;
import co.edu.udea.ceset.dto.AcademicActivityDTO;
import co.edu.udea.ceset.dto.BudgetDTO;
import co.edu.udea.ceset.dto.EstimatedByExpenditureDTO;
import co.edu.udea.ceset.dto.entities.*;
import co.edu.udea.ceset.utilities.Utilities;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.modelmapper.ModelMapper;

/**
 * Clase de la Lógica del negocio para Usuarios
 *
 * @author edevargas
 */
public class AcademicActivityBL implements Serializable {

    private static final long serialVersionUID = 4044273845736534004L;
    private static AcademicActivityBL singletonInstance = new AcademicActivityBL();
    private final String nombrePU = "ceset_PU";

    private AcademicActivityBL() {
    }

    public static AcademicActivityBL getInstance() {
        if (singletonInstance == null) { // Single Checked
            synchronized (AcademicActivityBL.class) {
                if (singletonInstance == null) { // Double checked
                    singletonInstance = new AcademicActivityBL();
                }
            }
        }
        return singletonInstance;
    }

    /**
     * Replacing the stream object with the same instance object, to maintain
     * singleton.
     *
     * @return
     */
    protected Object readResolve() {
        return getInstance();
    }

    public String obtenerTodods() {
        String parsed;
        Collection<Academicactivity> todas = this.obtenerAcadDAO().findAcademicactivityEntities();
        for (Academicactivity acad : todas) {
            this.organizaActividad(acad);
        }
        parsed = Utilities.jasonizer(todas);
        return parsed;
    }

    public void organizaActividad
            (Academicactivity acad) {
        acad.getIdUser().setPassword("");
        acad.getIdUser().setIdPerson(null);
        acad.getIdUser().setRolebyuserCollection(null);
        acad.getIdUser().setAcademicactivityCollection(null);
    }

    /**
     * Método que rotorna el DAO para Actividades Académicas
     *
     * @return AcademicactivityDAO
     */
    private AcademicactivityDAO obtenerAcadDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        AcademicactivityDAO DAO = new AcademicactivityDAO(emf);
        return DAO;
    }

    private EstimatedDAO obtenerEstmdDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        EstimatedDAO DAO = new EstimatedDAO(emf);
        return DAO;
    }

    private EstimatedbyexpenditureDAO obtenerEstmdByExpndDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        EstimatedbyexpenditureDAO DAO = new EstimatedbyexpenditureDAO(emf);
        return DAO;
    }

    private DiscountDAO obtenerDiscDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        DiscountDAO DAO = new DiscountDAO(emf);
        return DAO;
    }

    private GroupeDAO obtenerGroupDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        GroupeDAO DAO = new GroupeDAO(emf);
        return DAO;
    }

    private BudgetDAO obtenerBudgetDAO(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        BudgetDAO DAO = new BudgetDAO(emf);
        return DAO;
    }

    private BudgetbyexpenditureDAO obtenerBudgetByExpendDAO(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        BudgetbyexpenditureDAO DAO = new BudgetbyexpenditureDAO(emf);
        return DAO;
    }

    private ThemesDAO obtenerTemasDAO(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        ThemesDAO DAO = new ThemesDAO(emf);
        return DAO;
    }

    public String crear(AcademicActivityDTO academicactivity) {
        Academicactivity acadCreado;
        int idAcadNueva;
        String retorno;
        Estimated estimado;
        Collection<Discount> descuentos;
        Groupe grupo;
        ModelMapper modelMapper = new ModelMapper();
        // La actividad se crea en cascada, primero se crean los datos de cabecera,
        // Se captura el id del objeto recién creado y se setea a los hijos su valor
        Academicactivity acad = modelMapper.map(academicactivity, Academicactivity.class);
        Academicactivity paracrear = new Academicactivity();
        this.setearValores(paracrear, acad);
        acadCreado = obtenerAcadDAO().create(paracrear);
        idAcadNueva = acadCreado.getIdAcad();
        estimado = (Estimated)acad.getEstimatedCollection().toArray()[0];
        this.crearEstimado(estimado, idAcadNueva);
        descuentos = acad.getDiscountCollection();
        for(Discount aCrear: descuentos){
            this.crearDescuento(aCrear, idAcadNueva);
        }
        this.crearGrupos(acad.getGroupeCollection(), idAcadNueva);
        this.organizaActividad(acad);
        retorno = Utilities.jasonizer(acadCreado);

        return retorno;
    }

    public void crearEstimado(Estimated stmd, int idAcad){
        Estimated paraCrear  = this.setearEstimated(stmd, idAcad);
        int nuevoId = this.obtenerEstmdDAO().create(paraCrear);
        Collection<Estimatedbyexpenditure> lista = stmd.getEstimatedbyexpenditureCollection();
        for(Estimatedbyexpenditure actual: lista){
           actual.setIdEstimated(new Estimated(nuevoId));
           obtenerEstmdByExpndDAO().create(actual);
        }
    }

    public void crearDescuento(Discount disc, int idAcad){
        Discount nuevo = this.setearDiscount(disc, idAcad);
        this.obtenerDiscDAO().create(nuevo);
    }

    public void crearGrupos(Collection<Groupe> grupos, int idAcad){
        Groupe currentGroupe, grupoAcrear;
        Budget bdgCurrent, nuevoBdgt, bdgCreado;
        Collection<Budgetbyexpenditure> listaDeGastos;
        Collection<Themes> temas;
        for(Groupe actual: grupos){
            grupoAcrear = this.setearGrupos(actual);
            grupoAcrear.setIdAcad(new Academicactivity(idAcad));
            currentGroupe = this.obtenerGroupDAO().create(grupoAcrear);

            bdgCurrent = (Budget)actual.getBudgetCollection().toArray()[0];

            nuevoBdgt = this.setearBudget(bdgCurrent);
            nuevoBdgt.setIdGroup(new Groupe(currentGroupe.getIdGroup()));
            bdgCreado = this.obtenerBudgetDAO().create(nuevoBdgt);

            listaDeGastos = bdgCurrent.getBudgetbyexpenditureCollection();
            for (Budgetbyexpenditure currentBudgetByExp: listaDeGastos){
                currentBudgetByExp.setIdBudget(new Budget(bdgCreado.getIdBudget()));
                this.obtenerBudgetByExpendDAO().create(currentBudgetByExp);
            }

            temas = actual.getThemesCollection();
            for(Themes themeForADream: temas){
                themeForADream.setIdGroup(new Groupe(currentGroupe.getIdGroup()));
                this.obtenerTemasDAO().create(themeForADream);
            }
        }
    }

    public void setearValores(Academicactivity acad, Academicactivity vieja){
        acad.setIdPort(vieja.getIdPort());
        acad.setIdUser(vieja.getIdUser());
        acad.setActaCode(vieja.getActaCode());
        acad.setActivityType(vieja.getActivityType());
        acad.setCodReune(vieja.getCodReune());
        acad.setContactEmail(vieja.getContactEmail());
        acad.setNameActivity(vieja.getNameActivity());
        acad.setContactTelephone(vieja.getContactTelephone());
        acad.setState(vieja.getState());
        acad.setSigepCode(vieja.getSigepCode());
        acad.setObservaciones(vieja.getObservaciones());
        acad.setInvestigationGroup(vieja.getInvestigationGroup());
        acad.setInitDateact(vieja.getInitDateact());
        acad.setEndDateact(vieja.getEndDateact());
        acad.setDurationMonths(vieja.getDurationMonths());
        acad.setDependency(vieja.getDependency());
        acad.setCreationDate(vieja.getCreationDate());
        acad.setCoordinatorName(vieja.getCoordinatorName());
        acad.setContractType(vieja.getContractType());
        acad.setContractInit(vieja.getContractInit());
        acad.setContractEntity(vieja.getContractEntity());
        acad.setContractEnd(vieja.getContractEnd());
        acad.setCoConcept(vieja.getCoConcept());
        acad.setCoEntity(vieja.getCoEntity());
        acad.setCoValue(vieja.getCoValue());
    }

    public Estimated setearEstimated(Estimated estm, int idAcad){
        Estimated std = new Estimated();
        std.setIdAcad(new Academicactivity(idAcad));
        std.setContributionsFaculty(estm.getContributionsFaculty());
        std.setTotalBudget(estm.getTotalBudget());
        std.setImprovised((estm.getImprovised()));
        std.setContributionsUdeA(estm.getContributionsUdeA());
        return std;
    }

    public Discount setearDiscount(Discount disc, int idAcad){
        Discount nuevo = new Discount();
        nuevo.setIdAcad(new Academicactivity(idAcad));
        nuevo.setDescription(disc.getDescription());
        nuevo.setQuantity(disc.getQuantity());
        nuevo.setTotal(disc.getTotal());
        nuevo.setValuedis(disc.getValuedis());
        return nuevo;
    }

    public Groupe setearGrupos(Groupe actual){
        Groupe nuevo = new Groupe();
        nuevo.setStates(actual.getStates());
        nuevo.setSchedule(actual.getSchedule());
        nuevo.setNumbers(actual.getNumbers());
        nuevo.setInitDate(actual.getInitDate());
        nuevo.setFinDate(actual.getFinDate());
        nuevo.setClassroom(actual.getClassroom());
        return nuevo;
    }

    public Budget setearBudget(Budget bdg){
        Budget nuevo = new Budget();
        nuevo.setContributionsFaculty(bdg.getContributionsFaculty());
        nuevo.setContributionsUdeA(bdg.getContributionsUdeA());
        nuevo.setMprovisedBudget(bdg.getMprovisedBudget());
        nuevo.setTotalRealBudget(bdg.getTotalRealBudget());
        return  nuevo;
    }

    public Academicactivity getById(int n) {
        return obtenerAcadDAO().findAcademicactivity(n);
    }

}
