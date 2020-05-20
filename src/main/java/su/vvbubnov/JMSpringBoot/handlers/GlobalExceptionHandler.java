package su.vvbubnov.JMSpringBoot.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="SQLException occured")
    @ExceptionHandler(SQLException.class)
    public void handleSQLException(HttpServletRequest request, Exception ex){
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="IOException occured")
    @ExceptionHandler(IOException.class)
    public void handleIOException(){
        //returning 404 error code
    }

    // TODO: 04.05.2020 хуй

}
