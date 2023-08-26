package com.example.omniscient.relationship;


import com.example.omniscient.entity.FavoriteEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HaveRelationship {
    @Id
    @GeneratedValue
    private Long id;

    @Property("name")
    private String name;

    @TargetNode
    private FavoriteEntity favorite;
}
