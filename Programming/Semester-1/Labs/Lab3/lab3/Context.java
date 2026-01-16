package world;

import entity.Character;
import entity.Entity;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class Context {

    private List<Entity> entities;
    private final Random random = new Random();

    // Set all entities in the world
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    // Get a random Character from the world
    public Optional<Character> getRandomCharacter() {
        if (entities == null || entities.isEmpty()) return Optional.empty();

        List<Character> characters = entities.stream()
                .filter(e -> e instanceof Character)
                .map(e -> (Character) e)
                .collect(Collectors.toList());

        if (characters.isEmpty()) return Optional.empty();

        return Optional.of(characters.get(random.nextInt(characters.size())));
    }
}
