package co.edu.udea.ceset.dto;

import co.edu.udea.ceset.dto.Rolebypermission;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< Updated upstream
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-12-05T13:59:31")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-12-05T16:25:35")
>>>>>>> Stashed changes
@StaticMetamodel(Permission.class)
public class Permission_ { 

    public static volatile SingularAttribute<Permission, Integer> idPermission;
    public static volatile SingularAttribute<Permission, String> descripcion;
    public static volatile SingularAttribute<Permission, String> shortDescription;
    public static volatile CollectionAttribute<Permission, Rolebypermission> rolebypermissionCollection;

}