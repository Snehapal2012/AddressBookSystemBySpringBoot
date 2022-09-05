package com.example.addressbooksystem.model;

import com.example.addressbooksystem.dto.AddressBookDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "address_book")
@Data
@NoArgsConstructor
public class AddressBook {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    private Long phoneNumber;
    private String address;
    private String city;
    private String state;
    private Long zipCode;
    @ElementCollection
    @CollectionTable(name = "user_email", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "email")
    private List<String> email;

    public AddressBook(AddressBookDTO book) {
        this.name=book.getName();
        this.phoneNumber=book.getPhoneNumber();
        this.email=book.getEmail();
        this.address=book.getAddress();
        this.city=book.getCity();
        this.state=book.getState();
        this.zipCode=book.getZipCode();
    }
}
