package co.edu.udea.ceset.dto;

import co.edu.udea.ceset.dto.Budgetbyitem;
import co.edu.udea.ceset.dto.Estimatedbyitem;
import co.edu.udea.ceset.dto.Expenditurebyitem;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-28T12:04:04")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, Double> totalValue;
    public static volatile CollectionAttribute<Item, Estimatedbyitem> estimatedbyitemCollection;
    public static volatile SingularAttribute<Item, Double> realValue;
    public static volatile CollectionAttribute<Item, Expenditurebyitem> expenditurebyitemCollection;
    public static volatile SingularAttribute<Item, String> description;
    public static volatile CollectionAttribute<Item, Budgetbyitem> budgetbyitemCollection;
    public static volatile SingularAttribute<Item, String> type;
    public static volatile SingularAttribute<Item, Double> stimatedValue;
    public static volatile SingularAttribute<Item, Integer> idItem;

}