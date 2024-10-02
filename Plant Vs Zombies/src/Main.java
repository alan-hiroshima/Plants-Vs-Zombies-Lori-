import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
    List<Plant> plants = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    System.out.println("Game mode:");
    String mode = sc.nextLine();

    String input;
    do{
        System.out.print("Add a plant: ");
        input = sc.nextLine();
        switch (input) {
            case "DONE":
                break;
            case "Sunflower":
                plants.add(new Plant.Sunflower());
                break;
            case "Jalapeno":
                plants.add(new Plant.Jalapeno());
                break;
            case "Wall Nut":
                plants.add(new Plant.WallNut());
                break;
            case "Sun-shroom":
                plants.add(new Mushroom.SunShroom(false));
                break;
            case "Doom-shroom":
                plants.add(new Mushroom.DoomShroom(false));
                break;
            case "Puff-shroom":
                plants.add(new Mushroom.PuffShroom(false));
                break;
            case "Peashooter":
                plants.add(new Plant.Peashooter());
                break;
            case "Lily Pad":
                plants.add(new Plant.LilyPad()); // Add Lily Pad here
                break;
            case "Squash":
                plants.add(new Plant.Squash()); // Add Squash here
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
                int sum = 0, count = 0;
                for (Plant p : plants) {
                    if (p instanceof SunProducer) {
                        count++;
                        sum += ((SunProducer)p).produce_sun();
                    }

                }
                if (count == 0){
                    System.out.println("You have no sun producers");
                } else {
                    System.out.println(+ count + " sun producers gather " + sum + " suns");
                }
                break;
            case "Attack":
                int attackers = 0;
                int totalDamage = 0;

                for (Plant plant : plants) {


                    // After the loop, print total attackers and damage dealt
                    System.out.println(attackers + " attackers dealing " + totalDamage + " damage");
                    break;

                }
            case "Attacker Status":
                for (Plant plant : plants) {
                    if (plant instanceof Attacker) {
                        int rangeType = ((Attacker)plant).rangeType();
                        switch (rangeType){
                            case 1:
                                System.out.println(plant.name + "  can attack on a single line");
                                break;
                            case 2:
                                System.out.println(plant.name + " can attack using area-of-effect");
                                break;
                            case 3:
                                System.out.println(plant.name + " can attack only when the enemy is nearby");
                                break;
                            case 4:
                                System.out.println(plant.name + " can attack anywhere");
                                break;
                        }
                    }
                }
                break;

            case "Instant Kill Status":
                for (Plant plant : plants) {
                    if (plant instanceof InstantKiller) {
                        int killType = ((InstantKiller)plant).killType();
                        switch (killType){
                            case 1:
                                System.out.println(plant.name + " can kill instantly");
                                break;
                            case 2:
                                System.out.println(plant.name + " can kill in close contact");
                                break;

                        }
                    }
                }
                break;
            case "Sort by Name":
                Collections.sort(plants);
                for (Plant p : plants){
                    System.out.println(p);
                }
                break;
            case "Sort by HP":
                plants.sort(new Plant.sortByHp());
                for (Plant p : plants) {
                    System.out.println(p);
                }
                break;

            case "Sort by Sun Cost":
                plants.sort(new Plant.sortBySunCost());
                for (Plant p : plants) {
                    System.out.println(p);
                }
                break;
            default:
                System.out.println("Unknown action: " + input);
                break;
        }
        } while (!input.equals("DONE"));
    }
}
