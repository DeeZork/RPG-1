class Potion extends Thing{
    private static String[] potionsName= new String[]{"Пузырек лечебного бальзама","Бутылка лечебного бальзама","Фляга лечебного бальзама"};
    public Potion(int stage, int power, int price) {
        super(potionsName[power-1], stage, power*stage, price);
    }
}
