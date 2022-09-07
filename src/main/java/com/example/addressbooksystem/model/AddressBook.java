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
    private String phoneNumber;
    @ElementCollection
    @CollectionTable(name = "addresses", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "address")
    private List<String> address;
    @ElementCollection
    @CollectionTable(name = "cities", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "city")
    private List<String> city;
    @ElementCollection
    @CollectionTable(name = "states", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "state")
    private List<String> state;
    @ElementCollection
    @CollectionTable(name = "zipCodes", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "zipCode")
    private List<String> zipCode;
    private String email;

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
