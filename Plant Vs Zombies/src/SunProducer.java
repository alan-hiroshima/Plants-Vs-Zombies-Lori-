public interface SunProducer {
    int produce_sun();
}

interface PlantUpgrade{
    int concurrentSunCost();
}

interface  Attacker{
    void attack();
    int rangeType();
}

interface InstantKiller{
    int killType();
}

interface  Upgradable{
    Plant upgrade();
}
