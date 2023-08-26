package com.example.omniscient.service;

import com.example.omniscient.entity.WordEntity;
import com.example.omniscient.repository.WordRepository;
import com.example.omniscient.unit.RelOfWordToWord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WordServiceTest {

    @Autowired
    WordService wordService;

    @Autowired
    WordRepository wordRepository;
    @Test
    void hasWord() {
        List<String> l=new ArrayList<>();
        l.add("1");
        l.add("2");
        System.out.println(l);
    }

    @Test
    void getWordInfo() {
        System.out.println(wordService.getWordInfo("test1"));
    }

    @Test
    void createWord() {
        WordEntity w=new WordEntity();
        w.setName("test5");
        Map<String,String> mp=new HashMap<>();
        mp.put("attr1","info1");
        mp.put("attr2","info2");
        System.out.println(mp);
        w.setInfo(mp);
        System.out.println(w.getValue());
        System.out.println(w.getAttr());
        System.out.println(w);
        //wordService.createWord(w);
    }

    @Test
    void createRels() {
        RelOfWordToWord rels=new RelOfWordToWord();
        rels.setWord("test1");
        Map<String, List<String>> mp=new HashMap<>();
        mp.put("attr1",List.of("test3"));
        mp.put("attr2",List.of("test2"));
        rels.setRelev(mp);
        wordService.createRels(rels);
        System.out.println(wordService.getWordInfo("test1"));
    }

    @Test
    void delWord() {
        wordService.delWord("test3");
    }
}