package test.java;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_GET_Request extends ExtentReportSetup
{

    String str_baseURI ="http://dummy.restapiexample.com/api/v1";

    @Test
    public void getEmployees()
    {
        test = extent.createTest("Fetch employees data");

        test.info("Base URI as: "  + str_baseURI);

        RestAssured.baseURI = str_baseURI;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        //GET
        Response response = request.get("/employees"); //"/employee/2");

        int statusCode = response.getStatusCode();
        test.pass("Expecting the status code should be 200");
        Assert.assertEquals(statusCode, 200);

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        //Verify Top 1 employee name = "Tiger Nixon";
        String expected_Employename =   "Tiger Nixon";
        String actual_Employeename = JsonPath.parse(responseBody).read("$.data[0].employee_name");
        Assert.assertEquals(actual_Employeename.toString(),expected_Employename.toString());

    }

}
