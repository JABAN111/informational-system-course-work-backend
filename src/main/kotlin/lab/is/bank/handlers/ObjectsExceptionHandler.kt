package lab.`is`.bank.handlers

import lab.`is`.bank.services.exception.ObjectAlreadyExistException
import lab.`is`.bank.services.exception.ObjectNotExistException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ObjectsExceptionHandler {


    @ExceptionHandler(ObjectNotExistException::class)
    @ResponseStatus
    fun objectNotExistException(e: ObjectNotExistException): String{
        return "Object not exist, details: ${e.message}"
    }

    @ExceptionHandler(ObjectAlreadyExistException::class)
    @ResponseStatus
    fun objectAlreadyExistException(e: ObjectAlreadyExistException): String{
        return "Object already exist, details: ${e.message}"
    }
}