package co.edu.udea.ceset.dto;

import co.edu.udea.ceset.dto.Academicactivity;
import co.edu.udea.ceset.dto.Estimatedbyitem;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-27T19:42:05")
@StaticMetamodel(Estimated.class)
public class Estimated_ { 

    public static volatile SingularAttribute<Estimated, Double> improvised;
    public static volatile SingularAttribute<Estimated, Double> contributionsFaculty;
    public static volatile CollectionAttribute<Estimated, Estimatedbyitem> estimatedbyitemCollection;
    public static volatile SingularAttribute<Estimated, Double> contributionsUdeA;
    public static volatile SingularAttribute<Estimated, Double> totalBudget;
    public static volatile SingularAttribute<Estimated, Integer> idEstimated;
    public static volatile SingularAttribute<Estimated, Academicactivity> idAcad;

}