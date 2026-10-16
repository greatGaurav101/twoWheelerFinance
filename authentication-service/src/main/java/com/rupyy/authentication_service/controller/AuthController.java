package com.rupyy.authentication_service.controller;

import com.rupyy.authentication_service.dto.APIResponseDTO1;
import com.rupyy.authentication_service.dto.Logindto;
import com.rupyy.authentication_service.dto.SignUpUserDTO;
import com.rupyy.authentication_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;

    private final AuthenticationManager authenticationManager; // constructor based DI
    private AuthController(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/reg")
    public String registerUser(@RequestBody SignUpUserDTO userDTO){
        authService.registerUser(userDTO);
        return "saved";

    }

    @PostMapping("/login")
    public ResponseEntity<APIResponseDTO1<String>> loginCheck(@RequestBody Logindto logindto){

        APIResponseDTO1<String> responsedto = new APIResponseDTO1<>();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(logindto.email(),
                                                        logindto.password());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if(authenticate.isAuthenticated()){
            responsedto.setStatusCode(200);
            responsedto.setMessage("Login Successfully");
            return new ResponseEntity<>(responsedto, HttpStatus.OK);
        }else {
            responsedto.setStatusCode(401);
            responsedto.setMessage("invalid cred");
            return new ResponseEntity<>(responsedto, HttpStatus.FORBIDDEN);
        }

    }
}
