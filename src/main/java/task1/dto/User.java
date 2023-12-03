package task1;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private String username;
    private String email;
    UserAddress address;
    private String phone;
    private String website;
    UserCompany company;
}
