package interfaces;

import characters.Character;

public interface Painter {
    void drawPortrait(Character target);
    
    default void cleanBrush() {
        System.out.println("Чищу кисть...");
    }
    
    static void sharpenBrush() {
        System.out.println("Точию кисть...");
    }
    
    // Приватный метод (Java 9+)
    private void preparePaint() {
        System.out.println("Готовлю краски...");
    }
}