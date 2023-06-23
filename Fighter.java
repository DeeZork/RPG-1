import java.util.List;
import java.util.Random;

class Fighter extends Unit implements Runnable {
    Thread thread;
    private List<Fighter> fighters;

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

    public void setFighters(List<Fighter> fighters) {
        this.fighters = fighters;
    }

    public void setWalet(int walet) {
        this.walet = walet;
    }

    public void setBackPack(BackPack backPack) {
        this.backPack = backPack;
    }

    public void setLive(int live) {
        this.live = live;
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

    public int getLive() {
        return live;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public Arms getRight() {
        return right;
    }

    public Arms getLeft() {
        return left;
    }


    public void go() {
        thread = new Thread(this, this.getName());
        thread.start();
    }

    @Override
    public void run() {
        this.fight();
    }

    public void fight() {
        for (Fighter fighter : fighters) {// реализуем бой с оппонентами
            while (this.getLive() > 0 && fighter.getLive() > 0)
                this.attak(fighter);
        }
    }

    private synchronized void attak(Fighter fighter) {
        Random random = new Random();
        System.out.println(this.getName() + " '" + this.whoop[random.nextInt(whoop.length)] + "'");
        if (random.nextInt(this.getSkill() + this.getRight().getPower() + this.getLeft().getPower()) >
                random.nextInt(fighter.getSkill() + fighter.getRight().getProtection() + fighter.getLeft().getProtection())) {
            fighter.setLive(fighter.getLive() - this.getRight().getPower() - this.getLeft().getPower());
            System.out.println(this.getName() + " ----> " + fighter.getName() + "-" + (this.getRight().getPower() + this.getLeft().getPower()));
            this.setSkill(this.getSkill() + 1);
        } else System.out.println(this.getName() + "- Атака не удалась");
        if (fighter.getLive() < 0) {
            System.out.println(fighter.getName() + "- ПОВЕРЖЕН!");

        }
        try {
            Thread.sleep(random.nextInt(10000 / this.getSkill()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

