import java.util.ArrayList;
import java.util.List;

class BackPack extends Thing {
    private List<Thing> things;
    private int fullThingsWeigt=0;
    public static String name="Заплечный мешок";

    public BackPack(int stage, int power, int price, List<Thing> things) {
        super(name, stage, power, price);
        this.things = things;
    }

    public BackPack(int stage, int power, int price) {
        super(name, stage, power, price);
        this.things = new ArrayList<>() ;
    }

    @Override
    public String toString() {
        return getName() +
                " Ур." + getStage()+
                " Грузоподъемность- "+ getPower()+"кг"+
                " Цена- "+getPrice();
    }

    public List<Thing> getThings() {
        return things;
    }

    public void setThings(List<Thing> things) {
        this.things = things;
    }

    public int getFullThingsWeigt() {
        return fullThingsWeigt;
    }

    public void setFullThingsWeigt(int fullThingsWeigt) {
        this.fullThingsWeigt = fullThingsWeigt;
    }

}
