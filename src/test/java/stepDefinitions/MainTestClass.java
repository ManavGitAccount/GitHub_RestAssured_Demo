package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import resources.TestDataBuilder;
import resources.Utility;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class MainTestClass extends Utility {

    TestDataBuilder testBuilder = new TestDataBuilder();
    public static String postString;
    public static String patchString;


    @Given("Add repo payload with {string}")
    public void add_repo_payload_with(String name) throws IOException {
        reqSpecFinal = given()
                .spec(requestSpecification())
                .body(testBuilder.addNamePayLoad(name));
    }


    @When("User calls {string} with {string} Http request")
    public void user_calls_with_http_request(String resource, String httpMethod) throws IOException {


        respSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();


        if(httpMethod.equalsIgnoreCase("POST")){
            System.out.println("Inside Post");
            responseObj = reqSpecFinal.when().post();
            postString = getGlobalValue("deleteUrl")+"/"+ getJsonPath(responseObj, "full_name");
            System.out.println("Value of the postString " + postString);
        }

        else if(httpMethod.equalsIgnoreCase("GET")){
            responseObj = reqSpecFinal.when().get();
            System.out.println("Inside Get");
        }

        else if(httpMethod.equalsIgnoreCase("PATCH")){

            System.out.println("Inside Patch");
            System.out.println("PostString inside patch = "  + postString);
            responseObj = reqSpecFinal.when().patch(postString);
            patchString = getGlobalValue("deleteUrl")+"/"+ getJsonPath(responseObj, "full_name");
            System.out.println("This is the value of post String: "  + patchString);
        }

        else if(httpMethod.equalsIgnoreCase("DELETE")){
            System.out.println("Inside Delete");
            responseObj = reqSpecFinal.when().delete(patchString);

        }

    }

    @Then("the API call is success with the status code {int}")
    public void the_api_call_is_success_with_the_status_code(int int1) {

        // System.out.println("This is int1 " +  int1);
        // System.out.println("Response Object  " + responseObj.getStatusCode());
        // System.out.println("Full Name of the repo " + getJsonPath(responseObj, "full_name"));

        assertEquals(responseObj.getStatusCode(), int1);
    }


    @Then("{string} in the response body is {string}")
    public void in_the_response_body_is(String keyValue, String expectedValue) {
        assertEquals(getJsonPath(responseObj, keyValue), expectedValue);
    }


    @Then("verify that")
    public void verify_that() {

    }

    @Given("Delete Repo Payload")
    public void delete_repo_payload() throws IOException {
        // I understand what I have to do here. First I am building the final reqSpec.
        // Passing all the variables to the requestSpecification. Then let things run.
        // Response Object is blank because there is nothing in it.
        reqSpecFinal = given()
                .spec(requestSpecification());
    }


    @Given("Patch Payload with {string}")
    public void patch_payload_with(String pathName) throws IOException {
        reqSpecFinal = given()
                .spec(requestSpecification())
                .body(testBuilder.addNamePayLoad(pathName));

        //System.out.println("Lets get the pathName " + pathName);
    }
}
