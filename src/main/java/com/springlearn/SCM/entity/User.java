package com.springlearn.SCM.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {


    @Id
    private String id;

    @Column(nullable = false)
    private String name;
    //emailid is our username
    @Column(nullable = false, unique = true)
    private String email;
    @Getter(AccessLevel.NONE)
    private String password;
    private String about;
    private String profilePic;
    private String phone;
    @Builder.Default
    @Getter(AccessLevel.NONE)
    private boolean enabled=true;
    @Builder.Default
    private boolean emailVerified=false;
    @Builder.Default
    private boolean phoneVerified=false;

    //how the user logged in to our website
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Providers provider=Providers.SELF;
    private String providerId;

    //contactList
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contactList;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> rolesList=new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<SimpleGrantedAuthority> roles = this.rolesList.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());

        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {

        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
