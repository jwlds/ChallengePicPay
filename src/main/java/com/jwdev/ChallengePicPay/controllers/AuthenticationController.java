package com.jwdev.ChallengePicPay.controllers;


import com.jwdev.ChallengePicPay.domain.user.User;
import com.jwdev.ChallengePicPay.dtos.user.AuthenticationDTO;
import com.jwdev.ChallengePicPay.dtos.user.LoginResponse;
import com.jwdev.ChallengePicPay.dtos.user.RegisterDTO;
import com.jwdev.ChallengePicPay.services.auth.TokenService;
import com.jwdev.ChallengePicPay.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var tokenJWT = tokenService.generateToken( (User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponse(tokenJWT));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if(userService.isPresentUser(data.email())) return ResponseEntity.badRequest().build();
        this.userService.registerUser(data);
        return ResponseEntity.ok().build();
    }
}
