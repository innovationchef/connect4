package com.innovationchef.utiltiy;

import com.innovationchef.ApplicationTests;
import com.innovationchef.constant.Player;
import com.innovationchef.model.TestMoveSequence;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestFileReader {

    public static BufferedReader fileReader(String filename) {
        ClassLoader classLoader = ApplicationTests.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream(filename);
        return new BufferedReader(new InputStreamReader(is));
    }

    public static List<TestMoveSequence> readMoveSeqTestFile(String fileName) {
        List<String> content = fileReader(fileName).lines().collect(Collectors.toList());
        List<TestMoveSequence> testMoveSequences = new ArrayList<>();
        int i = 1, j = 1;
        TestMoveSequence tCase = new TestMoveSequence();
        for (String line : content) {
            if (i % 5 == 0) {
                i = 1;
                j = 1;
                testMoveSequences.add(tCase);
                tCase = new TestMoveSequence();
                continue;
            }

            Function<String, LinkedList<Integer>> parseMoveLine = line1 ->
                    Arrays.stream(line1.split(":")[1].trim().split(","))
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .map(x -> x - 1)
                            .collect(Collectors.toCollection(LinkedList::new));
            switch (j) {
                case 1:
                    tCase.setBeginner(Player.fromString(line.split(":")[1].trim()));
                    break;
                case 2:
                    LinkedList<Integer> list1 = parseMoveLine.apply(line);
                    if (tCase.getBeginner().equals(Player.RED))
                        tCase.getP1Moves().addAll(list1);
                    else
                        tCase.getP2Moves().addAll(list1);
                    break;
                case 3:
                    LinkedList<Integer> list2 = parseMoveLine.apply(line);
                    if (tCase.getBeginner().equals(Player.RED))
                        tCase.getP2Moves().addAll(list2);
                    else
                        tCase.getP1Moves().addAll(list2);
                    break;
                case 4:
                    tCase.setWinner(Player.fromString(line.split(":")[1].trim()));
                    break;
            }
            i++;
            j++;
        }
        // testMoveSequences.forEach(System.out::println);
        return testMoveSequences;
    }
}
