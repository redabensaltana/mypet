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

    public Map register(UserDto userDto){
        User user = new User();
        Map map = new HashMap();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setNumberPhone(userDto.getNumberPhone());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User u = userRepo.save(user);
        map.put("token",jwtUtils.generateToken(userDetailsService.loadUserByUsername(user.getEmail())));
        map.put("userId",u.getId());
        return map;
    }

    public Map authenticate(UserDto userDto){
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       userDto.getEmail(),
                       userDto.getPassword()
               )
       );
       User user = userRepo.findUserByEmail(userDto.getEmail());
       Map map = new HashMap();
       map.put("token",jwtUtils.generateToken(userDetailsService.loadUserByUsername(user.getEmail())));
       map.put("userId",user.getId());
        return map;
    }
}
