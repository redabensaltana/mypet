package com.redabens.mypet.Service;

import com.redabens.mypet.Dto.UserDto;
import com.redabens.mypet.Entity.User;
import com.redabens.mypet.Repository.UserRepo;
import com.redabens.mypet.Response.Response;
import com.redabens.mypet.Security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public String register(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setNumberPhone(userDto.getNumberPhone());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepo.save(user);
        return jwtUtils.generateToken(userDetailsService.loadUserByUsername(user.getEmail()));
    }

    public String authenticate(UserDto userDto){
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       userDto.getEmail(),
                       userDto.getPassword()
               )
       );
       User user = userRepo.findUserByEmail(userDto.getEmail());
        return jwtUtils.generateToken(userDetailsService.loadUserByUsername(user.getEmail()));
    }
}
