package Person;
import com.github.javafaker.Faker;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Slf4JSqlLogger;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.setSqlLogger(new Slf4JSqlLogger());
        Person person1 = new Person(100, "Zanan", LocalDate.of(1999,9,17), Person.Gender.MALE, "zananpech9@gmail.com", "+36202611068", "Student", false);
        Person person2 = new Person(101, "John", LocalDate.of(1999,9,17), Person.Gender.MALE, "zananpech9@gmail.com", "+36202611068", "Student", false);

        List<Person> personSets = jdbi.withExtension(PersonDao.class, dao -> {
            dao.createTable();
            int num = Integer.parseInt(args[0]);

            for(int i=0;i<num;i++){
                var faker = new Faker();
                var fakePerson = new FakePerson(faker);
                var person = Person.builder()
                        .id(fakePerson.getId())
                        .name(fakePerson.getName())
                        .birthDate(fakePerson.getDate())
                        .gender(fakePerson.getGender())
                        .email(fakePerson.getEmail())
                        .phone(fakePerson.getPhone())
                        .profession(fakePerson.getEProfession())
                        .married(fakePerson.getMarried())
                        .build();
                dao.insertPerson(person);
            }
            dao.insertPerson(person1);
            dao.insertPerson(person2);
            dao.getPerson(100);
            dao.deletePerson(person1);
            return dao.listPersons();
    });
        personSets.forEach(System.out::println);

    }

}
