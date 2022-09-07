package com.example.addressbooksystem.repository;

import com.example.addressbooksystem.model.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressBookRepo extends JpaRepository<AddressBook,Long> {
    @Query(value = "select * from address_book_system.address_book,address_book_system.cities where address_book.id=cities.id and city= :city", nativeQuery = true)
    List<AddressBook> findUserByCity(String city);
    @Query(value = "select * from address_book_system.address_book,address_book_system.states where address_book.id=states.id and state= :state", nativeQuery = true)
    List<AddressBook> findUserByState(String state);
    @Query(value = "select * from address_book_system.address_book,address_book_system.addresses where address_book.id=addresses.id and address= :address", nativeQuery = true)
    List<AddressBook> findUserByAddress(String address);
    @Query(value = "select * from address_book_system.address_book,address_book_system.zipCodes where address_book.id=zipCodes.id and zipCode= :zipCode", nativeQuery = true)
    List<AddressBook> findUserByZip(String zip);
}

