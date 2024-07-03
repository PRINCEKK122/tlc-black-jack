package enums;

public enum PlayerStrategy {
    HIT(16),
    STICK(17),
    GO_BUST(22),
    WINNER(21);
    private final int points;

    PlayerStrategy(int points) {
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }
}
