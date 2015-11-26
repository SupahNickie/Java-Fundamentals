import java.util.Scanner;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RussianRoulette {

  class Gun {
    String caliber;
    int rounds;
    int capacity;
    int deadlyChamber;

    Gun(String caliber, int rounds) {
      this.caliber = caliber;
      this.rounds = rounds;
      this.capacity = 6;

      Random rand = new Random();
      int answer = rand.nextInt((this.capacity - this.rounds) + 1);
      this.deadlyChamber = answer;
    }

    public void pullTrigger(Player plyr) {
      this.deadlyChamber--;
      if (this.deadlyChamber <= 0) {
        System.out.println("BOOM, " +  plyr.name + " is dead at age " + plyr.age + ".");
        plyr.status = "Dead";
      } else {
        System.out.println("CLIIIIIIIICK!!");
      }
    }
  }

  class Player {
    String name;
    int age;
    String status;

    Player(String name, int age) {
      this.name = name;
      this.age = age;
      this.status = "Alive";
    }
  }

  public static void play(Gun gun, Player player) {
    Scanner userInput = new Scanner(System.in);
    Pattern ptn = Pattern.compile("(?i)yes");
    while (player.status == "Alive") {
      System.out.print("Pull trigger? ");
      String input = userInput.next();
      Matcher m = ptn.matcher(input);
      if (m.matches()) {
        gun.pullTrigger(player);
      } else {
        System.out.println("Pussy.");
        break;
      }
    }
  }

  public static void main(String[] args) {
    Scanner userInput = new Scanner(System.in);
    System.out.print("What caliber weaponry are we talking here? ");
    String caliber = userInput.next();
    System.out.print("How many rounds are we loading? ");
    int rounds = Integer.parseInt(userInput.next());

    String _ = userInput.nextLine();
    System.out.print("What is the player's name and age? ");
    String rawValue = userInput.nextLine();
    String name = rawValue.split(" ")[0];
    int age = Integer.parseInt(rawValue.split(" ")[1]);
    System.out.println("Cool, thanks.");
    RussianRoulette mp = new RussianRoulette();
    RussianRoulette.Gun gun = mp.new Gun(caliber, rounds);
    RussianRoulette.Player plyr = mp.new Player(name, age);
    RussianRoulette.play(gun, plyr);
  }
}
