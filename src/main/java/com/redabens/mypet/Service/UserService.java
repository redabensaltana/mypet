package com.redabens.mypet.Service;

import com.redabens.mypet.Dto.UserDto;
import com.redabens.mypet.Entity.User;
import com.redabens.mypet.Repository.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> getAll() {
        return userRepo.findAll();
    }
    public User getUserById(int id) {
        return userRepo.findById(id).get();
    }
    public User getUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }
    public User saveUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return userRepo.save(user);
    }

    public User updateUser(UserDto userDto, int id) {
        User user = userRepo.findById(id).get();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setNumberPhone(userDto.getNumberPhone());
        user.setPassword(userDto.getPassword());

        return userRepo.save(user);
    }
    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }


}
