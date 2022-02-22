package ru.netology.utils;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;
import ru.netology.entities.RegistrationDto;

import java.util.Locale;

import static io.restassured.RestAssured.given;

@UtilityClass
public class DataGenerator {

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    @UtilityClass
    public static class Authorization {
        public static RegistrationDto generateInfo(String locale, String status) {
            Faker faker = new Faker(new Locale(locale));
            RegistrationDto registrationDto = new RegistrationDto(
                    faker.name().username(),
                    faker.internet().password(8, 10, false, false),
                    status);
            given() // "дано"
                    .spec(requestSpec) // указываем, какую спецификацию используем
                    .body(registrationDto) // передаём в теле объект, который будет преобразован в JSON
                    .when() // "когда"
                    .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                    .then() // "тогда ожидаем"
                    .statusCode(200); // код 200 OK
            return registrationDto;
        }
    }

}
