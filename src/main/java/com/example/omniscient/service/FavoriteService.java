package com.example.omniscient.service;


import com.example.omniscient.entity.FavoriteEntity;
import com.example.omniscient.entity.WordEntity;
import com.example.omniscient.relationship.IncludeRelationship;
import com.example.omniscient.repository.FavoriteRepository;
import com.example.omniscient.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;


    public FavoriteEntity createFold(String account,String favorite) {
        String s=account+favorite;
        int i=1;
        if(favoriteRepository.existsFavoriteEntityByName(s)){
            while(favoriteRepository.existsFavoriteEntityByName(s+i)) i++;
            s=s+i;
        }
        FavoriteEntity f=new FavoriteEntity();
        f.setName(s);
        favoriteRepository.save(f);
        favoriteRepository.createHave(account,s);
        return f;
    }

    public Boolean addWord(String s, String word) {
        favoriteRepository.createInclude(s,word);
        List<String> words=favoriteRepository.getWordEntitiesByIncludes(s);
        return words.contains(word);
    }

    public Boolean delWord(String s, String word) {
        favoriteRepository.delInclude(s,word);
        List<String> words=favoriteRepository.getWordEntitiesByIncludes(s);
        return !words.contains(word);
    }

    public List<String> getAllWord(String s) {
        List<String> ls=favoriteRepository.getWordEntitiesByIncludes(s);
        Collections.reverse(ls);
        return ls;
    }

    public List<String> getAllFold(String account) {
        List<String> l=favoriteRepository.getFavoriteEntitiesByHaves(account);
        Collections.reverse(l);
        return l;
    }

    public void delFold(String s) {
        FavoriteEntity f=favoriteRepository.getFavoriteEntityByName(s);
        if(f!=null) favoriteRepository.delete(f);
    }
}
