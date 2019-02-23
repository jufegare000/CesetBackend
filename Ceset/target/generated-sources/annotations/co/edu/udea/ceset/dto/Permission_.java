package co.edu.udea.ceset.dto;

import co.edu.udea.ceset.dto.Rolebypermission;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-21T16:27:23")
@StaticMetamodel(Permission.class)
public class Permission_ { 

    public static volatile SingularAttribute<Permission, Integer> idPermission;
    public static volatile SingularAttribute<Permission, String> descripcion;
    public static volatile SingularAttribute<Permission, String> shortDescription;
    public static volatile CollectionAttribute<Permission, Rolebypermission> rolebypermissionCollection;

}