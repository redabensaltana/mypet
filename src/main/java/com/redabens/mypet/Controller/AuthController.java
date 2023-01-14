package com.redabens.mypet.Controller;


import com.redabens.mypet.Dto.UserDto;
import com.redabens.mypet.Response.Response;
import com.redabens.mypet.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

@Autowired
private AuthService authService;

    @PostMapping("/register")
    public Response auth(@RequestBody UserDto userDto){
        Response response = new Response();
        Map map = new HashMap();
        var jwt = authService.register(userDto);
        map.put("token",jwt);
        response.setData(map);
        response.setMessage("User registered successfully");
        response.setStatus(200);
        return response;
    }

    @PostMapping("/login")
    public Response login(@RequestBody UserDto userDto){
        Response response = new Response();
        Map map = new HashMap();
        var jwt = authService.authenticate(userDto);
        map.put("token",jwt);
        response.setData(map);
        response.setMessage("User logged in successfully");
        response.setStatus(200);
        return response;
    }

}
