package task3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import task3.dto.UserTodo;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;



public class UserOpenTasks {
    public static void main(String[] args) throws IOException {
        Scanner scannerUserId = new Scanner(System.in);
        System.out.println("Введіть id користувача незавершені задачі якого бажаєте отримати:");
        int userId = scannerUserId.nextInt();
        String urlTodos = "https://jsonplaceholder.typicode.com/users/" + userId + "/todos";
        scannerUserId.close();
        String responseTodos = Jsoup.connect(urlTodos).ignoreContentType(true).get().body().text();
        Type typeToken = TypeToken.getParameterized(List.class, UserTodo.class).getType();
        List<UserTodo> userPosts = new Gson().fromJson(responseTodos, typeToken);
        List<UserTodo> userOpenTodos = userPosts.stream().filter(todo -> todo.getCompleted().equals("false")).toList();
        for (UserTodo userTodo: userOpenTodos) {
            System.out.println("Задача № " + userTodo.getId() + " : " + userTodo.getTitle());
        }
    }


}
