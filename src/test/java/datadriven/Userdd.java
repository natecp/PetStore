package datadriven;

import commons.Data;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

public class Userdd {
    String uri = "https://petstore.swagger.io/v2/user";
    Data data; // objeto que representa a classe commons.data

    //Métodos e funções
    @BeforeMethod
    public void setup() {
        data = new Data();
    }


@Test
    public void incluirUser() throws IOException {
        String jsonBody = data.ler_json("db/user1.json");

        String userId =
                given()
                        .contentType("application/json")
                        .log().all()
                        .body(jsonBody)

                .when()
                .post(uri)

                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("code", is(200))
                        .body("type", is("unknown"))
                        .extract()
                        .path("message")

                ;

        System.out.println("O userID é " + userId);

    }

}
