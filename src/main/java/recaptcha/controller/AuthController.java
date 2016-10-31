package recaptcha.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recaptcha.dto.ReCaptchaResponseDto;
import recaptcha.dto.RegisterDto;
import recaptcha.service.ReCaptchaClient;

@Slf4j
@RestController
public class AuthController {

    @Autowired
    ReCaptchaClient reCaptchaClient;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(RegisterDto registerDto){

        log.info(registerDto.toString());

        ReCaptchaResponseDto reCaptchaResponse = reCaptchaClient.verify(registerDto.getRecaptchaResponse());

        if(!reCaptchaResponse.isSuccess()){
            throw new RuntimeException("");
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException e){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
