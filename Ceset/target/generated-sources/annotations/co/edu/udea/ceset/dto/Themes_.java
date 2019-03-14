package co.edu.udea.ceset.dto;

import co.edu.udea.ceset.dto.Cohort;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-10T23:28:41")
@StaticMetamodel(Themes.class)
public class Themes_ { 

    public static volatile SingularAttribute<Themes, String> universityLink;
    public static volatile SingularAttribute<Themes, Integer> hours;
    public static volatile SingularAttribute<Themes, String> responsbileDocument;
    public static volatile SingularAttribute<Themes, Date> endDate;
    public static volatile SingularAttribute<Themes, String> dependency;
    public static volatile SingularAttribute<Themes, Date> initDate;
    public static volatile SingularAttribute<Themes, String> responsibleEmail;
    public static volatile SingularAttribute<Themes, String> responsible;
    public static volatile SingularAttribute<Themes, String> contactNumber;
    public static volatile SingularAttribute<Themes, String> description;
    public static volatile SingularAttribute<Themes, Cohort> idCohort;
    public static volatile SingularAttribute<Themes, Integer> idTheme;

}