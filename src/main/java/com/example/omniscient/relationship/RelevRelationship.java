package com.example.omniscient.relationship;

import com.example.omniscient.entity.WordEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelevRelationship {
    @Id
    @GeneratedValue
    private Long id;

    @Property("name")
    private String name;

    @TargetNode
    private WordEntity word;
}
