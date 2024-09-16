package com.rakesh.amdocs.assignment.repository;

import com.rakesh.amdocs.assignment.dto.UserProfileDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileDTO, Long> {

}
