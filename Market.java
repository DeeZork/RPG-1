import java.util.ArrayList;
import java.util.List;

class Market extends Unit {
    private List<Thing> stock;

    public Market(String name, int stage, int power) {
        super(name, stage, power);
//        stage - Уровень магазина определяет максимальный уровень вещей в нем
//        power - определяет количество вещей в продаже
        this.stock = new ArrayList<>();
        for (int i = 0; i < power; i++) {
            switch ((int) Math.floor(Math.random() * 3)) {
                case 0: {
                    int stageBP = (int) Math.floor(Math.random() * stage)+1;// уровень
                    int bpp = (int) Math.round(Math.random() * 5 * stage)+10;// грузоподъемность рюкзака
                    stock.add(new BackPack(stageBP, bpp, stage * bpp * 2));
                    break;
                }
                case 1: {
                    int typA = (int) Math.floor(Math.random() * 2);
                    int nameA = (int) Math.round(Math.random() * 6);// имя,всего 7 видов оружия/защиты
                    int stageA = (int) Math.floor(Math.random() * stage)+1;// уровень
                    int powerA;
                    int protectA;
                    if (typA == 0) {
                        powerA = (int) Math.round(Math.random() * stage * nameA * 10)+1;// сила атаки
                        protectA = (int) Math.round(Math.random() * stage * nameA * 3)+1;// защита
                    } else {
                        powerA = (int) Math.round(Math.random() * stage * nameA * 3)+1;// сила атаки
                        protectA = (int) Math.round(Math.random() * stage * nameA * 10)+1;// защита
                    }
                    int weigtA = (int) Math.round(Math.random() * stage * nameA * (typA + 1) * 1.5)+1;//вес
                    int priceA = (int) Math.round(Math.random() * stage * (powerA + protectA - weigtA + 1))+1;// цена
                    stock.add(new Arms(Arms.getNamesArm(typA, nameA), stageA, powerA, priceA, protectA, weigtA));
                    break;
                }
                case 2: {
                    int stageP = (int) Math.floor(Math.random() * stage) + 1;// уровень
                    int powerP = (int) Math.floor(Math.random() * 3) + 1;// уровень
                    int priceP = (int) Math.floor(Math.random() * stage * powerP * 10)+1;// уровень
                    stock.add(new Potion(stageP, powerP, priceP));
                }
            }
        }
    }

    @Override
    public String toString() {
        return getName()+" товары " +getStage() +" уровня";
    }

    public void shoping(Human human){
        System.out.println(this.getName()+"\nТовары в продаже:");
        for (int i=0; i<this.stock.size();i++)
            System.out.println(i+1+") "+this.stock.get(i));
        System.out.println("X) Выход");
        String choice = Checker.check(this.getPower());


    }
}
