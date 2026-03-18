package world;

import characters.Character;
import entity.Animal;
import entity.MagicalObject;
import java.util.ArrayList;

public class World {
    private ArrayList<Character> characters;
    private ArrayList<Animal> animals;
    private ArrayList<MagicalObject> magicalObjects;
    private Context context;
    private Logger logger;
    
    public World() {
        this.characters = new ArrayList<>();
        this.animals = new ArrayList<>();
        this.magicalObjects = new ArrayList<>();
        this.context = new Context();
        this.logger = new Logger();
    }
    
    public void addCharacter(Character character) {
        characters.add(character);
        logger.log("Добавлен персонаж: " + character.getName());
    }
    
    public void addAnimal(Animal animal) {
        animals.add(animal);
        logger.log("Добавлено животное: " + animal.getSpecies());
    }
    
    public void addMagicalObject(MagicalObject magicalObject) {
        magicalObjects.add(magicalObject);
        logger.log("Добавлен волшебный объект: " + magicalObject.getName());
    }
    
    public void run() {
        logger.log("=== Начинается мир сказок ===");
        
        // Симулируем действия персонажей
        for (Character character : characters) {
            character.act(context);
        }
        
        // Симулируем действия животных
        for (Animal animal : animals) {
            animal.act(context);
        }
        
        // Генерируем случайное событие
        context.getRandomEvent();
        
        logger.log("=== Симуляция мира завершена ===");
    }
    
    public ArrayList<Character> getCharacters() {
        return new ArrayList<>(characters);
    }
    
    public ArrayList<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }
    
    public ArrayList<MagicalObject> getMagicalObjects() {
        return new ArrayList<>(magicalObjects);
    }
    
    public Context getContext() {
        return context;
    }
    
    public Logger getLogger() {
        return logger;
    }
}