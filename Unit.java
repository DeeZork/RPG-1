abstract class Unit {
    private String name;
    private int stage;
    private int power;

    public String getName() {
        return name;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getPower() {
        return power;
    }

    public Unit(String name, int stage, int power) {
        this.name = name;
        this.stage = stage;
        this.power = power;
    }
}
