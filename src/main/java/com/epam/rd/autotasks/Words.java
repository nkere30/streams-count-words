package com.epam.rd.autotasks;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Words {

    public String countWords(List<String> lines) {
        String wordsStatistics = lines
                .stream()
                .map((line) -> line.split("[\"\\s-‘/'’”“$&+,:;=?@#|<>.^*()%!—-]"))
                .flatMap(Arrays::stream)
                .map(String::toLowerCase)
                .filter((word) -> word.length() >= 4)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry::getKey))
                .filter((entry) -> entry.getValue() >= 10)
                .map((entry) -> entry.getKey() + " - " + entry.getValue() + "\n")
                .collect(Collectors.joining());
        return wordsStatistics.substring(0, wordsStatistics.length() - 1);
    }
}
