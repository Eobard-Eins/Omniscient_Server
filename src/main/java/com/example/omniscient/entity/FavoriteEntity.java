package com.example.omniscient.entity;


import com.example.omniscient.relationship.IncludeRelationship;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Node("Favorite")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Property("name")
    private String name;

    @Relationship(type = "Include",direction = Relationship.Direction.OUTGOING)
    private List<IncludeRelationship> includes;
}
