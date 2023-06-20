import java.util.Random;

abstract class Monster extends Fighter {
    private static String monsterName() {
        final String letters = "АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЭЮЯ";
        Random random = new Random();
        String name = "";
        int allNameSize=random.nextInt(12);
        for (int i = 0; i < allNameSize; i++)
            name += letters.charAt(random.nextInt(27) + 1);
        return name;
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
}
