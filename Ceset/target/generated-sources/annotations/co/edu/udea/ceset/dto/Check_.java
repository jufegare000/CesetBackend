package co.edu.udea.ceset.dto;

import co.edu.udea.ceset.dto.Expenditure;
import co.edu.udea.ceset.dto.Notifficationbycheck;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-01T11:36:40")
@StaticMetamodel(Check.class)
public class Check_ { 

    public static volatile SingularAttribute<Check, Expenditure> idExpend;
    public static volatile CollectionAttribute<Check, Notifficationbycheck> notifficationbycheckCollection;
    public static volatile SingularAttribute<Check, Double> idRealValue;
    public static volatile SingularAttribute<Check, String> description;
    public static volatile SingularAttribute<Check, String> state;
    public static volatile SingularAttribute<Check, Integer> idCheck;

}