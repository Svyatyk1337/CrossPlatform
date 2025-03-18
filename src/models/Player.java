package models;

public class Player {
    private String name;
    private String position;
    private int rating;
    private double price; 

    public Player(String name, String position, int rating, double price) {
        this.name = name;
        this.position = position;
        this.rating = rating;
        this.price = price;
    }

    public String getName() { return name; }
    public String getPosition() { return position; }
    public int getRating() { return rating; }
    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }
    public void setRating(int rating) { this.rating = rating; }

    @Override
    public String toString() {
        return "📌 " + name + " | " + position + " | Рейтинг: " + rating + " | Ціна: " + price + " млн €";
    }
}
