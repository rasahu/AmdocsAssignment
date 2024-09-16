package com.rakesh.amdocs.assignment.dto;

import jakarta.persistence.*;

import lombok.*;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "USERProfile")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class UserProfileDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String email;
    private String idPictureFileName;

}