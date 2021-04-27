package com.example.v2.model.roles;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class AllowedTagsForRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long RoleId;

    private Long tagId;

    private boolean status;
}
