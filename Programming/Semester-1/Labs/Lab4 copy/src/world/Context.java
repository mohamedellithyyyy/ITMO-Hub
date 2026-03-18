package world;

import java.util.ArrayList;
import java.util.Random;

public class Context {
    private ArrayList<Location> locations;
    private Random random;
    
    public Context() {
        this.locations = new ArrayList<>();
        this.random = new Random();
    }
    
    public void addLocation(Location location) {
        locations.add(location);
    }
    
    public ArrayList<Location> getLocations() {
        return locations;
    }
    
    public void getRandomEvent() {
        String[] events = {
            "Нежный ветерок дует по полю",
            "Вдали поют птицы",
            "Солнце ярко светит",
            "На небе собираются тучи",
            "Появляется таинственный туман"
        };
        
        int index = random.nextInt(events.length);
        System.out.println("Событие: " + events[index]);
    }
    
    public Random getRandom() {
        return random;
    }
}