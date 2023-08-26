package com.example.omniscient.service;

import com.example.omniscient.entity.WordEntity;
import com.example.omniscient.relationship.RelevRelationship;
import com.example.omniscient.repository.WordRepository;
import com.example.omniscient.unit.RelOfWordToWord;
import com.example.omniscient.unit.WordInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    public Boolean hasWord(String word){
        return wordRepository.existsWordEntityByName(word);
    }

    public WordInfo getWordInfo(String word){
        WordInfo res=new WordInfo();
        WordEntity node=wordRepository.getWordEntityByName(word);
        res.setName(word);
        res.setAttr(node.getAttr());
        res.setInfo(node.getInfo());
        Map<String,List<String>> rel=new HashMap<>();
        for(String attr: node.getAttr()){
            List<String> rels=wordRepository.getWordEntityByRelevs(word,attr);
            rel.put(attr,rels);
        }
        res.setRelev(rel);
        return res;
    }

    public void createWord(WordEntity wordEntity){
        if(!wordRepository.existsWordEntityByName(wordEntity.getName())) wordRepository.save(wordEntity);
    }

    public void createRels(RelOfWordToWord rels){
        Map<String , List<String>> mp=rels.getRelev();//属性到一组终点名字的映射
        for(Map.Entry<String , List<String>> t:mp.entrySet()){
            String key= t.getKey();//每个属性
            List<String> value=t.getValue();//每组终点

            for(String EndWord:value){//每个终点
                if(Objects.equals(EndWord, "")) continue;
                wordRepository.createInclude(rels.getWord(),EndWord,key);
            }
        }
    }

    public void delWord(String word){
        WordEntity temp=wordRepository.getWordEntityByName(word);
        if(temp!=null) wordRepository.delete(temp);
    }
}
