package com.example.omniscient.unit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WordInfo {
    private String name;

    private List<String> attr;

    private Map<String,String> info;

    private Map<String, List<String>> relev;
}
