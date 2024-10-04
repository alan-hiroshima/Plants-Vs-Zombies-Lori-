import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Plant> plants = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Game Mode: ");
        String mode = sc.nextLine();
        
        boolean isState = mode.equals("Night") || mode.equals("Fog");
        
        String input;
        do {
            System.out.print("Add a plant: ");
            input = sc.nextLine();
            switch (input) {
                case "DONE":
                    break;
                case "Wall Nut":
                    plants.add(new Plant.WallNut());
                    break;
                case "Peashooter":
                    plants.add(new Plant.Peashooter());
                    break;
                case "Sunflower":
                    plants.add(new Plant.Sunflower());
                    break;
                case "Twin Sunflower":
                    for (int i = 0; i < plants.size(); i++) {
                        Plant p = plants.get(i);
                        if (p instanceof Plant.Sunflower) {
                            PlantUpgrade upgradedPlant = ((Upgradable) p).upgrade();
                            plants.remove(i);
                            plants.add(i, (Plant) upgradedPlant);
                            break; // Only upgrade the first matching plant
                        }
                    }
                    break;
                case "Squash":
                    plants.add(new Plant.Squash());
                    break;
                case "Lily Pad":
                    plants.add(new Plant.LilyPad());
                    break;
                case "Cattail":
                    for (int i = 0; i < plants.size(); i++) {
                        Plant p = plants.get(i);
                        if (p instanceof Plant.LilyPad) {
                            PlantUpgrade upgradedPlant = ((Upgradable) p).upgrade();
                            plants.remove(i);
                            plants.add(i, (Plant) upgradedPlant);
                            break; // Only upgrade the first matching plant
                        }
                    }
                    break;
                case "Jalapeno":
                    plants.add(new Plant.Jalapeno());
                    break;
                case "Puff-shroom":
                    plants.add(new Mushroom.PuffShroom(isState));
                    break;
                case "Doom-shroom":
                    plants.add(new Mushroom.DoomShroom(isState));
                    break;
                case "Sun-shroom":
                    plants.add(new Mushroom.SunShroom(isState));
                    break;
                case "Coffee Bean":
                    Plant.CoffeeBean coffeeBean = new Plant.CoffeeBean();
                    
                    for (Plant p : plants) {
                        if (p instanceof Mushroom && !((Mushroom) p).isAwake()) {
                            ((Mushroom) p).awaken(coffeeBean);
                            break; // Only awaken the first sleeping mushroom
                        }
                    }
                    
                    break;
                default:
                    System.out.println(input + " is not a plant");
            }
        } while (!input.equals("DONE"));

        do {
            System.out.print("Do something: ");
            input = sc.nextLine();
            switch (input) {
                case "DONE":
                    break;
                case "Produce Sun":
                    // add implementation here
                    int count = 0;
                    int sum = 0;
                    for(Plant p: plants){
                        if(p instanceof SunProducer){
                            sum += ((SunProducer)p).produce_sun();
                            count++;
                        }
                    }
                     System.out.println(count + " sun producers gather " + sum +" suns");
                    break;
                case "Attack":
                    // add implementation here
                    int attackerCount = 0;
                    int totalDamage = 0;
                    
                    // Iterate through the plants and process the attackers
                    for (Plant p : plants) {
                        if (p instanceof Attacker && p.isAlive()) {
                                // Attack and accumulate damage
                                int damage = ((Attacker) p).attack();
                                totalDamage += damage;
                                attackerCount++;
                        }
                    }
                    
                    // Output results based on attackers
                    if (attackerCount == 0) {
                        System.out.println("You have no attackers.");
                    } else {
                        System.out.println(attackerCount + " attackers dealing " + totalDamage + " damage");
                    }
                    break;
                // add more cases here
                case "Instant Kill Status":
                    // add implementation here
                    int n =0;
                    for (Plant p : plants) {
                        if (p instanceof InstantKiller && p.isAlive()) { // Check if the plant is alive
                            n++;
                            if (((InstantKiller) p).killType() == 1) {
                                System.out.println(p.name + " can kill instantly");
                            } else if (((InstantKiller) p).killType() == 2) {
                                System.out.println(p.name + " can kill on contact");
                            }
                        }
                        
                    }
                    
                    if(n == 0){
                      System.out.println("You have no plants which can kill instantly");
                    }
                    

                    break;
                case "Attacker Status":
                    // add implementation here
                    int countAttacker = 0;
                    for (Plant p : plants) {
                        if (p instanceof Attacker && p.isAlive()) {
                            countAttacker++;

                            switch (((Attacker) p).rangeType()) {
                                case 1:
                                    System.out.println(p.name + " can attack on a single line");
                                    break;
                                case 2:
                                    System.out.println(p.name + " can attack using area-of-effect");
                                    break;
                                case 3:
                                    System.out.println(p.name + " can attack only when enemy is nearby");
                                    break;
                                case 4:
                                    System.out.println(p.name + " can attack any enemies from anywhere");
                                    break;
                            }
                        }
                    }

                    if(countAttacker == 0){
                        System.out.println("You have no attackers");
                    }
                    break;
                case "Sort by HP":
                    // add implementation here
                    plants.sort(new Plant.sortByHp());
                    for (Plant p : plants) {
                        System.out.println(p);
                    }
                    break;
                case "Sort by Name":
                    // add implementation here
                    Collections.sort(plants);
                    for (Plant p : plants){
                        System.out.println(p);
                    }
                    break;
                case "Sort by Sun Cost":
                    // add implementation here
                   plants.sort(new Plant.sortBySunCost());
                    for (Plant p : plants) {
                        System.out.println(p);
                    }
                    break;
                default:
                    System.out.println("Unknown action: " + input);
            }
        } while (!input.equals("DONE"));
    }
}
