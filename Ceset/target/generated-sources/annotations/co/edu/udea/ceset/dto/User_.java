package co.edu.udea.ceset.dto;

import co.edu.udea.ceset.dto.Person;
import co.edu.udea.ceset.dto.Rolebyuser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-21T16:27:23")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Integer> idUser;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Date> dateCreation;
    public static volatile SingularAttribute<User, String> nameUser;
    public static volatile SingularAttribute<User, Person> idPerson;
    public static volatile CollectionAttribute<User, Rolebyuser> rolebyuserCollection;
    public static volatile SingularAttribute<User, String> states;

}