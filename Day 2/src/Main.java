import me.haifisch.AOC2022.AOC2022Helper;
import me.haifisch.tictactoe.RPSResult;
import me.haifisch.tictactoe.RPSRound;
import me.haifisch.tictactoe.RPSSymbols;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<RPSResult, Integer> resultScoreMap = new HashMap<>() {{
            put(RPSResult.Win, 6);
            put(RPSResult.Draw, 3);
            put(RPSResult.Loss, 0);
        }};

        Map<RPSSymbols, Integer> usedSymbolScoreMap = new HashMap<>() {{
            put(RPSSymbols.Rock, 1);
            put(RPSSymbols.Paper, 2);
            put(RPSSymbols.Scissors, 3);
        }};

        final Integer[] myScore = {0};
        List<RPSRound> rounds = new AOC2022Helper<RPSRound>().getDataForCurrentDay(2, RPSRound::new);

        rounds.forEach(r -> {
            myScore[0] += resultScoreMap.get(r.getDesiredResult());
            // first part
            //myScore[0] += usedSymbolScoreMap.get(r.getMyMove());

            // second part
            myScore[0] += usedSymbolScoreMap.get(r.getDesiredMove());
        });

        System.out.println(myScore[0]);
    }
}