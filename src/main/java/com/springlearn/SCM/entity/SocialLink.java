package com.springlearn.SCM.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialLink {

    @Id
    private String id;
    private String link;
    private String title;

    @ManyToOne
    private Contact contact;

}
