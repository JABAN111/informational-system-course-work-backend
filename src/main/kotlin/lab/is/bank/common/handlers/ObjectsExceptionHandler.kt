package lab.`is`.bank.common.handlers

import lab.`is`.bank.common.exception.ObjectAlreadyExistException
import lab.`is`.bank.common.exception.ObjectNecessaryFieldEmptyException
import lab.`is`.bank.common.exception.ObjectNotExistException
import org.hibernate.ObjectNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ObjectsExceptionHandler {


    @ExceptionHandler(ObjectNotExistException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun objectNotExistException(e: ObjectNotExistException): String{
        return "Object not exist, details: ${e.message}"
    }

    @ExceptionHandler(ObjectAlreadyExistException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun objectAlreadyExistException(e: ObjectAlreadyExistException): String{
        return "Object already exist, details: ${e.message}"
    }

    @ExceptionHandler(ObjectNecessaryFieldEmptyException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun objectNotFoundException(e: ObjectNecessaryFieldEmptyException): String{
        return "Object necessary field empty, details: ${e.message}"
    }
}