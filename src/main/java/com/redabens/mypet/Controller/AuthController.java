package com.redabens.mypet.Controller;


import com.redabens.mypet.Dto.UserDto;
import com.redabens.mypet.Response.Response;
import com.redabens.mypet.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {

@Autowired
private AuthService authService;

    @PostMapping("/register")
    public Response auth(@RequestBody UserDto userDto){
        Response response = new Response();
        var infos = authService.register(userDto);
        response.setData(infos);
        response.setMessage("User registered successfully");
        response.setStatus(200);
        return response;
    }

    @PostMapping("/login")
    public Response login(@RequestBody UserDto userDto){
        Response response = new Response();
        var infos = authService.authenticate(userDto);
        response.setData(infos);
        response.setMessage("User logged in successfully");
        response.setStatus(200);
        return response;
    }

}
