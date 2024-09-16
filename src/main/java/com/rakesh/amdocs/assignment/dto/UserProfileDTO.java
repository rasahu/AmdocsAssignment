package com.rakesh.amdocs.assignment.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "USERProfile")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class UserProfileDTO {
    @Id
    private Long userId;
    private String name;
    private String email;
    private String idPictureFileName;

}