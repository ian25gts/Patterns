package data;

import com.github.javafaker.Faker;
import domian.RegistrationByCardInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {}

    public static class Registration {
        private Registration() {}

        public static String generateDate(int days) {
            return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public static RegistrationByCardInfo generateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationByCardInfo(faker.address().city(), faker.name().fullName(), faker.phoneNumber().phoneNumber());
        }
    }
 }