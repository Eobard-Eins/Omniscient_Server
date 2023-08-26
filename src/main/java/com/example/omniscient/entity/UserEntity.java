package com.example.omniscient.entity;

import com.example.omniscient.relationship.HaveRelationship;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Node("User")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Property("account")
    private String account;

    @Property("username")
    private String username;

    @Property("password")
    private String password;

    @Relationship(type = "Have",direction = Relationship.Direction.OUTGOING)
    private List<HaveRelationship> haves;

    private Boolean pass;
}
