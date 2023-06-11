import java.util.List;

abstract class Fighter extends Unit{
    private int walet;
    private BackPack backPack;
    private String[] whoop;
    private int live;
    private int skill;
    private Arms right;
    private Arms left;

    public Fighter(String name, int stage, int power, int walet, BackPack backPack, String[] whoop, int live, int skill, Arms right, Arms left) {
        super(name, stage, power);
        this.walet = walet;
        this.backPack = backPack;
        this.whoop = whoop;
        this.live = live;
        this.skill = skill;
        this.right = right;
        this.left = left;
    }
}
