package task1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Jsoup;

import task1.dto.User;
import task1.dto.UserAddress;
import task1.dto.UserAddressGeo;
import task1.dto.UserCompany;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class UsersTests {
    public static void main(String[] args) throws IOException {
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
        System.out.println("Кількість користувачів: " + users.size());
        users.add(newUser);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonUser = gson.toJson(newUser);
        System.out.println("Новий створенний користувач: " + jsonUser);
        System.out.println("Кількість користувачів після створення нового користувача: " + users.size());

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

    }


}
