package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

@Test
public class User {
    String uri = "https://petstore.swagger.io/v2/user";

    // 3.2 - Métodos e Funções
    public String ler_json(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    public void incluirUser() throws IOException {
    String jsonBody = ler_json("db/user1.json");

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
