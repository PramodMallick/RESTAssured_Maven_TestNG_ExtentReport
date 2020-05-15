package test.java;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Test_PUT_Request extends ExtentReportSetup
{

    String str_baseURI ="http://dummy.restapiexample.com/api/v1";
    String path = "/create";

    @Test
    public void RegistrationSuccessful()
    {
        test = extent.createTest("Create employee data");

        RestAssured.baseURI = str_baseURI;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        // We can add Key - Value pairs using the put method
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Pramod kumar mallick");
        requestParams.put("salary", "100");

        request.body(requestParams.toJSONString());

        // Post the request and check the response
        Response response = request.post("/create");

        int statusCode = response.getStatusCode();

        test.pass("Expecting the status code should be 200");
        Assert.assertEquals(statusCode, 200);

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        String newIDCreated = JsonPath.parse(responseBody.toString()).read("$.data.id").toString();
        System.out.println(newIDCreated);
        test.info( "New ID created as :"  + newIDCreated);

    }

}
