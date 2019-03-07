package co.edu.udea.ceset.dto;

import co.edu.udea.ceset.dto.Budget;
import co.edu.udea.ceset.dto.Cohort;
import co.edu.udea.ceset.dto.Discount;
import co.edu.udea.ceset.dto.Estimated;
import co.edu.udea.ceset.dto.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-06T10:00:00")
@StaticMetamodel(Academicactivity.class)
public class Academicactivity_ { 

    public static volatile SingularAttribute<Academicactivity, Integer> durationMonths;
    public static volatile SingularAttribute<Academicactivity, String> contractEntity;
    public static volatile SingularAttribute<Academicactivity, String> investigationGroup;
    public static volatile SingularAttribute<Academicactivity, String> contactTelephone;
    public static volatile SingularAttribute<Academicactivity, String> dependency;
    public static volatile SingularAttribute<Academicactivity, String> contactEmail;
    public static volatile SingularAttribute<Academicactivity, String> contractType;
    public static volatile SingularAttribute<Academicactivity, Date> contractInit;
    public static volatile SingularAttribute<Academicactivity, Date> contractEnd;
    public static volatile CollectionAttribute<Academicactivity, Cohort> cohortCollection;
    public static volatile SingularAttribute<Academicactivity, Date> creationDate;
    public static volatile CollectionAttribute<Academicactivity, Discount> discountCollection;
    public static volatile SingularAttribute<Academicactivity, Integer> idAcad;
    public static volatile CollectionAttribute<Academicactivity, Estimated> estimatedCollection;
    public static volatile SingularAttribute<Academicactivity, User> idUser;
    public static volatile SingularAttribute<Academicactivity, String> nameActivity;
    public static volatile CollectionAttribute<Academicactivity, Budget> budgetCollection;
    public static volatile SingularAttribute<Academicactivity, String> state;
    public static volatile SingularAttribute<Academicactivity, String> activityType;
    public static volatile SingularAttribute<Academicactivity, String> coordinatorName;

}