package com.rakesh.amdocs.assignment.services;

import com.rakesh.amdocs.assignment.dto.UserProfileDTO;
import com.rakesh.amdocs.assignment.exception.UserNotFoundException;
import com.rakesh.amdocs.assignment.repository.UserProfileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@SpringBootTest

public class UserProfileDTOServiceTest{ /*{

    @Autowired
    private UserProfileService userProfileService;

    @MockBean
    private UserProfileRepository userProfileRepository;

    @Test
    @di
    public void testUploadIdPicture_UserNotFound() {
        Long userId = 1L;
        MultipartFile file = new MockMultipartFile("file", "test.png", "image/png", "image data".getBytes());

        Mockito.when(userProfileRepository.findById(userId)).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> userProfileService.uploadIdPicture(userId, file));
    }

    @Test
    public void testUploadIdPicture_Success() throws IOException {
        Long userId = 1L;
        MultipartFile file = new MockMultipartFile("file", "test.png", "image/png", "image data".getBytes());
        UserProfileDTO userProfile = new UserProfileDTO();
        userProfile.setUserId(userId);

        Mockito.when(userProfileRepository.findById(userId)).thenReturn(Optional.of(userProfile));

        userProfileService.uploadIdPicture(userId, file);

        Mockito.verify(userProfileRepository).save(userProfile);
    }
}*/
}
