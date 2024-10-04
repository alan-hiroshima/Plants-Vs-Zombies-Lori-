import java.util.Comparator;

public abstract class Plant implements Comparable<Plant>{
    
    public static final int INFINITE = Integer.MAX_VALUE;
    String name;
    int hp;
    int sun_cost;

    public Plant(String name, int sun_cost) {
        this.name = name;
        this.hp = 6;
        this.sun_cost = sun_cost;
    }

    public Plant(String name, int hp, int sun_cost) {
        this.name = name;
        this.hp = hp;
        this.sun_cost = sun_cost;
    }

    public boolean isAlive() {
        // TODO implementation
        // this will return true if hp > 0
        return hp > 0;
    }

    public String die() {
        // TODO implementation
        hp = 0;
        return name + " dies";
        // later this will be overriden by Instantkiller if " while exploding"
    }

    @Override
    public String toString() {
        // TODO implementation
        return name + (hp == INFINITE ? " (∞)" : " (" + hp + ")") + " - cost: " + sun_cost; 
    }
    
    
    // COMPARABLE AND COMPARATOR
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
    
    

    // Add Plant subclasses here, and
    // Hint: You can also add Comparator inner classes here
    // WallNut and CoffeeBean given for free
    public static class WallNut extends Plant{
        public WallNut() {
            super("Wall Nut", 25, 50);
        }
    }

    public static class CoffeeBean extends Plant{
        public CoffeeBean() {
            super("Coffee Bean", INFINITE, 75);
        }
    }
    
    public static class Sunflower extends Plant implements SunProducer, Upgradable{
        // is a SunProducer that produces 25 sun
        // is a Upgradable that will upgrade into a TwinSunflower, that will costs 50 suns.
        public Sunflower(){
            super("Sunflower", 50);
        }

        @Override
        public int produce_sun(){
            System.out.println(name + " produces 25 suns");
            return 25;
        }

        @Override
        public PlantUpgrade upgrade(){
            return new TwinSunflower();
        }

    }
    
    public static class TwinSunflower extends Plant implements PlantUpgrade, SunProducer{
        // is a SunProducer that produces 50 suns
        // and is also a PlantUpgrade, this plant initially costs 250 suns and has concurrent sun cost of 50 suns
        public TwinSunflower(){
            super("Twin Sunflower", 250);
       }

       @Override
        public int produce_sun(){
           System.out.print(name + " produces 50 suns\n");
           return 50;
       }

       @Override
        public int concurrentSunCost(){
           return 50;
       }
    }
    
    public static class Peashooter extends Plant implements Attacker{
        public Peashooter(){
            super("Peashooter", 100);
        }
        @Override
        public int attack(){
            System.out.println(name + " attacks");
            return 1;
        }
        
        public int rangeType(){
            return 1; // range: single line
        }
    }
    
    public static class Squash extends Plant implements InstantKiller, Attacker{
        // is an InstantKiller that could kill on contact and
        // is an Attacker that deals 3 damage and dies " while squashing zombies" and has a limited range. Plant costs 50 suns
        public Squash(){
            super("Squash", INFINITE, 50);
        }

        @Override
        public int killType(){
            // System.out.println( " instantly kills zombies on contact\n");
            return 2; // on contact
        }
        @Override
        public int attack() {
            System.out.println(name + " attacks");
            System.out.println(die());
            return 3;
        }
        @Override
        public int rangeType() {
            return 3;  // limited range
        }
        @Override
        public String die(){
            return super.die() + " while squashing zombies";
        }
    }
    
    public static class Jalapeno extends Plant implements InstantKiller, Attacker {
        // is an InstantKiller that could kill instantly and
        // is an Attacker that deals with 5 damage and dies " while exploding" and has a range of a single line
        public Jalapeno(){
            super("Jalapeno", INFINITE,125);
        }

        @Override
        public int attack(){
            System.out.println(name + " attacks");
            System.out.println(die());
            return 5;
        }
        @Override
        public int rangeType(){
            return 1; // single line
        }
        @Override
        public int killType(){
            return 1; // instant kill
        }
        @Override
        public String die(){
            return super.die() + " while exploding";
        }
    }
    
    public static class LilyPad extends Plant implements Upgradable{
        // is an Upgradable that will upgrade into a new Cattail.
        public LilyPad(){
            super("Lily Pad", 25);
        }

        @Override
        public PlantUpgrade upgrade(){
            return new Cattail();
        }
    }
    
    public static class Cattail extends Plant implements Attacker, PlantUpgrade{
        // is an Attacker that deals 1 damage and has a free range.
        // and also an PlantUpgrade, this plant initially has 225 suns and has a concurrent sun cost of 25 suns.
        public Cattail(){
            super("Cattail", 225);
        }
        @Override
        public int attack(){
            System.out.println(name + " attacks");
            return 1;
        }
        @Override
        public int rangeType(){
            return 4; // range: free range
        }
        @Override
        public int concurrentSunCost(){
            return 25;
        }
    }
    
}
