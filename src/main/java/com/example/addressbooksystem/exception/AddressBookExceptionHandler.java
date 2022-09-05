package com.example.addressbooksystem.exception;

import com.example.addressbooksystem.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AddressBookExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ObjectError> errorList=exception.getBindingResult().getAllErrors();
        List<String> errMsg=errorList.stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        ResponseDTO responseDTO=new ResponseDTO("Exception while processing REST request", errMsg.toString());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AddressBookException.class)
    public ResponseEntity<ResponseDTO> handlePayrollException(AddressBookException exception){
        ResponseDTO responseDTO=new ResponseDTO("Exception while processing REST request",exception.getMessage());
        return new ResponseEntity<>(responseDTO,HttpStatus.BAD_GATEWAY);
    }

}
