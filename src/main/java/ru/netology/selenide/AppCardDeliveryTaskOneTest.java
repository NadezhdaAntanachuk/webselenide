package ru.netology.selenide;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class AppCardDeliveryTaskOneTest {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldBeSuccessfullyCompleted() {
        open("http://localhost:9999");
        $(cssSelector("[data-test-id='city'] input")).setValue("Тверь");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $(cssSelector("[data-test-id='date'] input")).sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $(cssSelector("[data-test-id='date'] input")).setValue(planningDate);
        $(cssSelector("[data-test-id='name'] input")).setValue("Иван Ромашкин");
        $(cssSelector("[data-test-id='phone'] input")).setValue("+79270000000");
        $(cssSelector("[data-test-id='agreement']")).click();
        $(cssSelector("button.button")).click();
        $(cssSelector("div.notification__content"))
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно забронирована на " + planningDate));
    }

}
