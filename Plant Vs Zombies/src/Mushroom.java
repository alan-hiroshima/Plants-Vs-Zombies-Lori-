public class Mushroom extends Plant {
    boolean state;

    public Mushroom(String name, int sun_cost, boolean state) {
        super(name, sun_cost);
        this.state = state;
    }

    public boolean isAwake(){
        return true;
    }

    public void awaken(CoffeeBean coffeebean){

    }

    public static class SunShroom extends Mushroom implements SunProducer{
        public SunShroom(boolean state) {
            super("Sun-shroom", 25, state);
        }

        @Override
        public int produce_sun() {
            return 10;
        }
    }

    public static class PuffShroom extends Mushroom implements Attacker{
        public PuffShroom(boolean state){
            super("Puff-shroom", 0, state);
        }


        @Override
        public int attack() {
            if (!isAwake()){
                System.out.println(name + " is asleep and cannot attack");
            }
            System.out.println(name + " attacks");
            return 1;
        }

        @Override
        public int rangeType() { // limited range = 3
            return 3;
        }
    }

    public static class DoomShroom extends Mushroom implements Attacker, InstantKiller{
        public DoomShroom(boolean state){
            super("Doom-shroom", 125, state);
        }

        @Override
        public int killType() { // instant = 1
            return 1;
        }

        @Override
        public int attack() {
            if (!isAwake()){
                System.out.println(name + " is asleep and cannot attack");
            }
            System.out.println(name + " dies while exploding and leaves a crater");
            return 10;
        }

        @Override
        public int rangeType() { // AOE = 2
            return 2;
        }
    }
}
