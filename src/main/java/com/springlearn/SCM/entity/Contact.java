package com.springlearn.SCM.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {

   @Id
   private  String id;
   private  String name;
   private  String email;
   private  String phone;
   private  String address;
   private  String description;
   @Builder.Default
   private boolean favorite=false;

   @ManyToOne
   @JsonIgnore
   private User user;

   @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
   @Builder.Default
   private List<SocialLink> links=new ArrayList<>();
}
