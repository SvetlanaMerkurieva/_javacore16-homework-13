package task2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import task2.dto.UserPost;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserPostComments {
    public static void main(String[] args) throws IOException {
        Scanner scannerUserId = new Scanner(System.in);
        System.out.println("Введіть id користувача пости якого бажаєте отримати:");
        int userId = scannerUserId.nextInt();
        String urlPosts = "https://jsonplaceholder.typicode.com/users/" + userId + "/posts";
        scannerUserId.close();
        String responsePosts = Jsoup.connect(urlPosts).ignoreContentType(true).get().body().text();
        Type typeToken = TypeToken.getParameterized(List.class, UserPost.class).getType();
        List<UserPost> userPosts = new Gson().fromJson(responsePosts, typeToken);
        List<Integer> idList = new ArrayList<Integer>();
        for (UserPost userPost: userPosts) {
            idList.add(userPost.getId());
        }
        int maxId;
        maxId = idList.stream().max((a, b) -> a - b).get();
        String urlComments = "https://jsonplaceholder.typicode.com/posts/" + maxId + "/comments";
        String responseComments = Jsoup.connect(urlComments).ignoreContentType(true).get().body().text();
        String fileName = "src/main/java/task2/user-" + userId + "-post-" + maxId + "-comments.json";
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(responseComments);
            writer.flush();
        } catch (IOException e) {
            System.out.println((e.getMessage()));
        }
        System.out.println(("В папці task2 створено файл user-" + +userId + "-post-" + maxId + "-comments.json"));
    }
}
