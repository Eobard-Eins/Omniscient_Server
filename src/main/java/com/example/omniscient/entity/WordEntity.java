package com.example.omniscient.entity;

import com.example.omniscient.relationship.RelevRelationship;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.util.*;

@Node("Word")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Property("name")
    private String name;

    @Property("attr")
    private List<String> attr;//Map<String,String> info;

    @Property("value")
    private List<String> value;

    @Relationship(type="Relevancy",direction = Relationship.Direction.OUTGOING)
    private List<RelevRelationship> relevs;

    public Map<String,String> getInfo(){
        Map<String,String> res=new HashMap<>();
        for(int i=0;i<attr.size();i++){
            res.put(attr.get(i), value.get(i));
        }
        return res;
    }

    public void setInfo(Map<String,String> mp){
        if(attr==null) attr=new ArrayList<>();
        if(value==null) value=new ArrayList<>();
        if(!attr.isEmpty()) attr.clear();
        if(!value.isEmpty()) value.clear();
        for(Map.Entry<String,String> t:mp.entrySet()){
            attr.add(0,t.getKey());
            value.add(0,t.getValue());
        }
    }
}
