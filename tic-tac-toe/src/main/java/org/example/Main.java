package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Controller controller = new Controller();
        controller.initialiseGame();
        controller.startGame();
    }
}