package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utility {

    protected RequestSpecification reqSpecFinal;
    public static RequestSpecification reqSpecInitial;

    public static final String TOKEN = "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";

    protected ResponseSpecification respSpec;
    protected Response responseObj;
    
    JsonPath js;

    public RequestSpecification requestSpecification() throws IOException {

        //try with header added here and also header coming from TestDataBuilder
        //class you will create.

        if(reqSpecInitial == null){

            PrintStream logData = new PrintStream(new FileOutputStream("logging.txt"));
            reqSpecInitial = new RequestSpecBuilder()
                    .setBaseUri(getGlobalValue("baseUrl"))
                    .addHeader("Authorization", "token " + TOKEN)
                    .addFilter(RequestLoggingFilter.logRequestTo(logData))
                    .addFilter(ResponseLoggingFilter.logResponseTo(logData))
                    .setContentType(ContentType.JSON).build();
            return reqSpecInitial;
        }

        return reqSpecInitial;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties propFile = new Properties();
        FileInputStream fileInputStream = new FileInputStream
                ("C:\\Users\\manav\\RestAPI_Example_InHome\\src\\test\\java\\resources\\global.properties");
        propFile.load(fileInputStream);
        return propFile.getProperty(key).toString();
    }

    public String getJsonPath(Response responseObj, String key){

        // Convert the Response Object to String
        String respStr = responseObj.asString();

        // Then create a new object js.
        js = new JsonPath(respStr);

        //Here using the js object that has been converted to a string,
        // we get the key value and return it.
        // are returning a string
        return js.get(key).toString();
    }

}
