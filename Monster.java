import java.util.List;

abstract class Monster extends Fighter{
    public Monster(String name, int stage, int power, int walet, BackPack backPack, String[] sound, int live, int skill, Arms right, Arms left) {
        super(name, stage, power, walet, backPack, sound, live, skill, right, left);
    }
}
