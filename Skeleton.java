import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Skeleton extends Monster {
    static String[] whoop = new String[]{"УаааУ", "ГГрруу", "Баззз"};
    private static Random random = new Random();

    public Skeleton(int stage, Human human) {
        super("Скелет ", stage,
                stage * random.nextInt(5),
                stage * random.nextInt(10),
                whoop,
                stage * random.nextInt(6),
                stage * random.nextInt(6),
                new Arms(Arms.gen(stage)),
                new Arms(Arms.gen(stage)));
        List<Fighter> opponents = new ArrayList<>();
        opponents.add(human);
        super.setFighters(opponents);
    }
    @Override
    public String toString() {
        return this.getName()+" Ур."+this.getStage()+", Сила-"+this.getPower()+", В правой-"+this.getRight().getName()+
                ", В левой-"+this.getLeft().getName();
    }

}
