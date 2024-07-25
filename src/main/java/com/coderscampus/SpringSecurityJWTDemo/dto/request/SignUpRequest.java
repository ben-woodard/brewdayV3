package com.coderscampus.SpringSecurityJWTDemo.dto.request;

import java.util.Optional;

public record SignUpRequest(String email,
                            String password,
                            String firstName,
                            String lastName,
                            String companyName,
                            Optional<String> authorityOpt) {

}
