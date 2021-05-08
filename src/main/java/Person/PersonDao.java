package Person;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Person.class)

public interface PersonDao {
    @SqlUpdate("""
        CREATE TABLE person (
            id INTEGER PRIMARY KEY,
            name VARCHAR NOT NULL,
            birthDate DATE NOT NULL,
            gender VARCHAR NOT NULL,
            email VARCHAR NOT NULL,
            phone VARCHAR NOT NULL,
            profession VARCHAR NOT NULL,
            married BIT NOT NULL
        )
        """
    )
    void createTable();


    @SqlUpdate("INSERT INTO person VALUES (:id,:name,:birthDate,:gender,:email,:phone,:profession,:married)")
    void insertPerson(@BindBean Person person);

    @SqlQuery("SELECT * FROM person WHERE id = :id")
    Optional<Person> getPerson(@Bind("id") int id);

    @SqlUpdate("DELETE FROM person WHERE id = :id")
    void deletePerson(@BindBean Person person);

    @SqlQuery("SELECT * FROM person ORDER BY id")
    List<Person> listPersons();

}
