package com.example.addressbooksystem.service;

import com.example.addressbooksystem.dto.AddressBookDTO;
import com.example.addressbooksystem.exception.AddressBookException;
import com.example.addressbooksystem.model.AddressBook;
import com.example.addressbooksystem.repository.AddressBookRepo;
import com.example.addressbooksystem.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService implements IAddressBookService{
    @Autowired
    AddressBookRepo repo;
    @Autowired
    TokenUtil tokenUtil;
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
        if(repo.findById(id).isPresent()) {
            addressBook.setName(book.getName());
            addressBook.setPhoneNumber(book.getPhoneNumber());
            addressBook.setEmail(book.getEmail());
            addressBook.setAddress(book.getAddress());
            addressBook.setCity(book.getCity());
            addressBook.setState(book.getState());
            addressBook.setZipCode(book.getZipCode());
            return repo.save(addressBook);
        }else {
            throw new AddressBookException("Sorry! Cannot find user id: "+id);
        }
    }
    @Override
    public List<AddressBook> getAll() {
        List<AddressBook> addressBook=repo.findAll();
        return addressBook;
    }
    @Override
    public Optional<AddressBook> findById(long id) {
        Optional<AddressBook> addressBook=repo.findById(id);
        if(addressBook.isPresent()) {
            return repo.findById(id);
        }else{
        throw new AddressBookException("Sorry! Cannot find user id: "+id);
        }
    }
    @Override
    public void deleteById(long id) {
        Optional<AddressBook> addressBook=repo.findById(id);
        if(addressBook.isPresent()){
            repo.deleteById(id);
        }else {
            throw new AddressBookException("Sorry! Cannot find user id: " + id);
        }
    }
    @Override
    public List<AddressBook> findUserByEmail(String email){
        List<AddressBook> getByEmail=repo.findUserByEmail(email);
        if(getByEmail.isEmpty()){
           throw new ArithmeticException("Sorry! Can not find user email: "+email);
        }else {
            return repo.findUserByEmail(email);
        }
    }
    @Override
    public String uploadByToken(AddressBookDTO addressBookDTO) throws Exception{
        AddressBook addressBook=new AddressBook(addressBookDTO);
        repo.save(addressBook);
        String token=tokenUtil.createToken(addressBook.getId());
        return token;
    }
    @Override
    public List<AddressBook> getByToken(String token){
        long id=tokenUtil.decodeToken(token);
        Optional<AddressBook> isUserPresent=repo.findById(id);
        if(isUserPresent.isPresent()){
            List<AddressBook> addressBookList=repo.findAll();
            return addressBookList;
        }else {
            System.out.println("Exception........!! Token not found!");
            return null;
        }
    }
}
