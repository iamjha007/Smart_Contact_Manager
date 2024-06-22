package com.springlearn.SCM.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private String about;
    private String profilePic;
    private String phone;
    private boolean enabled=false;
    private boolean emailVerified=false;;
    private boolean phoneVerified=false;

    //how the user logged in to our website
    private Providers provider=Providers.SELF;
    private String providerId;

    //contactList
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contactList;


}
