#!/bin/bash
mkdir -p src/world src/entity src/characters src/insects src/interfaces src/enums src/model src/exceptions
mkdir -p build
mkdir -p docs/report
touch src/Main.java
touch src/world/World.java
touch src/world/Context.java
touch src/world/Logger.java
touch src/entity/Entity.java
touch src/entity/Character.java
touch src/entity/Insect.java
touch src/characters/Human.java
touch src/characters/Neznaika.java
touch src/insects/MayBug.java
touch src/insects/Butterfly.java
touch src/interfaces/Movable.java
touch src/interfaces/Attackable.java
touch src/interfaces/Aggressor.java
touch src/enums/State.java
touch src/enums/Direction.java
touch src/enums/Gender.java
touch src/enums/LocationType.java
touch src/model/Position.java
touch src/exceptions/CollisionException.java
touch src/exceptions/RuntimeCollisionException.java
echo "# Лабораторная работа 3-4" > README.md
echo "Структура проекта успешно создана!\n Урааааааааааааааааааааааааааааааааааааааааааа"
