//1 - Pacote
package petstore;

//2 - Bibliotecas


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

//3 - Classe
public class Pet {
    //3.1 - Atributos
    String uri = "https://petstore.swagger.io/v2/pet";
    private Object IOException;

    // 3.2 - Métodos e Funções
    public String ler_json(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // Post
    @Test
    public void incluir_pet() throws IOException {
        String jsonBody = ler_json("db/pet1.json");

        //Sintaxe Gherkin
        //Dado - Quando - Então
        //Given - When - Then

        given()
                .contentType("application/json") //comum em APIs REST
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)

        .then()
                .log().all()
                .statusCode(200)
        ;



    }
    }

