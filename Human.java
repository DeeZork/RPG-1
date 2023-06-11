import java.util.List;

class Human extends Fighter{
    private int experience;
    private static String[] whoop= new String[]{"Так тебе!","Получай!!","Пошла раздача!!!"};

    public Human(String name) {
        super(name, 1, 5, 10, null, whoop, 10, 5,
                new Arms(Arms.getNamesArm(0,0),1,1,0,1,0),
                new Arms(Arms.getNamesArm(0,0),1,1,0,1,0));
        this.experience = 0;
    }

}
