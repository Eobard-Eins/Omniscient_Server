package com.example.omniscient.repository;

import com.example.omniscient.entity.WordEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends Neo4jRepository<WordEntity,Long> {
    public Boolean existsWordEntityByName(String name);

    @Query("match (word:Word) where word.name=$name return word")
    public WordEntity getWordEntityByName(@Param("name") String name);

    @Query("match (word:Word)-[r:Relevancy]->(n:Word) where word.name=$name and r.name=$rel return n.name")
    public List<String> getWordEntityByRelevs(@Param("name") String name,@Param("rel") String rel);

    @Query("match(p:Word),(q:Word) where p.name=$name and q.name=$word create (p)-[rel:Relevancy{name:$attr}]->(q)")
    public Boolean createInclude(@Param("name") String name,@Param("word") String word,@Param("attr") String attr);
}
