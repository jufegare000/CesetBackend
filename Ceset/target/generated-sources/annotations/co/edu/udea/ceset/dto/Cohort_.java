package co.edu.udea.ceset.dto;

import co.edu.udea.ceset.dto.Academicactivity;
import co.edu.udea.ceset.dto.Budget;
import co.edu.udea.ceset.dto.Themes;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-27T19:42:05")
@StaticMetamodel(Cohort.class)
public class Cohort_ { 

    public static volatile CollectionAttribute<Cohort, Budget> budgetCollection;
    public static volatile SingularAttribute<Cohort, Date> endDate;
    public static volatile SingularAttribute<Cohort, Date> initDate;
    public static volatile CollectionAttribute<Cohort, Themes> themesCollection;
    public static volatile SingularAttribute<Cohort, Academicactivity> idActivity;
    public static volatile SingularAttribute<Cohort, Integer> idCohort;
    public static volatile SingularAttribute<Cohort, String> states;

}