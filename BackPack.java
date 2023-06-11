import java.util.List;

class BackPack extends Thing {
    private List<Thing> things;

    public BackPack(int stage, int power, int price, List<Thing> things) {
        super("BackPack", stage, power, price);
        this.things = things;
    }

    public BackPack(int stage, int power, int price) {
        super("BackPack", stage, power, price);
        this.things = null;
    }
}
