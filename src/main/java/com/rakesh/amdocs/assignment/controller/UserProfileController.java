package com.rakesh.amdocs.assignment.controller;

import com.rakesh.amdocs.assignment.exception.UserNotFoundException;
import com.rakesh.amdocs.assignment.services.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/user/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;
//Solution start for problem 4
    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PutMapping("/{userId}/uploadIdPicture")
    public ResponseEntity<String> uploadIdPicture(@PathVariable Long userId,
                                                  @RequestParam("file") MultipartFile file) {
        try {
            userProfileService.uploadIdPicture(userId, file);
            return ResponseEntity.ok("ID picture uploaded successfully.");
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed.");
        }
    }
}

