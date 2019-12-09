package com.ehi.graphql.service;

import com.ehi.graphql.dao.entity.Header;
import com.ehi.graphql.dao.entity.Location;
import com.ehi.graphql.dao.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ehi.graphql.dao.entity.*;
import com.ehi.graphql.dao.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;
import java.net.URL;

@Service
public class EhiGraphQLService {

    private final UserRepository userRepository;

    public EhiGraphQLService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User addUser(UserInput userInput) {
        final User user = new User();
        user.setFname(userInput.fname);
        user.setEmail(userInput.email);
        user.setLname(userInput.lname);
        user.setDob(userInput.dob);
        return this.userRepository.save(user);
    }

    @Transactional
    public Optional<User> updateUser(UserInput userInput, int id) {
        Optional<User> optional = this.userRepository.findById(id);
        optional.ifPresent(user -> {
            user.setFname(userInput.fname);
            user.setEmail(userInput.email);
            user.setLname(userInput.lname);
            user.setDob(userInput.dob);
            this.userRepository.save(user);
        });
        return optional;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUser(final int count) {
        return this.userRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<User> getUser(final int id) {
        return this.userRepository.findById(id);
    }

    private String invokeGETAPI(String urlStr) {
        String resp = "";
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            StringBuffer response = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                response.append(output);
                System.out.println(output);
            }
            conn.disconnect();
            resp = response.toString();
            System.out.println("JSON String Result " + response.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    private <T> List<T> asList(String json, String path, Class<T> clazz) {
        Gson gson = new Gson();
        String[] paths = path.split("\\.");
        JsonObject o = gson.fromJson(json, JsonObject.class);
        for (int i = 0; i < paths.length - 1; i++) {
            o = o.getAsJsonObject(paths[i]);
        }
        JsonArray jsonArray = o.getAsJsonArray(paths[paths.length - 1]);
        Class<T[]> clazzArray = (Class<T[]>) ((T[]) Array.newInstance(clazz, 0)).getClass();
        T[] objectArray = gson.fromJson(jsonArray, clazzArray);
        return Arrays.asList(objectArray);
    }

    public AllianceInfo getAllianceInfo(String allianceId) {
        AllianceInfo mapData = null;
        String url = "https://www.qa.ehealthmedicareplans.com/mcws/rs/alliance/call-service/v2/" + allianceId;
        String resp = invokeGETAPI(url);
        mapData = new Gson().fromJson(resp, AllianceInfo.class);
        return mapData;
    }

    public List<Location> getLocation(String zip) {
        List<Location> mapData = null;
        String url = "https://www.qa.ehealthmedicareplans.com/mcws/rs/locations/v2?zip=" + zip;
        String resp = invokeGETAPI(url);
        mapData = asList(resp, "locationList", Location.class);
        return mapData;
    }

    public Header getHeader() {
        Header mapData = null;
        String url = "https://www.qa.ehealthmedicare.com/wp-json/ehm/v1/menu/header/";
        String resp = invokeGETAPI(url);
        mapData = new Gson().fromJson(resp, Header.class);
        return mapData;
    }
}
