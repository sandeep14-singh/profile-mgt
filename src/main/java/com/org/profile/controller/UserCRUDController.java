package com.org.profile.controller;

import com.org.profile.dto.UserDto;
import com.org.profile.entity.UserProfile;
import com.org.profile.repo.UserProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@RestController
public class UserCRUDController {

    private UserProfileRepository userProfileRepository;

    public UserCRUDController(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    ResponseEntity<?> save(@RequestBody UserDto userDto) {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(new Random().nextLong(1000000000));
        userProfile.setAge(userDto.getAge());
        userProfile.setEmail(userDto.getEmail());
        userProfile.setName(userDto.getName());
        UserProfile saved = userProfileRepository.save(userProfile);
        return ResponseEntity.status(HttpStatus.OK)
                .body(saved);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    ResponseEntity<?> getUser(Integer id) {
        Optional<UserProfile> userProfile = userProfileRepository.findById((long)id);
        if (userProfile.isPresent()) {
            return ResponseEntity.ok()
                    .body(userProfileRepository.findById((long) id).get());
        } else {
            return ResponseEntity.status((HttpStatus.NOT_FOUND))
                    .body("User does not exist");
        }
    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> update(@RequestBody UserDto userDto, @PathVariable("id") Long id) {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(id);
        userProfile.setAge(userDto.getAge());
        userProfile.setEmail(userDto.getEmail());
        userProfile.setName(userDto.getName());
        UserProfile updated = userProfileRepository.save(userProfile);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updated);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        userProfileRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
