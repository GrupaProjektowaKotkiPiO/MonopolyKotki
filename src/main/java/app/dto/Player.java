package app.dto;

public class Player {
    private String name;
    int position = 0;
    boolean inJail = false;
    int timeInJail = 0;
    private int money = 1500;

    public Player(String name) {
        this.name=name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBudget() {
        return money;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
