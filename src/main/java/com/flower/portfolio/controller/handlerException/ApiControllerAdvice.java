package com.flower.portfolio.controller.handlerException;

import com.flower.portfolio.dto.response.ExceptionDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO exceptionHandler(MethodArgumentNotValidException ex){
        List<FieldError> errorLIst= ex.getBindingResult().getFieldErrors();
        Map<String,String> detalle= new HashMap<>();
        errorLIst.forEach(e->detalle.put(e.getField(),e.getDefaultMessage()));
        return new ExceptionDTO(HttpStatus.BAD_REQUEST.value(),"Invalid Request",detalle);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO exceptionHandlerRequestParam(ConstraintViolationException ex){
        Set<ConstraintViolation<?>> constraintViolations=((ConstraintViolationException) ex).getConstraintViolations();
        Map<String,String> detalle= new HashMap<>();
        constraintViolations.forEach(c->detalle.put(c.getPropertyPath().toString(),c.getMessage()));
        return new ExceptionDTO(HttpStatus.BAD_REQUEST.value(),"Invalid Request",detalle);
    }
}
