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

    public void setWalet(int walet) {
        this.walet = walet;
    }

    public void setBackPack(BackPack backPack) {
        this.backPack = backPack;
    }

    public void setWhoop(String[] whoop) {
        this.whoop = whoop;
    }

    public void setLive(int live) {
        this.live = live;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public void setRight(Arms right) {
        this.right = right;
    }

    public void setLeft(Arms left) {
        this.left = left;
    }

    public int getWalet() {
        return walet;
    }

    public BackPack getBackPack() {
        return backPack;
    }

    public String[] getWhoop() {
        return whoop;
    }

    public int getLive() {
        return live;
    }

    public int getSkill() {
        return skill;
    }

    public Arms getRight() {
        return right;
    }

    public Arms getLeft() {
        return left;
    }
}
