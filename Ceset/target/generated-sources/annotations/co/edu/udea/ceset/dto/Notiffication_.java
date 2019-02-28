package co.edu.udea.ceset.dto;

import co.edu.udea.ceset.dto.Notifficationbycheck;
import co.edu.udea.ceset.dto.Notifficationbyrole;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-27T19:42:05")
@StaticMetamodel(Notiffication.class)
public class Notiffication_ { 

    public static volatile CollectionAttribute<Notiffication, Notifficationbycheck> notifficationbycheckCollection;
    public static volatile SingularAttribute<Notiffication, Integer> idNotif;
    public static volatile SingularAttribute<Notiffication, String> notiftype;
    public static volatile SingularAttribute<Notiffication, String> description;
    public static volatile CollectionAttribute<Notiffication, Notifficationbyrole> notifficationbyroleCollection;

}