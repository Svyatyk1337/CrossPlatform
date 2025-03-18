package services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerService {
    private final String filePath;
    private final Gson gson;
    private List<Player> players;

    public PlayerService(String filePath, Gson gson) {
        this.filePath = filePath;
        this.gson = gson;
        this.players = loadPlayers();
    }

    private List<Player> loadPlayers() {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, new TypeToken<List<Player>>() {}.getType());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void savePlayers() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(players, writer);
        } catch (IOException e) {
            System.out.println("Помилка збереження JSON!");
        }
    }

    public void addPlayer(String name, String position, int rating, double price) {
        players.add(new Player(name, position, rating, price));
        savePlayers();
        System.out.println("Гравець доданий!");
    }

    public List<Player> getAllPlayers() {
        return players;
    }

    public boolean updatePlayer(String name, int newRating, double newPrice) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(name)) {
                player.setRating(newRating);
                player.setPrice(newPrice);
                savePlayers();
                System.out.println("Дані оновлено!");
                return true;
            }
        }
        System.out.println("Гравця не знайдено.");
        return false;
    }

    public boolean deletePlayer(String name) {
        boolean removed = players.removeIf(player -> player.getName().equalsIgnoreCase(name));
        savePlayers();
        System.out.println(removed ? "Гравця видалено!" : "Гравця не знайдено.");
        return removed;
    }

    public List<Player> searchPlayers(String name) {
        return players.stream()
                .filter(player -> player.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void sortPlayersByPrice() {
        players.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
    }

    public void sortPlayersByRating() {
        players.sort((p1, p2) -> Integer.compare(p2.getRating(), p1.getRating()));
    }
}
