package com.example.omniscient.unit;

import com.example.omniscient.relationship.RelevRelationship;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordInput {

    private String name;

    private List<String> attr;//Map<String,String> info;

    private List<String> value;

}
