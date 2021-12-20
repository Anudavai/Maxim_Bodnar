package com.webapi.stepdefs;

import com.mashape.unirest.http.HttpResponse;
import com.webapi.app.DropboxApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.IOException;

public class StepDefinitions {
    private static final String ACCESS_TOKEN = "KAgVZSvHNNUAAAAAAAAAARC8wRlgri1FKmcrGmEDrrAnbuI8yjUQc88PaAPA4gmg";
    private static final DropboxApp app = new DropboxApp(ACCESS_TOKEN);
    private static HttpResponse<String> lastResponse;

    // Upload
    @Given("I have a file {string}")
    public void iHaveAFile(String fileName) {
        Assert.assertTrue("File " + fileName + " is not exist to upload!", app.isFileExists(fileName));
    }

    @When("I upload file {string} to Dropbox")
    public void iUploadFileToDropbox(String fileName) throws IOException {
        HttpResponse<String> response = app.uploadFile(fileName);
        Assert.assertNotNull("There are no response!", response);

        int statusCode = response.getStatus();
        System.out.println("Status code: " + statusCode);
        Assert.assertEquals("Bad status code", statusCode, 200);

        JSONObject responseBody = new JSONObject(response.getBody());
        int spacesToIndentEachLevel = 2;
        System.out.println("Response body:\n" + responseBody.toString(spacesToIndentEachLevel));
        Assert.assertEquals(responseBody.get("name"), fileName);
    }

    @Then("I see that the file {string} has been uploaded successfully")
    public void iSeeThatTheFileHasBeenUploadedSuccessfully(String fileName) {
        Assert.assertTrue("Failed to upload file " + fileName + " to Dropbox", app.isFileUploaded(fileName));
    }

    // GetFileMetadata
    @Given("The file {string} is uploaded")
    public void theFileIsUploaded(String fileName) {
        Assert.assertTrue("File " + fileName + " was not uploaded to Dropbox", app.isFileUploaded(fileName));
    }

    @When("I request metadata of the file {string}")
    public void iRequestMetadataOfTheFile(String fileName) {
        lastResponse = app.getFileMetadata(fileName);
    }

    @Then("I receive metadata of the file {string}")
    public void iReceiveMetadataOfTheFile(String fileName) {
        Assert.assertNotNull("There are no response!", lastResponse);
        int statusCode = lastResponse.getStatus();
        System.out.println("Status code: " + statusCode);
        Assert.assertEquals("Bad status code", statusCode, 200);

        JSONObject responseBody = new JSONObject(lastResponse.getBody());
        int spacesToIndentEachLevel = 2;
        System.out.println("Response body:\n" + responseBody.toString(spacesToIndentEachLevel));
        Assert.assertTrue(responseBody.has("id"));
        Assert.assertEquals(responseBody.get("name"), fileName);
    }

    // DeleteFile
    // Given is the same as in GetFileMetadata
    @When("I ask to delete file {string}")
    public void iAskToDeleteFile(String fileName) {
        HttpResponse<String> response = app.deleteFile(fileName);
        Assert.assertNotNull("There are no response!", response);

        int statusCode = response.getStatus();
        System.out.println("Status code: " + statusCode);
        Assert.assertEquals("Bad status code", statusCode, 200);

        JSONObject responseBody = new JSONObject(response.getBody());
        int spacesToIndentEachLevel = 2;
        System.out.println("Response body:\n" + responseBody.toString(spacesToIndentEachLevel));
        Assert.assertTrue(responseBody.getJSONObject("metadata").has("id"));
        Assert.assertEquals(responseBody.getJSONObject("metadata").get("name"), fileName);
    }

    @Then("I see file {string} is successfully deleted")
    public void iSeeFileIsSuccessfullyDeleted(String fileName) {
        Assert.assertFalse("File " + fileName + " was not deleted from Dropbox!", app.isFileUploaded(fileName));
    }
}