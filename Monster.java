import java.util.Random;

abstract class Monster extends Fighter {
    private static String monsterName() {
        final String letters = "йцукенгшщзхфывапролджэячсмитбю";
        Random random = new Random();
        String name = "";
        int allNameSize=random.nextInt(5)+1;
        for (int i = 0; i < allNameSize; i++)
            name += letters.charAt(random.nextInt(29) + 1);
        return name.replaceFirst(String.valueOf(name.charAt(0)), String.valueOf(name.charAt(0)).toUpperCase());
    }

    private static BackPack monsterBPT(int stage) {
        Random random = new Random();
        int bpPower = stage * random.nextInt(10);
        BackPack backPack = new BackPack(stage, bpPower, stage * random.nextInt(10));
        for (int i = 0; i < random.nextInt(10); i++) {
            switch (random.nextInt(2)) {
                case 0: {
                    Arms arm = new Arms(Arms.gen(stage));
                    if (bpPower <= (backPack.getFullThingsWeigt() + arm.getWeight()))
                        backPack.getThings().add(arm);
                    break;
                }
                case 1:
                    backPack.getThings().add(new Potion(Potion.gen(stage)));
            }
        }
        return backPack;
    }

    public Monster(String name, int stage, int power, int walet, String[] sound, int live, int skill, Arms right, Arms left) {
        super(name + monsterName(), stage, power, walet, monsterBPT(stage), sound, live, skill, right, left);
    }
    @Override
    public String toString() {
        return this.getName()+" Ур."+this.getStage()+", Здоровье-"+this.getLive()+", В правой-"+this.getRight().getName()+
                ", В левой-"+this.getLeft().getName();
    }
}
