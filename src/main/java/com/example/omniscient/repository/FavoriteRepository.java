package com.example.omniscient.repository;

import com.example.omniscient.entity.FavoriteEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends Neo4jRepository<FavoriteEntity,Long> {
    public Boolean existsFavoriteEntityByName(String name);
    @Query("match (f:Favorite) where f.name=$name return f")
    public FavoriteEntity getFavoriteEntityByName(@Param("name") String name);

    @Query("match (m:Favorite)-[r:Include]->(n:Word) where m.name=$name and r.name=$name return n.name")
    public List<String> getWordEntitiesByIncludes(@Param("name") String name);

    @Query("match (m:User)-[r:Have]->(n:Favorite) where m.account=$name and r.name=$name return n.name")
    public List<String> getFavoriteEntitiesByHaves(@Param("name") String name);

    @Query("match(p:Favorite),(q:Word) where p.name=$name and q.name=$word create (p)-[rel:Include{name:$name}]->(q)")
    public Boolean createInclude(@Param("name") String name,@Param("word") String word);

    @Query("match (f:Favorite)-[r:Include]->(n:Word) where f.name=$name and r.name=$name and n.name=$word detach delete r")
    public Boolean delInclude(@Param("name") String name,@Param("word") String word);

    @Query("match(p:User),(q:Favorite) where p.account=$name and q.name=$favorite create (p)-[rel:Have{name:$name}]->(q)")
    public void createHave(@Param("name") String name, @Param("favorite") String favorite);

}
