import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Goblin extends Monster {
    static String[] whoop = new String[]{"АшшшАА", "РушшАА", "Бушша"};
    private static Random random = new Random();

    public Goblin(int stage, Human human) {
        super("Гоблин ", stage,
                stage * random.nextInt(5)+1,
                stage * random.nextInt(10),
                whoop,
                stage * random.nextInt(30)+20,
                stage * random.nextInt(10)+5,
                new Arms(Arms.gen(stage)),
                new Arms(Arms.gen(stage)));
        List<Fighter> opponents = new ArrayList<>();
        opponents.add(human);
        super.setFighters(opponents);
    }
}
