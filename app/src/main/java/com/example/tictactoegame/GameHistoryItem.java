package com.example.tictactoegame;

public class GameHistoryItem {

    private int id;
    private String winner;
    private String timestamp;

    public GameHistoryItem(int id, String winner, String timestamp) {
        this.id = id;
        this.winner = winner;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getWinner() {
        return winner;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
