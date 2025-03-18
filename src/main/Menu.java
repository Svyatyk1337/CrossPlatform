package main;

import models.Player;
import services.PlayerService;

import java.util.List;
import java.util.Scanner;

public class Menu {
    
    private final Scanner scanner;
    private final PlayerService playerService;
    
    public Menu(Scanner scanner, PlayerService playerService) {
        this.scanner = scanner;
        this.playerService = playerService;
    }

    public void start() {
        
        while (true) {
            System.out.println("\n==============================");
            System.out.println("⚽  Менеджер Футбольних Трансферів");
            System.out.println("==============================");
            System.out.println("1️⃣  Додати гравця");
            System.out.println("2️⃣  Відобразити всіх гравців");
            System.out.println("3️⃣  Оновити дані гравця");
            System.out.println("4️⃣  Видалити гравця");
            System.out.println("5️⃣  Пошук гравця");
            System.out.println("6️⃣  Сортувати гравців");
            System.out.println("7️⃣  Вийти");
            System.out.print("🔹 Виберіть опцію: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addPlayer();
                case 2 -> showAllPlayers();
                case 3 -> updatePlayer();
                case 4 -> deletePlayer();
                case 5 -> searchPlayer();
                case 6 -> sortPlayers();
                case 7 -> System.exit(0);
                default -> System.out.println("Некоректний вибір.");
            }
        }
    }

    private void addPlayer() {
        System.out.print("Введіть ім'я гравця: ");
        String name = scanner.nextLine();
        System.out.print("Введіть позицію: ");
        String position = scanner.nextLine();
        System.out.print("Введіть рейтинг (0-100): ");
        int rating = scanner.nextInt();
        System.out.print("Введіть ціну (млн €): ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        playerService.addPlayer(name, position, rating, price);
    }

    private  void showAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        if (players.isEmpty()) {
            System.out.println("Немає зареєстрованих гравців.");
        } else {
            players.forEach(System.out::println);
        }
    }

    private  void updatePlayer() {
        System.out.print("Введіть ім'я гравця для оновлення: ");
        String name = scanner.nextLine();
        System.out.print("Введіть новий рейтинг (0-100): ");
        int newRating = scanner.nextInt();
        System.out.print("Введіть нову ціну (млн €): ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();

        playerService.updatePlayer(name, newRating, newPrice);
    }

    private  void deletePlayer() {
        System.out.print("Введіть ім'я гравця для видалення: ");
        String name = scanner.nextLine();
        playerService.deletePlayer(name);
    }

    private  void searchPlayer() {
        System.out.print("Введіть ім'я для пошуку: ");
        String name = scanner.nextLine();
        List<Player> players = playerService.searchPlayers(name);

        if (players.isEmpty()) {
            System.out.println("Гравця не знайдено.");
        } else {
            players.forEach(System.out::println);
        }
    }

    private  void sortPlayers() {
        System.out.println("1️⃣  Сортувати за ціною");
        System.out.println("2️⃣  Сортувати за рейтингом");
        System.out.print("🔹 Виберіть опцію: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            playerService.sortPlayersByPrice();
        } else if (choice == 2) {
            playerService.sortPlayersByRating();
        } else {
            System.out.println("Некоректний вибір.");
        }

        showAllPlayers();
    }
}
