#!/bin/bash

mkdir -p app/bin
javac -cp app/vendor/Pokemon.jar -d app/bin $(find app/src  -name "*.java")
mkdir -p build
echo -e "Main-Class: Main\nClass-Path: Pokemon.jar" > MANIFEST.mf
jar cfm build/Lab2.jar MANIFEST.mf -C app/bin .
java -cp build/Lab2.jar:app/vendor/Pokemon.jar Main