package Person;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class FakePerson {

    private final Faker faker;

    public FakePerson(Faker faker) {
        this.faker = faker;
        count++;

    }

    public static int count=0;


    public int getId(){
        return count;
    }
    public String getName(){
        return this.faker.name().fullName();
    }
    public  LocalDate getDate(){
        Date date;
        date = this.faker.date().birthday();
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
    public  Person.Gender getGender(){
        return this.faker.options().option(Person.Gender.values());
    }
    public  String getEmail(){
        return this.faker.internet().emailAddress();
    }
    public  String getPhone(){
        return this.faker.phoneNumber().cellPhone();
    }
    public  String getEProfession(){
        return this.faker.company().profession();
    }
    public boolean getMarried(){
        return this.faker.bool().bool();
    }
}
