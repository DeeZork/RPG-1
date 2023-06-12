import java.util.List;

class BackPack extends Thing {
    private List<Thing> things;

    public BackPack(int stage, int power, int price, List<Thing> things) {
        super("Заплечный мешок", stage, power, price);
        this.things = things;
    }

    public BackPack(int stage, int power, int price) {
        super("Заплечный мешок", stage, power, price);
        this.things = null;
    }

    @Override
    public String toString() {
        return getName() +
                " Ур." + getStage()+
                " Грузоподъемность- "+ getPower()+"кг"+
                " Цена- "+getPrice();
    }
}
