package com.coderscampus.SpringSecurityJWTDemo.web.advice;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice(basePackages = { "com.cooksys.groupfinal.controllers" })
@ResponseBody
public class BrewdayControllerAdvice {
}
