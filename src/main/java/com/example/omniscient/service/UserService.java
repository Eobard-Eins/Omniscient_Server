package com.example.omniscient.service;

import com.example.omniscient.entity.FavoriteEntity;
import com.example.omniscient.entity.UserEntity;
import com.example.omniscient.relationship.HaveRelationship;
import com.example.omniscient.repository.FavoriteRepository;
import com.example.omniscient.repository.UserRepository;
import com.example.omniscient.unit.UserRegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;

    public UserEntity login(UserEntity userEntity) {
        if(userRepository.existsUserEntityByAccount(userEntity.getAccount())){
            UserEntity u=userRepository.getUserEntityByAccount(userEntity.getAccount());
            u.setPass(Objects.equals(u.getPassword(), userEntity.getPassword()));
            return u;
        }
        return UserEntity.builder().pass(false).build();
    }

    public UserRegisterInfo<?> register(UserEntity userEntity) {
        if(userRepository.existsUserEntityByAccount(userEntity.getAccount()))
            return new UserRegisterInfo<String>(true,"账号不唯一");

        //创收藏夹
        FavoriteEntity f=new FavoriteEntity();
        f.setName(userEntity.getAccount()+"默认收藏夹");
        favoriteRepository.save(f);

        //创关系
        List<HaveRelationship> l=new ArrayList<>();
        HaveRelationship h=new HaveRelationship();
        h.setFavorite(f);
        h.setName(userEntity.getAccount());
        l.add(h);
        userEntity.setHaves(l);

        //保存
        userRepository.save(userEntity);

        return new UserRegisterInfo<UserEntity>(false,userEntity);
    }

    public UserEntity getInfo(String account) {
        return userRepository.getUserEntityByAccount(account);
    }

    public Boolean setInfo(UserEntity userEntity) {
        UserEntity u=userRepository.getUserEntityByAccount(userEntity.getAccount());
        if(!Objects.equals(userEntity.getPassword(), "")){
            u.setPassword(userEntity.getPassword());
        }
        if(!Objects.equals(userEntity.getUsername(), "")){
            u.setUsername(userEntity.getUsername());
        }
        userRepository.save(u);
        boolean res=true;
        UserEntity u1=userRepository.getUserEntityByAccount(userEntity.getAccount());
        if(!Objects.equals(userEntity.getPassword(), "")){
            res= res&&Objects.equals(u1.getPassword(), userEntity.getPassword());
        }
        if(!Objects.equals(userEntity.getUsername(), "")){
            res=res&&(Objects.equals(u1.getUsername(), userEntity.getUsername()));
        }
        return res;
    }

    public void delUser(String account) {
        UserEntity u=userRepository.getUserEntityByAccount(account);
        List<String> lf=userRepository.getFavoriteEntitiesByHaves(account);
        for (String name:lf){
            FavoriteEntity f=favoriteRepository.getFavoriteEntityByName(name);
            favoriteRepository.delete(f);
        }
        if(u!=null) userRepository.delete(u);
    }
}
