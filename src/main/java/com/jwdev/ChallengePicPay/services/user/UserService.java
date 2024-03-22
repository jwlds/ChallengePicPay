package com.jwdev.ChallengePicPay.services.user;

import com.jwdev.ChallengePicPay.domain.user.User;
import com.jwdev.ChallengePicPay.domain.user.UserType;
import com.jwdev.ChallengePicPay.dtos.user.RegisterDTO;
import com.jwdev.ChallengePicPay.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;



    public User registerUser(RegisterDTO data) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data, encryptedPassword);
        return userRepository.insert(newUser);
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getRole() == UserType.RETAILER) throw new Exception("Not authorized for retailer type user");
        if(sender.getBalance().compareTo(amount) < 0) throw new Exception("Not enough balance");
    }


    public User findUserById(String id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
    }


    public boolean isPresentUser(String login) {
        UserDetails user = userRepository.findByEmail(login);
        return user != null;
    }
}
