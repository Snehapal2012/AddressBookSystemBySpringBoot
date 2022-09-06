package com.example.addressbooksystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
public class AddressBookDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-z]{3,}\\s{0,}[A-z]{1}[a-z]{2,}$",message = "Employee name can have First name and Last name and both should start with caps!")
    private String name;
    @Pattern(regexp = "^[1-9]{2}\\s{0,1}[0-9]{10}$",message = "Phone number should have 2 digit country code and 10 digit number!")
    private String phoneNumber;
    private List<String> email;
    @Size(min=3,message = "Address size should be more than 3!")
    private String address;
    @Pattern(regexp = "^[A-Z]{1,}[a-z]{0,}$",message = "City should starts with Caps letter!")
    private String city;
    @NotNull(message = "State can not be null!")
    private String state;
    @NotNull(message = "Zip Code can not be empty!")
    private String zipCode;
}
