package main;

import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import services.PlayerService;

public class Main {

    public static void main(String[] args) {
        String filePath = "data/players.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        PlayerService playerService = new PlayerService(filePath, gson);
        Menu menu = new Menu(new Scanner(System.in), playerService);
        menu.start();
    }
    
}
