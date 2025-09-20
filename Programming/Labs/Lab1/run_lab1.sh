#!/bin/bash
# Lab1 Automation Script – Using Existing Folders

# Variables
JAVA_FILE="Lab1.java"
CLASS_FILE="Lab1.class"
JAR_FILE="app.jar"
MANIFEST_FILE="MANIFEST.mf"

# Move Java source file to src/ if not already there
if [ -f "$JAVA_FILE" ]; then
    mv "$JAVA_FILE" src/
fi

# Compile the Java program inside src/
cd src/
echo "Compiling $JAVA_FILE..."
javac "$JAVA_FILE"
if [ $? -ne 0 ]; then
    echo "Compilation failed."
    exit 1
fi
echo "Compilation successful."

# Move compiled class to bin/
mv "$CLASS_FILE" ../bin/

# Return to project root
cd ..

# Create manifest file
echo "Creating $MANIFEST_FILE..."
echo -e "Main-Class: Lab1\n" > "$MANIFEST_FILE"

# Create the JAR file from bin/
jar -cfm "$JAR_FILE" "$MANIFEST_FILE" -C bin/ "$CLASS_FILE"

# Move JAR to jar/ folder
mv "$JAR_FILE" jar/
echo "JAR file created successfully in jar/"

# Run the JAR file
echo "Running $JAR_FILE..."
java -jar jar/"$JAR_FILE"
