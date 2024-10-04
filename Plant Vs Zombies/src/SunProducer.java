public interface SunProducer {
    public int produce_sun();
    // return no of suns it produce
}

interface PlantUpgrade{
   public int concurrentSunCost();
    // return the sum of og sum and the concurrent sun
}

interface  Attacker{
    public int attack();
        // prints the attack action "name" + attacks, returns damage
    public int rangeType();
    /*
     * 1 - Single Line
     * 2 - AOE
     * 3 - Limited Range
     * 4 - Free Range
     */

}

interface InstantKiller{
    public int killType();
    /*
      1 = Instant
      2 = Close Contact
     */

}

interface Upgradable{
    public PlantUpgrade upgrade();
}
