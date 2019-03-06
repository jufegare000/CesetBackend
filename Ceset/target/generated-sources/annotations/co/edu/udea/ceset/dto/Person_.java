package co.edu.udea.ceset.dto;

import co.edu.udea.ceset.dto.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-06T08:29:59")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, Integer> idPerson;
    public static volatile SingularAttribute<Person, String> documentType;
    public static volatile SingularAttribute<Person, String> document;
    public static volatile CollectionAttribute<Person, User> userCollection;
    public static volatile SingularAttribute<Person, String> completeName;
    public static volatile SingularAttribute<Person, String> email;

}