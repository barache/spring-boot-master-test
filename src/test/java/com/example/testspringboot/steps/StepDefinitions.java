package com.example.testspringboot.steps;

import com.example.testspringboot.config.TestConfig;
import com.example.testspringboot.entity.Acteur;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
@ContextConfiguration(classes = TestConfig.class)

public class StepDefinitions {

    private Response response;
    private final String POST_FILM_ENDPOINT = "http://localhost:8080/api/film/";
    private final String GET_FILM_ENDPOINT = "http://localhost:8080/api/film/1";

    private final String PUT_FILM_ENDPOINT = "http://localhost:8080/api/film/1";
    private final String DELETE_FILM_ENDPOINT = "http://localhost:8080/api/film/1";
    private String requestBody;

    private final String FILM_DETAIL = "{\"titre\":\"Star Wars: The Empire Strikes Back\"," +
            "\"description\":\"Darth Vader is adamant about turning Luke Skywalker to the dark side.\"," +
            "\"acteurs\":[{\"nom\":\"Hamill\",\"prenom\":\"Mark\"}," +
            "{\"nom\":\"Ford\",\"prenom\":\"Harrison\"}," +
            "{\"nom\":\"Fisher(\",\"prenom\":\"Carrie\"}]}";

    private final String FILM_UPDATE_DETAIL = "{\"id\":1,\"titre\":\"Star Wars: Episode IV - A New Hope\"," +
            "\"description\":\"Luke Skywalker joins forces with a Jedi Knight, a cocky pilot.\"," +
            "\"acteurs\":[{\"id\":1,\"nom\":\"Hamill\",\"prenom\":\"Mark\"}," +
            "{\"id\":2,\"nom\":\"Ford\",\"prenom\":\"Harrison\"}," +
            "{\"id\":3,\"nom\":\"Fisher(\",\"prenom\":\"Carrie\"}]}";


    @Given("I set request body")
    public void i_set_request_body() {
        requestBody = FILM_DETAIL;
    }

    @When("I send the POST HTTP request")
    public void i_send_the_post_http_request() {
        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(POST_FILM_ENDPOINT);
    }

    @Then("I receive valid HTTP response code 201")
    public void i_receive_valid_http_response_code_201() {
       assertEquals(201, response.getStatusCode());
    }

    @Then("I receive valid film details in the response")
    public void i_receive_valid_film_details_in_the_response() {

        JsonPath jsonPath = response.jsonPath();
        String titre = jsonPath.getString("titre");
        String description = jsonPath.getString("description");
        List<Acteur> acteurList = jsonPath.getList("acteurs");

        assertAll(
                "Grouped Assertions of Film",
                () -> assertEquals("Star Wars: The Empire Strikes Back", titre),
                () -> assertEquals("Darth Vader is adamant about turning Luke Skywalker to the dark side.",
                        description),
                () -> assertEquals(3, acteurList.size())
        );
    }

    @Given("I set the GET film service api endpoint")
    public void i_set_the_get_film_service_api_endpoint() {
        // The endpoint is already set as a class variable
    }

    @When("I send the GET HTTP request")
    public void i_send_the_get_http_request() {
        response = given().when().get(GET_FILM_ENDPOINT);
    }

    @Then("I receive valid HTTP response code 200")
    public void i_receive_valid_http_response_code_200() {
        response.then().statusCode(200);
    }

    @Then("I receive film details in the response")
    public void i_receive_film_details_in_the_response() {
        JsonPath jsonPath = response.jsonPath();
        String titre = jsonPath.getString("titre");
        String description = jsonPath.getString("description");
        List<Acteur> acteurList = jsonPath.getList("acteurs");

        assertAll(
                "Grouped Assertions of Film",
                () -> assertEquals("Star Wars: The Empire Strikes Back", titre),
                () -> assertEquals("Darth Vader is adamant about turning Luke Skywalker to the dark side.",
                        description),
                () -> assertEquals(3, acteurList.size())
        );
    }

    @Given("I set the PUT film service api endpoint")
    public void i_set_the_put_film_service_api_endpoint() {
        requestBody = FILM_UPDATE_DETAIL;
    }
    @When("I send the PUT HTTP request")
    public void i_send_the_put_http_request() {
        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put(PUT_FILM_ENDPOINT);
    }

    @Then("I receive film details updated in the response")
    public void i_receive_film_details_updated_in_the_response() {
        response.then().statusCode(200);
        JsonPath jsonPath = response.jsonPath();
        String titre = jsonPath.getString("titre");
        String description = jsonPath.getString("description");
        List<Acteur> acteurList = jsonPath.getList("acteurs");

        assertAll(
                "Grouped Assertions of Film",
                () -> assertEquals("Star Wars: Episode IV - A New Hope", titre),
                () -> assertEquals("Luke Skywalker joins forces with a Jedi Knight, a cocky pilot.",
                        description),
                () -> assertEquals(3, acteurList.size())
        );

    }


    @Given("I set the DELETE film service api endpoint")
    public void i_set_the_delete_film_service_api_endpoint() {
    }

    @When("I send the DELETE HTTP request")
    public void i_send_the_delete_http_request() {
        response = given()
                .when()
                .delete(DELETE_FILM_ENDPOINT);
    }
    @Then("I receive valid HTTP response code 204")
    public void i_receive_valid_http_response_code_204() {
        response.then().statusCode(204);
    }

}