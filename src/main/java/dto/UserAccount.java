package dto;

import lombok.Data;

@Data
public class UserAccount {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String addressLine1;
    private String city;
    private String state;
    private String zipCode;
    private String mobilePhone;
    private String alias;
}
