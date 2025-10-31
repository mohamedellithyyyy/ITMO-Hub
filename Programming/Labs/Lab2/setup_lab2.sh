#!/bin/bash

# Project root
PROJECT="Lab2"

# Directories to create
DIRS=(
    "$PROJECT/app/src/attacks/physical"
    "$PROJECT/app/src/attacks/special"
    "$PROJECT/app/src/attacks/status"
    "$PROJECT/app/src/pokemons"
    "$PROJECT/app/bin"
    "$PROJECT/app/vendor"
    "$PROJECT/build"
    "$PROJECT/docs/diagrams"
    "$PROJECT/docs/imges"
)

# Create directories
echo "Creating directories..."
for dir in "${DIRS[@]}"; do
    mkdir -p "$dir"
done

# Files to create
FILES=(
    "$PROJECT/LICENSE"
    "$PROJECT/README.md"
    "$PROJECT/app/src/Main.java"
    "$PROJECT/app/vendor/Pokemon.jar"
    "$PROJECT/docs/Лабораторная работа №2-Report.docx"
    "$PROJECT/pokemon_attacks.txt"
)

echo "Creating files..."
for file in "${FILES[@]}"; do
    touch "$file"
done

# Attack and Pokémon class names
ATTACKS_PHYSICAL=("BodySlam" "CrushClaw" "Facade" "Nuzzle" "StoneEdge")
ATTACKS_SPECIAL=("ChargeBeam" "DreamEater" "FireBlast" "Psychic")
ATTACKS_STATUS=("DoubleTeam" "ThunderWave")
POKEMONS=("Igglybuff" "Jigglypuff" "Pachirisu" "Sandshrew" "Sandslash" "Wigglytuff")

# Function to create a Java file with package declaration
create_java_file() {
    local path="$1"
    local package="$2"
    local class="$3"
    echo "package $package;

public class $class {
}
" > "$path"
}

# Create attack classes
echo "Creating attack class files..."
for atk in "${ATTACKS_PHYSICAL[@]}"; do
    create_java_file "$PROJECT/app/src/attacks/physical/$atk.java" "attacks.physical" "$atk"
done
for atk in "${ATTACKS_SPECIAL[@]}"; do
    create_java_file "$PROJECT/app/src/attacks/special/$atk.java" "attacks.special" "$atk"
done
for atk in "${ATTACKS_STATUS[@]}"; do
    create_java_file "$PROJECT/app/src/attacks/status/$atk.java" "attacks.status" "$atk"
done

# Create Pokémon classes
echo "Creating Pokémon class files..."
for poke in "${POKEMONS[@]}"; do
    create_java_file "$PROJECT/app/src/pokemons/$poke.java" "pokemons" "$poke"
done
echo "Project setup from zero complete!"

