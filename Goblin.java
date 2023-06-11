import java.util.List;

public class Goblin extends Monster{
    static String[] sound=new String[]{"АшшшАА","РушшАА","Бушша"};

    public Goblin(int stage, int power, int walet, BackPack backPack, int live, int skill, Arms right, Arms left) {
        super("Гоблин", stage, power, walet, backPack, sound, live, skill, right, left);
    }
}
