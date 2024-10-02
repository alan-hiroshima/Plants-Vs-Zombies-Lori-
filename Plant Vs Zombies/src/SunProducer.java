public interface SunProducer {
    int produce_sun();
    // return no of suns it produce
}

interface PlantUpgrade{
    int concurrentSunCost();
    // return the sum of og sum and the concurrent sun
}

interface  Attacker{
    int attack();
        // prints the attack action "name" + attacks, returns damage
    int rangeType();
    /*
     * 1 - Single Line
     * 2 - AOE
     * 3 - Limited Range
     * 4 - Free Range
     */

}

interface InstantKiller{
    int killType();
    /*
      1 = Instant
      2 = Close Contact
     */

}

interface  Upgradable{
    Plant upgrade();
}
