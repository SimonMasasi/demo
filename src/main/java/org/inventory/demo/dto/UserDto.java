package org.inventory.demo.dto;

import lombok.*;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String uuid;
    private String firstName;
    private  String middleName;
    private  String lastName;
    private  String phoneNumber;
    private  String userName;
    private  String email;

}
