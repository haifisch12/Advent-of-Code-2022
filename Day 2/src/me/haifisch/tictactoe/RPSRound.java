package me.haifisch.tictactoe;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RPSRound {

    private static Map<RPSSymbols, RPSSymbols> winningStrat = new HashMap<>() {{
        put(RPSSymbols.Rock, RPSSymbols.Paper);
        put(RPSSymbols.Paper, RPSSymbols.Scissors);
        put(RPSSymbols.Scissors, RPSSymbols.Rock);
    }};

    // first part
    private static Map<String, RPSSymbols> inputToEnumMapper = new HashMap<>() {{
        put("A", RPSSymbols.Rock);
        put("X", RPSSymbols.Rock);
        put("B", RPSSymbols.Paper);
        put("Y", RPSSymbols.Paper);
        put("C", RPSSymbols.Scissors);
        put("Z", RPSSymbols.Scissors);
    }};

    // second part
    private static Map<String, RPSResult> inputToResultMapper = new HashMap<>() {{
        put("X", RPSResult.Loss);
        put("Y", RPSResult.Draw);
        put("Z", RPSResult.Win);
    }};


    private RPSSymbols enemyMove;
    private RPSSymbols myMove;

    // second part
    private RPSResult desiredResult;

    public RPSRound(String line) {
        String[] splittedLine = line.split(" ");

        setEnemyMove(inputToEnumMapper.get(splittedLine[0]));
        setMyMove(inputToEnumMapper.get(splittedLine[1]));
        setDesiredResult(inputToResultMapper.get(splittedLine[1]));
    }

    // first part
    public RPSResult getResult() {
        if (enemyMove == myMove)
            return RPSResult.Draw;
        else if (myMove == winningStrat.get(enemyMove))
            return RPSResult.Win;
        else
            return RPSResult.Loss;
    }

    // second part
    public RPSSymbols getDesiredMove() {
        RPSSymbols desiredMove = RPSSymbols.Rock;

        switch (desiredResult) {
            case Win  -> desiredMove = winningStrat.get(enemyMove);
            case Draw -> desiredMove = enemyMove;
            case Loss -> {
                for (Map.Entry<RPSSymbols, RPSSymbols> entry : winningStrat.entrySet()) {
                    if (Objects.equals(enemyMove, entry.getValue())) {
                        desiredMove = entry.getKey();
                    }
                }
            }
        }

        return desiredMove;
    }

    public RPSSymbols getEnemyMove() {
        return enemyMove;
    }

    public void setEnemyMove(RPSSymbols enemyMove) {
        this.enemyMove = enemyMove;
    }

    public RPSSymbols getMyMove() {
        return myMove;
    }

    public void setMyMove(RPSSymbols myMove) {
        this.myMove = myMove;
    }

    public RPSResult getDesiredResult() {
        return desiredResult;
    }

    public void setDesiredResult(RPSResult desiredResult) {
        this.desiredResult = desiredResult;
    }

    @Override
    public String toString() {
        return this.enemyMove + " " + this.myMove;
    }
}
