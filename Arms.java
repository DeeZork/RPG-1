class Arms extends Thing{
    private int protection;
    private int weight;
    private static String[][] namesArm =new String[][]{{"Кулак","Палка","Нож","Кинжал","Короткий Меч","Средний Меч","Длинный Меч","Булава"},
            {"Кулак","Маленький деревянный щит","Средний деревянный щит","Большой деревянный щит",
                    "Маленький железный щит","Средний железный щит","Большой железный щит","Заговоренный щит"}};

    public int getProtection() {
        return protection;
    }

    public int getWeight() {
        return weight;
    }

    public static String getNamesArm(int i,int j) {
        return namesArm[i][j];  // от 0 до 7
    }

    public Arms(String name, int stage, int power, int price, int protection, int weight) {
        super(name, stage, power, price);
        this.protection = protection;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return getName() +
                " Ур." + getStage()+
                " Вес- " +getWeight()+"кг"+
                " Атака +"+getPower()+
                " Защита +"+getProtection() +
                " Цена- " + getPrice();
    }
}
