package co.edu.udea.ceset.dto;

import co.edu.udea.ceset.dto.Academicactivity;
import co.edu.udea.ceset.dto.Budgetbyitem;
import co.edu.udea.ceset.dto.Cohort;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-27T19:42:05")
@StaticMetamodel(Budget.class)
public class Budget_ { 

    public static volatile SingularAttribute<Budget, Double> contributionsFaculty;
    public static volatile SingularAttribute<Budget, Double> contributionsUdeA;
    public static volatile SingularAttribute<Budget, Double> mprovisedBudget;
    public static volatile SingularAttribute<Budget, Integer> idBudget;
    public static volatile SingularAttribute<Budget, Academicactivity> idActivity;
    public static volatile CollectionAttribute<Budget, Budgetbyitem> budgetbyitemCollection;
    public static volatile SingularAttribute<Budget, Cohort> idCohort;
    public static volatile SingularAttribute<Budget, Double> totalRealBudget;

}