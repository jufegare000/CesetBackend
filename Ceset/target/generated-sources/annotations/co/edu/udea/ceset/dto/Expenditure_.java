package co.edu.udea.ceset.dto;

import co.edu.udea.ceset.dto.Check;
import co.edu.udea.ceset.dto.Expenditurebyitem;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-28T12:04:04")
@StaticMetamodel(Expenditure.class)
public class Expenditure_ { 

    public static volatile SingularAttribute<Expenditure, String> typeE;
    public static volatile CollectionAttribute<Expenditure, Check> checkCollection;
    public static volatile SingularAttribute<Expenditure, Integer> idExpend;
    public static volatile SingularAttribute<Expenditure, Double> quantity1;
    public static volatile SingularAttribute<Expenditure, Double> quantity2;
    public static volatile CollectionAttribute<Expenditure, Expenditurebyitem> expenditurebyitemCollection;
    public static volatile SingularAttribute<Expenditure, String> description;
    public static volatile SingularAttribute<Expenditure, String> measureUnit;
    public static volatile SingularAttribute<Expenditure, Double> stimatedValue;

}