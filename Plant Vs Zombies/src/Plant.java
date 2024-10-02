import java.util.Comparator;

public abstract class Plant implements Comparable<Plant>, Comparator<Plant> {,
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
}
