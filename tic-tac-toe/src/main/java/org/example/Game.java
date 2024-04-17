package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game {
    private Queue<Player> playerQueue;
    private Board board;
    private List<Move> moves;
    private static final int MAX_PLAYER_CAPACITY = 100;

    public Game(int boardSize) {
        board = new Board(boardSize);
        playerQueue = new ArrayBlockingQueue<>(MAX_PLAYER_CAPACITY);
        moves = new CopyOnWriteArrayList<>();
    }

    public Game(int boardSize, List<Player> players) {
        board = new Board(boardSize);
        playerQueue = new ArrayBlockingQueue<>(MAX_PLAYER_CAPACITY, true, players);
        moves = new CopyOnWriteArrayList<>();
    }

    public boolean addPlayer(Player player){
        return playerQueue.add(player);
    }

    public Player getTurn() throws GameException {
        if(playerQueue.isEmpty()){
            throw new GameException("Empty Player Queue");
        }
        return playerQueue.peek();
    }

    public void nextTurn(){
        Player playerInFront = playerQueue.poll();
        playerQueue.add(playerInFront);
    }

    public void show(){
        board.printBoard();
    }

    public void makeMove(Move move) throws GameException {
        if(board.isCellFilled(move.getRow(), move.getCol())){
            throw new GameException(String.format("Invalid Move! Cell with row %s and col %s already filled", move.getRow(), move.getCol()));
        }
        board.fillCell(move.getRow(), move.getCol(), move.getSymbol());
    }

    public boolean isWinning(Move move){
        return board.checkForMatch(move.getRow(), move.getCol(), move.getSymbol());
    }

    public boolean isTerminated(){
        return board.isFilled();
    }

}
