package task1;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAddress {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    UserAddressGeo geo;
}
