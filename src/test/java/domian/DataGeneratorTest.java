package domian;
import com.codeborne.selenide.Condition;
import data.DataGenerator;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

 class DataGeneratorTest {

     @Test
     public void shouldFrd() {
         RegistrationByCardInfo info = DataGenerator.Registration.generateInfo("ru");
         String planningDay1 = DataGenerator.Registration.generateDate(3);
         String planningDay2 = DataGenerator.Registration.generateDate(7);

         open("http://localhost:9999");
         $("[data-test-id='city'] input").setValue(info.getCity());
         $("[data-test-id='date'] input").doubleClick().sendKeys(planningDay1);
         $("[data-test-id='name'] input").setValue(info.getName());
         $("[data-test-id='phone'] input").setValue(info.getPhoneNumber());
         $("[data-test-id='agreement']").click();
         $$("button").find(exactText("Запланировать")).click();
         $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
         $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + planningDay1), Duration.ofSeconds(15));
         closeWebDriver();
         open("http://localhost:9999");
         $("[data-test-id='city'] input").setValue(info.getCity());
         $("[data-test-id='date'] input").doubleClick().sendKeys(planningDay2);
         $("[data-test-id='name'] input").setValue(info.getName());
         $("[data-test-id='phone'] input").setValue(info.getPhoneNumber());
         $("[data-test-id='agreement']").click();
         $$("button").find(exactText("Запланировать")).click();
         $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(visible, Duration.ofSeconds(10));
         $$("button").find(exactText("Перепланировать")).click();
         $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
         $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + planningDay2), Duration.ofSeconds(15));
     }
 }
