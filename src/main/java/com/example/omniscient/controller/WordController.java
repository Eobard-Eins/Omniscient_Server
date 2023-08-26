package com.example.omniscient.controller;

import com.example.omniscient.entity.WordEntity;
import com.example.omniscient.service.WordService;
import com.example.omniscient.unit.RelOfWordToWord;
import com.example.omniscient.unit.WordInfo;
import com.example.omniscient.unit.WordInput;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/Word")
public class WordController {
    @Autowired
    private WordService wordService;
    @GetMapping("/test")
    public String test(){
        return "test";
    }
    @GetMapping("/hasWord")
    public Boolean hasWord(@RequestParam("word") String word){
        return wordService.hasWord(word);
    }

    @GetMapping("/GetInfo")
    public ResponseEntity<WordInfo> getWordInfo(@RequestParam("word") String word){
        return ResponseEntity.ok(wordService.getWordInfo(word));
    }

    @PostMapping("/Create")
    public ResponseEntity<Boolean> createWord(@RequestBody WordEntity wordEntity){
        wordService.createWord(wordEntity);
        return ResponseEntity.ok(wordService.hasWord(wordEntity.getName()));
    }

    @PostMapping("/CreateRel")
    public ResponseEntity<HttpStatus> createRels(@RequestBody RelOfWordToWord rels){
        wordService.createRels(rels);
        return new ResponseEntity<>(HttpStatus.CREATED);//201
    }

    @DeleteMapping("/Del")
    public ResponseEntity<HttpStatus> delWord(@RequestParam("word") String word){
        wordService.delWord(word);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
    }
}
