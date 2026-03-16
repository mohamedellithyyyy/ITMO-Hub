import characters.*;
import animals.*;
import magical.*;
import model.Position;
import enums.LocationType;
import world.World;
import utils.Instrument;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // The simulation of a fairy-tale world begins
        System.out.println("=== Начинается симуляция сказочного мира ===\n"); 

        World world = new World();

        Position fieldPosition = new Position(10, 20, LocationType.FIELD);
        Position cityPosition = new Position(100, 50, LocationType.CITY);
        Position mountainPosition = new Position(200, 300, LocationType.MOUNTAIN);

        // Add characters
        world.addCharacter(new Neznaika(cityPosition));
        world.addCharacter(new Znayka(cityPosition));
        world.addCharacter(new DoctorPilulkin(cityPosition));
        world.addCharacter(new Tyubik(cityPosition));
        world.addCharacter(new Guslya(cityPosition));
        world.addCharacter(new Ponchik(cityPosition));
        world.addCharacter(new Pulka(cityPosition));

        IvanTsarevich ivan = new IvanTsarevich(fieldPosition);
        Princess princess = new Princess("Царевна Елена Прекрасная", mountainPosition);
        King king = new King("Царь Василий", cityPosition);

        world.addCharacter(ivan);
        world.addCharacter(princess);
        world.addCharacter(king);

        // Add animals
        Falcon falcon = new Falcon(fieldPosition);
        world.addAnimal(falcon);

        Horse fallenHorse = new Horse(fieldPosition, 33);
        fallenHorse.fall(); // Horse is fallen
        world.addAnimal(fallenHorse);

        Goat goat = new Goat(fieldPosition);
        MultiHeadedDragon dragon = new MultiHeadedDragon(12, mountainPosition);
        world.addAnimal(goat);
        world.addAnimal(dragon);

        // Add magical objects
        Seed seed = new Seed(mountainPosition);
        Egg egg = new Egg(mountainPosition, seed);
        Duck duck = new Duck(mountainPosition, egg);
        Hare hare = new Hare(mountainPosition, duck);
        Chest chest = new Chest(mountainPosition, hare);

        world.addMagicalObject(chest);
        world.addMagicalObject(hare);
        world.addMagicalObject(duck);
        world.addMagicalObject(egg);
        world.addMagicalObject(seed);

        CrystalMountain mountain = new CrystalMountain(mountainPosition);

        // CLI setup
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Добро пожаловать в интерактивный мир сказок ==="); // Welcome to the interactive world of fairy tales
        System.out.println("Введите 'help', чтобы увидеть команды."); // Type 'help' to see the commands.

        while (running) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toLowerCase();
            String[] parts = input.split(" ");

            switch (parts[0]) {
                case "help":
                    System.out.println("Доступные команды:"); // Display "Available commands:"
                    System.out.println("act - выполнять действие всех персонажей"); // "act" - make all characters perform their action
                    System.out.println("divide - разделить павшую лошадь"); // "divide" - split the fallen horse
                    System.out.println("transform falcon - превратить Ивана в сокола"); // "transform falcon" - turn Ivan into a falcon
                    System.out.println("transform ant - превратить Ивана в муравья"); // "transform ant" - turn Ivan into an ant
                    System.out.println("revert - вернуть Ивана в человека"); // "revert" - turn Ivan back into a human
                    System.out.println("interact chest - открыть сундук"); // "interact chest" - open the chest
                    System.out.println("burn seed - сжечь семечко"); // "burn seed" - burn the seed
                    System.out.println("melt mountain - растопить хрустальную гору"); // "melt mountain" - melt the crystal mountain
                    System.out.println("fight dragon - сражаться со змеем"); // "fight dragon" - fight the dragon
                    System.out.println("exit - выйти из игры"); // "exit" - exit the game
                    break;

                case "act":
                    for (characters.Character character : world.getCharacters()) {
                        character.act(world.getContext());
                    }
                    break;

                case "divide":
                    try {
                        System.out.println(fallenHorse.divideHorse());
                        falcon.speakToCharacter(ivan);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;

                case "transform":
                    if (parts.length > 1) {
                        if (parts[1].equals("falcon")) ivan.transformToFalcon();
                        else if (parts[1].equals("ant")) ivan.transformToAnt();
                        else System.out.println("Неизвестная форма.");
                        System.out.println("Текущая форма Ивана: " + ivan.getCurrentForm());
                    }
                    break;

                case "revert":
                    ivan.revertTransformation();
                    break;

                case "interact":
                    if (parts.length > 1 && parts[1].equals("chest")) {
                        chest.interact(ivan);
                    }
                    break;

                case "burn":
                    if (parts.length > 1 && parts[1].equals("seed")) {
                        seed.burn();
                    }
                    break;

                case "melt":
                    if (parts.length > 1 && parts[1].equals("mountain")) {
                        seed.meltCrystalMountain(mountain);
                    }
                    break;

                case "fight":
                    if (parts.length > 1 && parts[1].equals("dragon")) {
                        ivan.fightDragon(dragon.getHeads());
                        dragon.fightWithHero(ivan);
                    }
                    break;

                case "exit":
                    running = false;
                    System.out.println("Выход из игры...");
                    break;

                default:
                    System.out.println("Неизвестная команда. Введите 'help'."); 
            }
        }

        scanner.close();
        // System.out.println("\n=== История логов ===");
        world.getLogger().printHistory();
        System.out.println("\n=== Симуляция завершена ===");
    }
}
// Урааааааааааааааааааааааааа