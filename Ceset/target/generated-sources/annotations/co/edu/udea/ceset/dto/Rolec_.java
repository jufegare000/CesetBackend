package co.edu.udea.ceset.dto;

import co.edu.udea.ceset.dto.Notifficationbyrole;
import co.edu.udea.ceset.dto.Rolebypermission;
import co.edu.udea.ceset.dto.Rolebyuser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-27T19:42:05")
@StaticMetamodel(Rolec.class)
public class Rolec_ { 

    public static volatile SingularAttribute<Rolec, Date> createdAt;
    public static volatile SingularAttribute<Rolec, Integer> idRole;
    public static volatile SingularAttribute<Rolec, String> description;
    public static volatile CollectionAttribute<Rolec, Rolebyuser> rolebyuserCollection;
    public static volatile CollectionAttribute<Rolec, Rolebypermission> rolebypermissionCollection;
    public static volatile CollectionAttribute<Rolec, Notifficationbyrole> notifficationbyroleCollection;
    public static volatile SingularAttribute<Rolec, Date> updatedAt;
    public static volatile SingularAttribute<Rolec, String> states;

}