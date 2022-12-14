package com.example.addressbooksystem.service;

import com.example.addressbooksystem.dto.AddressBookDTO;
import com.example.addressbooksystem.model.AddressBook;

import java.util.List;
import java.util.Optional;

public interface IAddressBookService {
    String greetUser(String name);
    AddressBook upload(AddressBookDTO book);
    AddressBook updateById(AddressBookDTO book, long id);
    List<AddressBook> getAll();
    Optional<AddressBook> findById(long id);
    void deleteById(long id);

    List<AddressBook> findUserByCity(String city);

    String uploadByToken(AddressBookDTO addressBookDTO) throws Exception;

    List<AddressBook> getByToken(String token);

    AddressBook findByToken(String token);

    List<AddressBook> findUserByState(String state);

    List<AddressBook> findUserByAddress(String address);

    List<AddressBook> findUserByZip(String zip);
}
