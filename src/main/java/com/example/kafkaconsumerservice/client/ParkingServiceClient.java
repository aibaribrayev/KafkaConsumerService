package com.example.kafkaconsumerservice.client;

import com.example.kafkaconsumerservice.model.ParkingSpot;
import com.example.kafkaconsumerservice.model.User;
import okhttp3.*;

import java.io.IOException;
import java.time.LocalDateTime;

public class ParkingServiceClient {
    private static final String BASE_URL = "http://localhost:8080";

    private final OkHttpClient httpClient;

    public ParkingServiceClient() {
        this.httpClient = new OkHttpClient();
    }

    public void getUser(String userId) {
        String url = BASE_URL + "/users/" + userId;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                String responseBody = response.body().string();
                // здесь парсим responseBody в объект User
                //User user = new Gson().fromJson(responseBody, User.class);
                System.out.println(responseBody);
            }
        });
    }

    public void getAllUser(String userId) {
        String url = BASE_URL + "/users/" + userId;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                String responseBody = response.body().string();
                // здесь парсим responseBody в объект User
                //User user = new Gson().fromJson(responseBody, User.class);
                System.out.println(responseBody);
            }
        });
    }

    public void addParkingHistory(ParkingSpot parkingSpot) {
        String url = BASE_URL + "/users/history/" + parkingSpot.getCurrentUserId();
        double price = (parkingSpot.getEndTime().getMinute() - parkingSpot.getStartTime().getMinute())*100/60;

        if(parkingSpot.getCurrentUserId() == null){
            System.out.println("Couldn't fix user");
            return;
        }

        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("parkingSpotNumber", parkingSpot.getSpotNumber())
                .addFormDataPart("carNumber", parkingSpot.getCurrentCarNumber())
                .addFormDataPart("startTime", String.valueOf(parkingSpot.getStartTime()))
                .addFormDataPart("endTime", String.valueOf(parkingSpot.getEndTime()))
                .addFormDataPart("price", String.valueOf(price))
                .addFormDataPart("isPaid", "false")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                String responseBody = response.body().string();
                System.out.println(responseBody);
            }
        });
    }
    public void getAllUserHistoryForLast24Hours() {
        String url = BASE_URL + "/users/history/all/";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                String responseBody = response.body().string();
                // здесь парсим responseBody в объект User
                //User user = new Gson().fromJson(responseBody, User.class);
                System.out.println(responseBody);
            }
        });
    }
}
