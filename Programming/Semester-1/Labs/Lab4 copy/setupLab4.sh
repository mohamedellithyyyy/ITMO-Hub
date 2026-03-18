#!/bin/bash
# setup_lab4.sh
# Script to set up Lab4 Java project structure and stub files

echo "Creating Lab4 project structure..."

# Base folder
mkdir -p src

# Packages
mkdir -p src/characters
mkdir -p src/entity
mkdir -p src/enums
mkdir -p src/exceptions
mkdir -p src/animals
mkdir -p src/magical
mkdir -p src/interfaces
mkdir -p src/model
mkdir -p src/utils
mkdir -p src/world

# Main.java
cat <<EOL > src/Main.java
public class Main {
    public static void main(String[] args) {
        System.out.println("Lab4 project initialized.");
    }
}
EOL

# Characters
for c in Character Neznaika Znayka DoctorPilulkin Tyubik Guslya Ponchik Pulka IvanTsarevich Princess King; do
    touch src/characters/$c.java
done

# Entity
for e in Entity Animal MagicalObject; do
    touch src/entity/$e.java
done

# Enums
for en in Direction Gender LocationType State Color; do
    touch src/enums/$en.java
done

# Exceptions
for ex in CollisionException InvalidActionException RuntimeCollisionException; do
    touch src/exceptions/$ex.java
done

# Animals
for a in Falcon Horse Goat MultiHeadedDragon; do
    touch src/animals/$a.java
done

# Magical objects
for m in Chest Hare Duck Egg Seed CrystalMountain; do
    touch src/magical/$m.java
done

# Interfaces
for i in Aggressor Attackable Dividable Movable Musician Painter Transformable; do
    touch src/interfaces/$i.java
done

# Model
for md in DivisionResult Position; do
    touch src/model/$md.java
done

# Utils
touch src/utils/Instrument.java

# World
for w in Context Logger World; do
    touch src/world/$w.java
done

echo "Lab4 project structure created successfully."
