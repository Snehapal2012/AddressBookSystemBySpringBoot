package com.example.addressbooksystem.controller;

import com.example.addressbooksystem.dto.AddressBookDTO;
import com.example.addressbooksystem.dto.ResponseDTO;
import com.example.addressbooksystem.model.AddressBook;
import com.example.addressbooksystem.service.IAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AddressBookController {
    @Autowired
    IAddressBookService service;

    @GetMapping(value = {"/","","/home"})
    public String welcomeMessage(){
        return "<h1><font color=blue style=bold>Welcome to Address Book System !!</font></h1>";
    }
    @GetMapping("/greetUser/{name}")
    public String greetUser(@PathVariable String name){
        String result=service.greetUser(name);
        return result;
    }
    @PostMapping("/insertUser")
    public ResponseEntity<ResponseDTO> upload(@Valid  @RequestBody AddressBookDTO book){
        AddressBook addressBook =service.upload(book);
        ResponseDTO responseDTO=new ResponseDTO("User details uploaded successfully!",addressBook);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseDTO> updateById(@Valid @RequestBody AddressBookDTO book,@PathVariable long id){
        AddressBook addressBook=service.updateById(book,id);
        ResponseDTO responseDTO=new ResponseDTO("Address book details are edited!",addressBook);
        return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
    }
    @GetMapping ("/allDetails")
    public ResponseEntity<ResponseDTO> getAll(){
        List<AddressBook> bookList=service.getAll();
        ResponseDTO responseDTO=new ResponseDTO("All Employee details!",bookList);
        return new ResponseEntity(responseDTO,HttpStatus.OK);
    }
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable long id){
        Optional<AddressBook> response=service.findById(id);
        ResponseDTO responseDTO=new ResponseDTO("Employee details is found!",response);
        return new ResponseEntity(responseDTO,HttpStatus.FOUND);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteUserById(@PathVariable long id){
        service.deleteById(id);
        ResponseDTO responseDTO=new ResponseDTO("Employee details is deleted!","Deleted employee id: "+id);
        return new ResponseEntity<>(responseDTO,HttpStatus.GONE);
    }
    @GetMapping("/findUserByEmail/{email}")
    public ResponseEntity<ResponseDTO> findUserByEmail(@PathVariable String email){
        List<AddressBook> addressBookList=service.findUserByEmail(email);
        ResponseDTO responseDTO=new ResponseDTO("Employee department is found!",addressBookList);
        return new ResponseEntity<>(responseDTO,HttpStatus.FOUND);
    }
}

