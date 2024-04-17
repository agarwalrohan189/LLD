package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Controller {
    Game game;
    private static final int BOARD_SIZE = 3;

    public Controller() {
        this.game = new Game(BOARD_SIZE);
    }

    public void initialiseGame(){

        //create and add players
        Player player1 = new Player(UUID.randomUUID().toString(), "FOO", Symbol.X);
        Player player2 = new Player(UUID.randomUUID().toString(), "BAR", Symbol.O);
        game.addPlayer(player1);
        game.addPlayer(player2);
    }

    public void startGame(){
        boolean winnerFound = false;
        while(!winnerFound){
            try {
                Player playerWithTurn = game.getTurn();
                System.out.printf("Turn for player: %s with symbol: %s\n",  playerWithTurn.getName(), playerWithTurn.getSymbol().name());
                game.show();
                System.out.println("Enter row and column for your move: ");

                Move move = createMove(playerWithTurn);
                try{
                    game.makeMove(move);
                } catch (GameException e) {
                    System.err.printf("Move Error: %s%n", e.getMessage());
                    continue;
                }

                if(game.isWinning(move)){
                    winnerFound = true;
                    System.out.printf("Player : %s won\n", playerWithTurn.getName());
                }
                if(game.isTerminated()){
                    System.out.println("Game ended in a tie");
                    break;
                }

                game.nextTurn();

            } catch (GameException e) {
                System.err.printf("Game failed with the exception: %s%n", e.getMessage());
                break;
            }
        }
        game.show();
    }

    private List<Integer> takeMoveInput(){

        Scanner inputScanner = new Scanner(System.in);
        String input = inputScanner.nextLine();
        String[] values = input.split(",");
        int inputRow = Integer.parseInt(values[0]);
        int inputColumn = Integer.parseInt(values[1]);

        return List.of(inputRow, inputColumn);
    }

    private Move createMove(Player player){

        List<Integer> coordinates = takeMoveInput();
        return new Move(player.getId(), coordinates.get(0), coordinates.get(1), player.getSymbol());
    }
}
