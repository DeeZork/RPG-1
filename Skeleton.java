import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Skeleton extends Monster {
    static final String[] whoop = new String[]{"УаааУ", "ГГрруу", "Баззз"};
    private static Random random = new Random();

    public Skeleton(int stage, Human human) {
        super("Скелет ", stage,
                stage * random.nextInt(5)+1,
                stage * random.nextInt(10),
                whoop,
                stage * random.nextInt(20)+30,
                stage * random.nextInt(6)+2,
                new Arms(Arms.gen(stage)),
                new Arms(Arms.gen(stage)));
        List<Fighter> opponents = new ArrayList<>();
        opponents.add(human);
        super.setFighters(opponents);
    }
}
