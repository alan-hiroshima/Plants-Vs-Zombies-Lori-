import java.util.Comparator;

public abstract class Plant implements Comparable<Plant> {
    public static final int INFINITY = Integer.MAX_VALUE;
    String name;
    int hp;
    int sun_cost;

    public Plant (String name, int hp, int sun_cost) {
        this.name = name;
        this.hp = hp;
        this.sun_cost = sun_cost;
    }

    public Plant (String name, int sun_cost) {
        this.name = name;
        this.sun_cost = sun_cost;
        this.hp = 6;
    }

    @Override
    public String toString() {
        String s = "";
        if (hp == 6){
            s = name + " (" + hp + ") - cost: " + sun_cost;
        } else {
            s = name + " (∞) - cost: " + sun_cost;
        }
        return s;
    }

    public int compareTo(Plant p) {
        return name.compareTo(p.name);
    }

    public static class sortByHp implements Comparator<Plant> {
        public int compare(Plant p1, Plant p2){
            if (p2.hp == p1.hp){
                return p1.name.compareTo(p2.name);
            }
            return Integer.compare(p2.hp, p1.hp);
        }
    }

    public static class sortBySunCost implements Comparator<Plant> {
        public int compare(Plant p1, Plant p2){
            if (p2.sun_cost == p1.sun_cost){
                return p1.name.compareTo(p2.name);
            }
            return Integer.compare(p2.sun_cost, p1.sun_cost);
        }
    }

    // subclasses

    public static class Sunflower extends Plant implements SunProducer{
        public Sunflower(){
            super("Sunflower", 50);
        }
        @Override
        public int produce_sun() {
            return 0;
        }
    }

    public static class TwinSunflower extends Plant implements PlantUpgrade{
        public TwinSunflower(){
            super("Twin Sunflower", 250);
        }

        @Override
        public int concurrentSunCost() {
            return sun_cost+50;
        }
    }

    public static class Peashooter extends Plant implements Attacker{
        public Peashooter(){
            super("Peashooter", 100);
        }

        @Override
        public int attack() {
            System.out.println(name + " attacks");
            return 1;
        }

        @Override
        public int rangeType() { // single line = 1
            return 1;
        }
    }

    public static class WallNut extends Plant {
        public WallNut(){
            super("Wall Nut", 25, 50);
        }
    }

    public static class Squash extends Plant implements Attacker, InstantKiller{
        public Squash(){
            super("Squash", INFINITY, 50);
        }

        @Override
        public int attack() {
            System.out.println(name + " dies while squashing zombies");
            return 3;
        }

        @Override
        public int rangeType() { // Limited range = 3
            return 3;
        }

        @Override
        public int killType() { // on contact = 2
            return 2;
        }
    }

    public static class Jalapeno extends Plant implements Attacker, InstantKiller{
        public Jalapeno(){
            super("Jalapeno", INFINITY, 125);
        }

        @Override
        public int attack() {
            System.out.println(name + " dies while exploding");
            return 5;
        }

        @Override
        public int rangeType() { // single line = 1
            return 1;
        }

        @Override
        public int killType() { // instant = 1
            return 1;
        }
    }

    public static class CoffeeBean extends Plant {
        public CoffeeBean(){
            super("Coffee Bean", INFINITY, 75);
        }
    }

    public static class LilyPad extends Plant implements Upgradable{
        public LilyPad(){
            super("Lily Pad", 25);
        }

        @Override
        public Plant upgrade() {
            return new Cattail();
        }
    }

    public static class Cattail extends Plant implements Attacker, PlantUpgrade{
        public Cattail(){
            super("Cattail", 225);
        }

        @Override
        public int attack() {
            System.out.println(name + " attacks");;
            return 1;
        }

        @Override
        public int rangeType() { // free range = 4
            return 4;
        }

        @Override
        public int concurrentSunCost() {
            return sun_cost+25;
        }
    }
}
