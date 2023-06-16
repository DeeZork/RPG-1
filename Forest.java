/*import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Forest extends Unit {
    private List<Monster> monsters;

    public Forest(String name, int stage, int power) {
        super(name+"ский лес", stage, power);
        monsters = new ArrayList<>();
        int random = new Random().nextInt(2);
        for (int i = 0; i < stage * 10; i++) {
            int stageM = stage - random + 2;
            int powerM = power - random + 2;
            int waletM = (int) ((int) stage * Math.random() * 10);
            BackPack backPackM = new BackPack();
            switch (random) {
                case 1: {
                    monsters.add(new Goblin());
                    break;
                }
                case 2:monsters.add(new Skeleton());
            }


        }
    } // stage - количество монстров в лесу, power - средний уровень монстров

    public Human battle(Human human) {


        return human;
    }
}

 */