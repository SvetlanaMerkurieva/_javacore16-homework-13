package task1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import task1.dto.User;
import task1.dto.UserAddress;
import task1.dto.UserAddressGeo;
import task1.dto.UserCompany;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class UsersTests {
    public static void main(String[] args) throws IOException, InterruptedException {
        String urlAllUsers = "https://jsonplaceholder.typicode.com/users";
        String allUsers = Jsoup.connect(urlAllUsers).ignoreContentType(true).get().body().text();
        Type typeToken = TypeToken.getParameterized(List.class, User.class).getType();

        List<User> users = new Gson().fromJson(allUsers, typeToken);

        System.out.println("Отримання інформації про всіх користувачів:");
        for (User user: users) {
            System.out.println(user);
        }

        System.out.println("------------------------------------------------------------------");

        System.out.println("Створення нового користувача:");
        UserCompany newUserCompany = new UserCompany("Південь", "Знаходимо в житті лише те, що самі вкладаємо в нього", "розробка актуальних застосунків");
        UserAddressGeo newUserAddressGeo = new UserAddressGeo("-38.2101", "44.4615");
        UserAddress newUserAddress = new UserAddress("О. Поля", "дім 44", "Дніпро", "49000", newUserAddressGeo);
        int newId = users.size()+1;
        User newUser = new User(newId, "Lana Merkury", "Merlana", "merlana@gmail.com" , newUserAddress, "093 193 44 85", "south.com.ua", newUserCompany);
        users.add(newUser);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonUsers = gson.toJson(users);

        URL url = new URL("https://jsonplaceholder.typicode.com/users/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        os.write(jsonUsers.getBytes());
        os.flush();
        os.close();

        int responseCode = connection.getResponseCode();
        System.out.println("POST responseCode: " + responseCode);
        StringBuffer response = new StringBuffer();

        if (responseCode == HttpURLConnection.HTTP_CREATED) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
             while ((inputLine = in.readLine()) != null) {
                 response.append(inputLine);
             }
             in.close();
        } else {
            System.out.println("POST request not worked");
        }
        System.out.println(response);

        System.out.println("------------------------------------------------------------------");

        System.out.println("Зміна даних користувача з id 5:");
        User userId5 = users.get(4);

        userId5.setUsername("Trino");
        userId5.setPhone("***-***-**-**");

        String jsonUserId5 = gson.toJson(userId5);

        URL urlPut = new URL("https://jsonplaceholder.typicode.com/users/5");
        HttpURLConnection connectionPut = (HttpURLConnection) urlPut.openConnection();
        connectionPut.setDoOutput(true);
        connectionPut.setRequestMethod("PUT");

        OutputStream osP = connectionPut.getOutputStream();
        osP.write(jsonUserId5.getBytes());
        osP.flush();
        osP.close();

        int responseCodePut = connectionPut.getResponseCode();
        System.out.println("PUT responseCode: " + responseCodePut);

        StringBuffer responsePut = new StringBuffer();

        if (responseCodePut== HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connectionPut.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                responsePut.append(inputLine);
            }
            in.close();
        } else {
            System.out.println("PUT request not worked");
        }
        System.out.println(responsePut);

        System.out.println("------------------------------------------------------------------");

        System.out.println("Видалення користувача з id 4:");
        URL urlUserDel = new URL("https://jsonplaceholder.typicode.com/users/4");

        HttpURLConnection connectionDel = (HttpURLConnection) urlUserDel.openConnection();
        connectionDel.setRequestMethod("DELETE");
        connectionDel.setDoOutput(true);

        int responseCodeDel = connectionDel.getResponseCode();
        System.out.println("DELETE responseCode: " + responseCodeDel);

        System.out.println("------------------------------------------------------------------");

        System.out.println("Отримання інформації про користувача за id:");
        Scanner scannerId = new Scanner(System.in);
        System.out.println("Введіть id користувача про якого бажаєте отримати інформацію:");
        int inputId = scannerId.nextInt();
        String urlUserById = "https://jsonplaceholder.typicode.com/users/" + inputId;
        String userById = Jsoup.connect(urlUserById).ignoreContentType(true).get().body().text();
        System.out.println(userById);
        scannerId.close();

        System.out.println("------------------------------------------------------------------");

        String userName = "Antonette";
        System.out.println("Oтримання інформації про користувача за username - Antonette: ");
        String urlUserByUsername = "https://jsonplaceholder.typicode.com/users?username=" + userName;
        String userByUsername = Jsoup.connect(urlUserByUsername).ignoreContentType(true).get().body().text();
        System.out.println(userByUsername);

        System.out.println("------------------------------------------------------------------");
        Jsoup.connect(urlAllUsers).method(Connection.Method.PUT);

    }


}
