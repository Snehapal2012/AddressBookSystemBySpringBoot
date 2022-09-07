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
        ResponseDTO responseDTO=new ResponseDTO("All User details found!",bookList);
        return new ResponseEntity(responseDTO,HttpStatus.OK);
    }
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable long id){
        Optional<AddressBook> response=service.findById(id);
        ResponseDTO responseDTO=new ResponseDTO("User details is found!",response);
        return new ResponseEntity(responseDTO,HttpStatus.FOUND);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteUserById(@PathVariable long id){
        service.deleteById(id);
        ResponseDTO responseDTO=new ResponseDTO("User details is deleted!","Deleted user id: "+id);
        return new ResponseEntity<>(responseDTO,HttpStatus.GONE);
    }
    @GetMapping("/findUserByAddress/{address}")
    public ResponseEntity<ResponseDTO> findUserByAddress(@PathVariable String address){
        List<AddressBook> addressBookList=service.findUserByAddress(address);
        ResponseDTO responseDTO=new ResponseDTO("User details is found by address!",addressBookList);
        return new ResponseEntity<>(responseDTO,HttpStatus.FOUND);
    }
    @GetMapping("/findUserByCity/{city}")
    public ResponseEntity<ResponseDTO> findUserByCity(@PathVariable String city){
        List<AddressBook> addressBookList=service.findUserByCity(city);
        ResponseDTO responseDTO=new ResponseDTO("User details is found by city!",addressBookList);
        return new ResponseEntity<>(responseDTO,HttpStatus.FOUND);
    }
    @GetMapping("/findUserByState/{state}")
    public ResponseEntity<ResponseDTO> findUserByState(@PathVariable String state){
        List<AddressBook> addressBookList=service.findUserByState(state);
        ResponseDTO responseDTO=new ResponseDTO("User details is found by state!",addressBookList);
        return new ResponseEntity<>(responseDTO,HttpStatus.FOUND);
    }
    @GetMapping("/findUserByZip/{zip}")
    public ResponseEntity<ResponseDTO> findUserByZip(@PathVariable String zip){
        List<AddressBook> addressBookList=service.findUserByZip(zip);
        ResponseDTO responseDTO=new ResponseDTO("User details is found by zipCode!",addressBookList);
        return new ResponseEntity<>(responseDTO,HttpStatus.FOUND);
    }
    @PostMapping ("/upload")
    public ResponseEntity<ResponseDTO> uploadByToken(@Valid @RequestBody AddressBookDTO addressBookDTO) throws Exception{
        String token=service.uploadByToken(addressBookDTO);
        ResponseDTO responseDTO=new ResponseDTO("Details uploaded!",token);
        return new ResponseEntity<>(responseDTO,HttpStatus.CREATED);
    }
    @CrossOrigin
    @GetMapping("/getAllByToken/{token}")
    public ResponseEntity<ResponseDTO> getByToken(@PathVariable String token){
        List<AddressBook> addressBookList=service.getByToken(token);
        ResponseDTO responseDTO=new ResponseDTO("Details found!",addressBookList);
        return new ResponseEntity<>(responseDTO,HttpStatus.FOUND);
    }
    @CrossOrigin
    @GetMapping("/findByToken/{token}")
    public ResponseEntity<ResponseDTO> findByToken(@PathVariable String token){
        AddressBook addressBook=service.findByToken(token);
        ResponseDTO responseDTO=new ResponseDTO("Details found with token!",addressBook);
        return new ResponseEntity<>(responseDTO,HttpStatus.FOUND);
    }
}

