package task3.dto;

import java.util.Objects;

public class UserTodo {
    private int id;
    private String title;
    private String completed;

    public UserTodo(int id, String title, String completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "UserTodo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed='" + completed + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTodo userTodo = (UserTodo) o;
        return Objects.equals(completed, userTodo.completed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(completed);
    }
}
