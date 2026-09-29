package dk.rebecca.model;

public class Transaction {
    private int id;
    private double amount;
    private String category;
    private String description;
    private String type;
    private String date;

    public Transaction() {
    }

    public Transaction(int id, double amount, String category, String description, String type, String date) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.type = type;
        this.date = date;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

}
