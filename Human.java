import java.util.List;

class Human extends Fighter{
    private int experience;
    private City place;
    private static String[] whoop= new String[]{"Так тебе!","Получай!!","Пошла раздача!!!"};

    public Human(String name,City place) {
        super(name, 1, 5, 50, new BackPack(1,5,1), whoop, 10, 5,
                new Arms(Arms.getNamesArm(0,0),1,1,0,1,0),
                new Arms(Arms.getNamesArm(0,0),1,1,0,1,0));
        this.experience = 0;
        this.place=place;
    }

    public void setPlace(City place) {
        this.place = place;
    }
}