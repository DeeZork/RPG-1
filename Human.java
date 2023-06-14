class Human extends Fighter{
    private int experience;
    private City place;
    private static String[] whoop= new String[]{"Так тебе!","Получай!!","Пошла раздача!!!"};

    public Human(String name,City place) {
        super(name, 1, 5, 1000, new BackPack(1,5,5), whoop, 10, 5,
                new Arms(Arms.getNamesArm(0,0),1,1,0,1,0),
                new Arms(Arms.getNamesArm(0,0),1,1,0,1,0));
        this.experience = 0;
        this.place=place;
    }

    public synchronized void dressed() {
        System.out.println("Переодеваемся");
    }

    public void setPlace(City place) {
        this.place = place;
    }
}
