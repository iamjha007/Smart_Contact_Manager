package com.springlearn.SCM.service.impl;

import com.springlearn.SCM.entity.User;
import com.springlearn.SCM.exception.ResourceNotFoundException;
import com.springlearn.SCM.misc.AppConstants;
import com.springlearn.SCM.repository.UserRepository;
import com.springlearn.SCM.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user) {
        //have to generate the user id
        String id= UUID.randomUUID().toString();
        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRolesList(List.of(AppConstants.ROLE_USER));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {

        User user2=userRepository.findById(user.getId()).orElseThrow(()-> new ResourceNotFoundException());


        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setPhone(user.getPhone());
        user2.setAbout(user.getAbout());
        user2.setProfilePic(user.getProfilePic());
        user2.setProvider(user.getProvider());
        user2.setProviderId(user.getProviderId());

        User save = userRepository.save(user2);
        log.info("user updated successfully");
        return Optional.ofNullable(save);

    }

    @Override
    public void deleteUser(String id) {

        User user=userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException());
        userRepository.delete(user);
    }

    @Override
    public boolean isUserExist(String id) {

        User user = userRepository.findById(id).orElse(null);
        return user!=null;
    }

    @Override
    public boolean isUserExistByEmail(String email) {

        User user = userRepository.findByEmail(email).orElse(null);
        return user!=null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
