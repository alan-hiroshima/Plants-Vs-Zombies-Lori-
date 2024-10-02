public class Mushroom extends Plant {
    boolean state;

    public Mushroom(String name, int sun_cost, boolean state) {
        super(name, sun_cost);
        this.state = state;
    }

    public boolean isAwake(){
        return true;
    }

    public void awaken(Coffeebean coffeebean){

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
        public void attack() {

        }

        @Override
        public int rangeType() {
            return 0;
        }
    }

    public static class DoomShroom extends Mushroom implements Attacker, InstantKiller{
        public DoomShroom(boolean state){
            super("Doom-shroom", 125, state);
        }

        @Override
        public int killType() {
            return 0;
        }

        @Override
        public void attack() {

        }

        @Override
        public int rangeType() {
            return 0;
        }
    }
}
