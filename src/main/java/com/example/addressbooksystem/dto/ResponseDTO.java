package com.example.addressbooksystem.dto;

import com.example.addressbooksystem.model.AddressBook;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseDTO {
    private String message;
    private Object object;
    public ResponseDTO(String message, AddressBook addressBook) {
        this.message=message;
        this.object=addressBook;
    }

    public ResponseDTO(String message, List<AddressBook> bookList) {
        this.message=message;
        this.object=bookList;
    }

    public ResponseDTO(String message, Optional<AddressBook> response) {
        this.message=message;
        this.object=response;
    }

    public ResponseDTO(String message, String output) {
        this.message=message;
        this.object=output;
    }
}
