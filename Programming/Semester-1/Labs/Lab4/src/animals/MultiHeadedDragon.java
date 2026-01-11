package animals;

import interfaces.Aggressor;
import entity.Animal;
import model.Position;
import world.Context;
import java.util.Random;

public class MultiHeadedDragon extends Animal implements Aggressor {
    private int heads;
    private int cowsDemanded = 0;
    
    public MultiHeadedDragon(int heads, Position position) {
        super("Змей", position);
        this.heads = heads;
    }
    
    @Override
    public void act(Context ctx) {
        System.out.println("Змей о " + heads + " головах стережет озеро");
    }
    
    @Override
    public void attack(entity.Entity target) {
        System.out.println("Змей с " + heads + " головами атакует " + 
                          target.getClass().getSimpleName());
    }
    
    public int getHeads() {
        return heads;
    }
    
    public void setHeads(int heads) {
        this.heads = heads;
    }
    
    public boolean demandCows(int count) {
        if (count <= 0) {
            return false;
        }
        cowsDemanded = count;
        System.out.println("Змей требует " + count + " коров!");
        return true;
    }
    
    public int getCowsDemanded() {
        return cowsDemanded;
    }
    
    public boolean fightWithHero(characters.Character hero) {
        Random rand = new Random();
        boolean heroWins = rand.nextBoolean();
        
        if (heroWins) {
            System.out.println(hero.getName() + " побеждает " + heads + "-главого змея!");
            heads = Math.max(0, heads - 1); // Теряет одну голову
            return true;
        } else {
            System.out.println("Змей выдерживает атаку!");
            return false;
        }
    }
}