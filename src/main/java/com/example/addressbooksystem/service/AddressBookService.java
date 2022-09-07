package com.example.addressbooksystem.service;

import com.example.addressbooksystem.dto.AddressBookDTO;
import com.example.addressbooksystem.exception.AddressBookException;
import com.example.addressbooksystem.model.AddressBook;
import com.example.addressbooksystem.repository.AddressBookRepo;
import com.example.addressbooksystem.util.EmailSenderService;
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
    @Autowired
    EmailSenderService emailSenderService;
    @Override
    public String greetUser(String name) {
        return "<h1><font color=orange style=bold>Hello "+name+" ! Welcome to Address Book System! </font></h1>";
    }
    @Override
    public AddressBook upload(AddressBookDTO book) {
        AddressBook addressBook=new AddressBook(book);
        repo.save(addressBook);
        emailSenderService.sendEmail(addressBook.getEmail(),"Test Mail", "Hii...."+addressBook.getName()+" your details are added!\n\n Name:  "+addressBook.getName()+"\n Phone number:  "+addressBook.getPhoneNumber()+"\n Email:  "+addressBook.getEmail()+"\n Address:  "+addressBook.getAddress()+"\n City:  "+addressBook.getCity()+"\n State:  "+addressBook.getState()+"\n ZipCode:  "+addressBook.getZipCode());
        return addressBook;
    }
    @Override
    public AddressBook updateById(AddressBookDTO book, long id) {
          AddressBook addressBook=repo.findById(id).get();
        if(repo.findById(id).isPresent()) {
            addressBook.setName(book.getName());
            addressBook.setPhoneNumber(book.getPhoneNumber());
            addressBook.setEmail(book.getEmail());
            addressBook.setAddress(book.getAddress());
            addressBook.setCity(book.getCity());
            addressBook.setState(book.getState());
            addressBook.setZipCode(book.getZipCode());
            emailSenderService.sendEmail(addressBook.getEmail(),"Test Mail","Hii...."+addressBook.getName()+" your details has been edited!\n\n Name:  "+addressBook.getName()+"\n Phone number:  "+addressBook.getPhoneNumber()+"\n  Email:  "+addressBook.getEmail()+"\n Address:  "+addressBook.getAddress()+"\n City:  "+addressBook.getCity()+"\n State:  "+addressBook.getState()+"\n ZipCode:  "+addressBook.getZipCode());
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
            emailSenderService.sendEmail(addressBook.get().getEmail(), "Test Mail","Hii....! Your details has been deleted!");
        }else {
            throw new AddressBookException("Sorry! Cannot find user id: " + id);
        }
    }
    @Override
    public List<AddressBook> findUserByAddress(String address){
        List<AddressBook> getByAddress=repo.findUserByAddress(address);
        if(getByAddress.isEmpty()){
            throw new ArithmeticException("Sorry! Can not find user address: "+address);
        }else {
            return repo.findUserByAddress(address);
        }
    }
    @Override
    public List<AddressBook> findUserByCity(String city){
        List<AddressBook> getByCity=repo.findUserByCity(city);
        if(getByCity.isEmpty()){
           throw new ArithmeticException("Sorry! Can not find user city: "+city);
        }else {
            return repo.findUserByCity(city);
        }
    }
    @Override
    public List<AddressBook> findUserByState(String state){
        List<AddressBook> getByState=repo.findUserByState(state);
        if(getByState.isEmpty()){
            throw new ArithmeticException("Sorry! Can not find user state: "+state);
        }else {
            return repo.findUserByState(state);
        }
    }
    @Override
    public List<AddressBook> findUserByZip(String zip){
        List<AddressBook> getByZip=repo.findUserByZip(zip);
        if(getByZip.isEmpty()){
            throw new ArithmeticException("Sorry! Can not find user zipcode: "+zip);
        }else {
            return repo.findUserByZip(zip);
        }
    }
    @Override
    public String uploadByToken(AddressBookDTO addressBookDTO) throws Exception{
        AddressBook addressBook=new AddressBook(addressBookDTO);
        repo.save(addressBook);
        String token=tokenUtil.createToken(addressBook.getId());
        emailSenderService.sendEmail(addressBook.getEmail(),"Test Mail","Hii...."+addressBook.getName()+" your details are added!\n\n Name:  "+addressBook.getName()+"\n Phone number:  "+addressBook.getPhoneNumber()+"\n Email:  "+addressBook.getEmail()+"\n Address:  "+addressBook.getAddress()+"\n City:  "+addressBook.getCity()+"\n  State:  "+addressBook.getState()+"\n ZipCode:  "+addressBook.getZipCode());
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

    @Override
    public AddressBook findByToken(String token) {
        long id=tokenUtil.decodeToken(token);
        Optional<AddressBook> addressBook=repo.findById(id);
        if(addressBook.isPresent()){
            return addressBook.get();
        }else {
        throw new AddressBookException("Sorry! cannot find the id: "+id);
      }
    }

}
