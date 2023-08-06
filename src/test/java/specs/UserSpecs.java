package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;

public class UserSpecs {
    public static RequestSpecification signInPostRequestSpecification = with()
            .header("Content-Type", "application/json; charset=utf-8")
            .log().all()
            .filter(withCustomTemplates());
    public static ResponseSpecification postResponseSpecification = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .build();
    public static RequestSpecification getRequestSpecification = with()
            .header("Content-Type", "application/vnd.api+json")
            .log().all()
            .filter(withCustomTemplates());
    public static ResponseSpecification getResponseSpecification = new ResponseSpecBuilder()
            .log(BODY)
            .log(STATUS)
            .expectStatusCode(200)
            .build();
    public static RequestSpecification signUpPostRequestSpecification = with()
            .header("Content-Type", "application/vnd.api+json")
            .log().all()
            .filter(withCustomTemplates());
    public static RequestSpecification patchRequestSpecification = with()
            .header("Content-Type", "application/json; charset=utf-8")
            .log().all()
            .filter(withCustomTemplates());
}
