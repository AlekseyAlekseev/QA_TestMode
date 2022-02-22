package ru.netology.web;

import org.junit.jupiter.api.Test;
import ru.netology.entities.RegistrationDto;
import ru.netology.utils.DataGenerator;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class AuthTest {

    @Test
    public void shouldUserActiveAuth() {
        RegistrationDto info = DataGenerator
                .Authorization
                .generateInfo("en", "active");

        open("http://localhost:9999/");
        $("[data-test-id=login] input").setValue(info.getName());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login] span").click();
    }

    @Test
    public void shouldUserBlockedAuth() {
        RegistrationDto info = DataGenerator
                .Authorization
                .generateInfo("en", "blocked");
        open("http://localhost:9999/");
        $("[data-test-id=login] input").setValue(info.getName());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login] span").click();
        $(".notification__title").shouldHave(text("Ошибка"));
        $(".notification__content").shouldHave(text("Ошибка! Пользователь заблокирован"));
    }

    @Test
    public void shouldInvalidLoginAuth() {
        RegistrationDto info = DataGenerator
                .Authorization
                .generateInfo("en", "active");
        open("http://localhost:9999/");
        $("[data-test-id=login] input").setValue("125151245124");
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login] span").click();
        $(".notification__title").shouldHave(text("Ошибка"));
        $(".notification__content").shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    public void shouldInvalidPasswordAuth() {
        RegistrationDto info = DataGenerator
                .Authorization
                .generateInfo("en", "active");
        open("http://localhost:9999/");
        $("[data-test-id=login] input").setValue(info.getName());
        $("[data-test-id=password] input").setValue("dfdfgsgdasgqwrq24e2");
        $("[data-test-id=action-login] span").click();
        $(".notification__title").shouldHave(text("Ошибка"));
        $(".notification__content").shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }
}
