package com.example.addressbooksystem.service;

import com.example.addressbooksystem.dto.AddressBookDTO;
import com.example.addressbooksystem.model.AddressBook;
import com.example.addressbooksystem.repository.AddressBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService implements IAddressBookService{
    @Autowired
    AddressBookRepo repo;
    @Override
    public String greetUser(String name) {
        return "<h1><font color=orange style=bold>Hello "+name+" ! Welcome to Address Book System! </font></h1>";
    }
    @Override
    public AddressBook upload(AddressBookDTO book) {
        AddressBook addressBook=new AddressBook(book);
        repo.save(addressBook);
        return addressBook;
    }
    @Override
    public AddressBook updateById(AddressBookDTO book,long id) {
        AddressBook addressBook=repo.findById(id).get();
        addressBook.setName(book.getName());
        addressBook.setPhoneNumber(book.getPhoneNumber());
        addressBook.setEmail(book.getEmail());
        addressBook.setAddress(book.getAddress());
        addressBook.setCity(book.getCity());
        addressBook.setState(book.getState());
        addressBook.setZipCode(book.getZipCode());
        return repo.save(addressBook);
    }
    @Override
    public List<AddressBook> getAll() {
        List<AddressBook> addressBook=repo.findAll();
        return addressBook;
    }
    @Override
    public Optional<AddressBook> findById(long id) {
        return repo.findById(id);
    }
    @Override
    public void deleteById(long id) {
        repo.deleteById(id);
    }
    @Override
    public List<AddressBook> findUserByEmail(String email){
        return repo.findUserByEmail(email);
    }
}
