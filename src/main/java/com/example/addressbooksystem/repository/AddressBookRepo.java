package com.example.addressbooksystem.repository;

import com.example.addressbooksystem.model.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressBookRepo extends JpaRepository<AddressBook,Long> {
    @Query(value = "select * from address_book_system.address_book,address_book_system.user_email where address_book.id=user_email.id and email= :email", nativeQuery = true)
    List<AddressBook> findUserByEmail(String email);
}

