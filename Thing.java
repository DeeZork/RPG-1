abstract class Thing extends Unit{
    private int price;

    public int getPrice() {
        return price;
    }

    public Thing(String name, int stage, int power, int price) {
        super(name, stage, power);
        this.price = price;
    }

    @Override
    public String toString() {
        return getName()+" цена - " + price;
    }
}
