package com.example.omniscient.repository;

import com.example.omniscient.entity.FavoriteEntity;
import com.example.omniscient.entity.UserEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends Neo4jRepository<UserEntity,Long> {
    public Boolean existsUserEntityByAccount(String account);

    @Query("match (user:User) where user.account=$account return user")
    public UserEntity getUserEntityByAccount(@Param("account") String account);

    @Query("match (m:User)-[r:Have]->(n:Favorite) where m.account=$name and r.name=$name return n.name")
    public List<String> getFavoriteEntitiesByHaves(@Param("name") String name);
}
