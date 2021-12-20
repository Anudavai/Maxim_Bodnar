package com.webapi.app;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.RawBody;
import com.mashape.unirest.request.body.RequestBodyEntity;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DropboxApp {
    private String accessToken;
    private static final String LOCAL_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\files\\";
    private static final String UPLOAD_URL = "https://content.dropboxapi.com/2/files/upload";
    private static final String GET_METADATA_URL = "https://api.dropboxapi.com/2/files/get_metadata";
    private static final String DELETE_FILE_URL = "https://api.dropboxapi.com/2/files/delete_v2";

    public DropboxApp(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isFileExists(String fileName) {
        try {
            File tempFile = new File(LOCAL_PATH + fileName);
            return tempFile.exists();
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e);
            return false;
        }
    }

    public HttpResponse<String> uploadFile(String fileName) throws IOException {
        try {
            byte[] data = Files.readAllBytes(Paths.get(LOCAL_PATH + fileName));

            Unirest.setTimeouts(0, 0);
            RawBody uploadFileBody = Unirest.post(UPLOAD_URL)
                    .header("Dropbox-API-Arg", "{\"path\": \"/" + fileName + "\",\"mode\": \"add\",\"autorename\": true,\"mute\": false,\"strict_conflict\": false}")
                    .header("Content-Type", "application/octet-stream")
                    .header("Authorization", "Bearer " + accessToken)
                    .body(data);

            HttpResponse<String> response = uploadFileBody.asString();
            return response;
        }
        catch (UnirestException e) {
            System.out.println("ERROR: " + e);
            e.printStackTrace();
            return null;
        }
    }

    public boolean isFileUploaded(String fileName) {
        try {
            Unirest.setTimeouts(0, 0);
            RequestBodyEntity getMetadataBody = Unirest.post("https://api.dropboxapi.com/2/files/list_folder")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + accessToken)
                    .body("""
                            {\r
                                "path": "",\r
                                "include_media_info": false,\r
                                "recursive": true,\r
                                "include_deleted": false,\r
                                "include_has_explicit_shared_members": false,\r
                                "include_mounted_folders": true,\r
                                "include_non_downloadable_files": true\r
                            }""");

            HttpResponse<String> response = getMetadataBody.asString();

            JSONObject responseBody = new JSONObject(response.getBody());
            int length = responseBody.getJSONArray("entries").length();
            for (int i = 0; i < length; i++) {
                if (responseBody.getJSONArray("entries").getJSONObject(i).get("name").equals(fileName))
                    return true;
            }
            return false;
        }
        catch (UnirestException e) {
            System.out.println("ERROR: " + e);
            e.printStackTrace();
            return false;
        }
    }

    public HttpResponse<String> getFileMetadata(String fileName) {
        try {
            Unirest.setTimeouts(0, 0);
            RequestBodyEntity getMetadataBody = Unirest.post(GET_METADATA_URL)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + accessToken)
                    .body("{\r\n    \"path\": \"/" + fileName + "\",\r\n    " +
                                   "\"include_media_info\": false,\r\n    " +
                                   "\"include_deleted\": false,\r\n    " +
                                   "\"include_has_explicit_shared_members\": false\r\n" +
                            "}");

            HttpResponse<String> response = getMetadataBody.asString();
            return response;
        }
        catch (UnirestException e) {
            System.out.println("ERROR: " + e);
            e.printStackTrace();
            return null;
        }
    }

    public HttpResponse<String> deleteFile(String fileName) {
        try {
            Unirest.setTimeouts(0, 0);
            RequestBodyEntity deleteFileBody = Unirest.post(DELETE_FILE_URL)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + accessToken)
                    .body("{\r\n    \"path\": \"/" + fileName + "\"\n}");

            HttpResponse<String> response = deleteFileBody.asString();
            return response;
        }
        catch (UnirestException e) {
            System.out.println("ERROR: " + e);
            e.printStackTrace();
            return null;
        }
    }

}
