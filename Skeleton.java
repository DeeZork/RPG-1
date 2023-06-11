import java.util.List;

public class Skeleton extends Monster{
    static String[] sound=new String[]{"УаааУ","ГГрруу","Баззз"};

    public Skeleton(int stage, int power, int walet, BackPack backPack, int live, int skill, Arms right, Arms left) {
        super("Скелет", stage, power, walet, backPack, sound, live, skill, right, left);
    }
}
