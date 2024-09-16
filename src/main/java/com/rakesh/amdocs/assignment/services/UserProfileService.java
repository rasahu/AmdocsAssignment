package com.rakesh.amdocs.assignment.services;

import com.rakesh.amdocs.assignment.dto.UserProfileDTO;
import com.rakesh.amdocs.assignment.exception.UserNotFoundException;
import com.rakesh.amdocs.assignment.repository.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@repo
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public void uploadIdPicture(Long userId, MultipartFile file) throws IOException {
        Optional<UserProfileDTO> userProfileOpt = userProfileRepository.findById(userId);

        if (userProfileOpt.isEmpty()) {
            throw new UserNotFoundException("User with id " + userId + " not found.");
        }

        UserProfileDTO userProfile = userProfileOpt.get();

        // Save file to file system
        String fileName = userId + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Update user profile with file name
        userProfile.setIdPictureFileName(fileName);
        userProfileRepository.save(userProfile);
    }
}
