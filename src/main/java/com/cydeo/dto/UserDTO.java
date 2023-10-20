package com.cydeo.dto;

import com.cydeo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private boolean enabled;
    private String phone;
    private RoleDTO role;
    private Gender gender;
}
