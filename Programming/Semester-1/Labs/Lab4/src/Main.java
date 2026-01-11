import characters.*;
import animals.*;
import magical.*;
import model.Position;
import enums.LocationType;
import world.World;
import utils.Instrument;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Начинается симуляция сказочного мира ===\n");
        
        // Создаем мир
        World world = new World();
        
        // Создаем позиции
        Position fieldPosition = new Position(10, 20, LocationType.FIELD);
        Position cityPosition = new Position(100, 50, LocationType.CITY);
        Position mountainPosition = new Position(200, 300, LocationType.MOUNTAIN);
        
        // Добавляем персонажей из Цветочного города
        world.addCharacter(new Neznaika(cityPosition));
        world.addCharacter(new Znayka(cityPosition));
        world.addCharacter(new DoctorPilulkin(cityPosition));
        world.addCharacter(new Tyubik(cityPosition));
        world.addCharacter(new Guslya(cityPosition));
        world.addCharacter(new Ponchik(cityPosition));
        world.addCharacter(new Pulka(cityPosition));
        
        // Добавляем сказочных персонажей
        world.addCharacter(new IvanTsarevich(fieldPosition));
        world.addCharacter(new Princess("Царевна Елена Прекрасная", mountainPosition));
        world.addCharacter(new King("Царь Василий", cityPosition));
        
        // Добавляем животных
        world.addAnimal(new Falcon(fieldPosition));
        
        Horse fallenHorse = new Horse(fieldPosition, 33);
        fallenHorse.fall(); // Лошадь лежит 33 года
        world.addAnimal(fallenHorse);
        
        world.addAnimal(new Goat(fieldPosition));
        world.addAnimal(new MultiHeadedDragon(12, mountainPosition));
        
        // Создаем вложенные волшебные объекты (Сундук -> Заяц -> Утка -> Яйцо -> Семечко)
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
        
        // Добавляем хрустальную гору
        CrystalMountain mountain = new CrystalMountain(mountainPosition);
        
        // Запускаем симуляцию
        world.run();
        
        // Демонстрируем взаимодействия
        System.out.println("\n=== Особые взаимодействия ===");
        
        // Получаем Ивана-царевича - используем полное имя characters.Character
        IvanTsarevich ivan = null;
        for (characters.Character character : world.getCharacters()) {
            if (character instanceof IvanTsarevich) {
                ivan = (IvanTsarevich) character;
                break;
            }
        }
        
        if (ivan != null) {
            // Иван разделяет лошадь
            System.out.println("\nИван-царевич находит павшую лошадь:");
            try {
                var divisionResult = fallenHorse.divideHorse();
                System.out.println("Результат раздела: " + divisionResult);
                
                // Сокол дарует силу превращения
                System.out.println("\nСокол благодарен:");
                ivan.transformToFalcon();
                System.out.println("Иван теперь: " + ivan.getCurrentForm());
                ivan.revertTransformation();
                
                // Иван сражается со змеем
                System.out.println("\nИван сражается со змеем:");
                ivan.fightDragon(12);
                
                // Находим семечко и плавим гору
                System.out.println("\nИщем волшебное семечко:");
                chest.interact(ivan);
                seed.burn();
                seed.meltCrystalMountain(mountain);
                
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        
        // Демонстрируем деятельность жителей Цветочного города
        System.out.println("\n=== Занятия жителей Цветочного города ===");
        
        // Тюбик рисует портрет
        Tyubik tyubik = null;
        for (characters.Character character : world.getCharacters()) {
            if (character instanceof Tyubik) {
                tyubik = (Tyubik) character;
                break;
            }
        }
        
        if (tyubik != null) {
            Neznaika neznaika = null;
            for (characters.Character character : world.getCharacters()) {
                if (character instanceof Neznaika) {
                    neznaika = (Neznaika) character;
                    break;
                }
            }
            
            if (neznaika != null) {
                tyubik.drawPortrait(neznaika);
            }
        }
        
        // Гусля играет музыку
        Guslya guslya = null;
        for (characters.Character character : world.getCharacters()) {
            if (character instanceof Guslya) {
                guslya = (Guslya) character;
                break;
            }
        }
        
        if (guslya != null) {
            Instrument trumpet = new Instrument("Труба");
            guslya.playMusic(trumpet);
        }
        
        // Показываем историю логов
        System.out.println("\n");
        world.getLogger().printHistory();
        
        System.out.println("\n=== Симуляция завершена ===");
    }
}