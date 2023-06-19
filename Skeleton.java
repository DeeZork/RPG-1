import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Skeleton extends Monster implements Runnable, Go {
    private Thread thread;
    static String[] sound = new String[]{"УаааУ", "ГГрруу", "Баззз"};
    private static Random random = new Random();

    List<Fighter> opponents = new ArrayList<>();

    public Skeleton(int stage, Human human) {
        super("Скелет ", stage,
                stage * random.nextInt(5),
                stage * random.nextInt(10),
                sound,
                stage * random.nextInt(6),
                stage * random.nextInt(6),
                new Arms(Arms.gen(stage)),
                new Arms(Arms.gen(stage)));
        opponents.add(human);
    }

    public void go() {
        thread = new Thread(this, this.getName());
        thread.start();
    }

    @Override
    public void run() {
        super.fight(opponents);

    }
}
