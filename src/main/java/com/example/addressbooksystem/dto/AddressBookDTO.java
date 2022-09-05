package com.example.addressbooksystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
public class AddressBookDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-z]{3,}\\s{0,}[A-z]{1}[a-z]{2,}$",message = "Invalid Employee name!")
    private String name;
    @NotNull(message = "Phone number can not be null!")
    private Long phoneNumber;
    private List<String> email;
    @NotNull(message = "Address can not null!")
    private String address;
    @NotEmpty(message = "City can not be empty!")
    private String city;
    @NotNull(message = "State can not be null!")
    private String state;
    @NotNull(message = "Zip Code can not be empty!")
    private Long zipCode;
}
