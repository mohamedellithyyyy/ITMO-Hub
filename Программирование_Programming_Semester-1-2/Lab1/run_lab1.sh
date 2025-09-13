#!/bin/bash
# Lab1 Automation Script for Helios

# Variables
JAVA_FILE="Lab1.java"
CLASS_FILE="Lab1.class"
JAR_FILE="app.jar"
MANIFEST_FILE="MANIFEST.mf"

# Check if Java file exists
if [ ! -f "$JAVA_FILE" ]; then
    echo "Error: $JAVA_FILE not found in the current directory."
    exit 1
fi

# Compile the Java program
echo "Compiling $JAVA_FILE..."
javac "$JAVA_FILE"
if [ $? -ne 0 ]; then
    echo "Compilation failed."
    exit 1
fi
echo "Compilation successful."

# Create the manifest file
echo "Creating $MANIFEST_FILE..."
echo "Main-Class: Lab1" > "$MANIFEST_FILE"

# Create the JAR file
echo "Creating JAR file $JAR_FILE..."
jar -cfm "$JAR_FILE" "$MANIFEST_FILE" "$CLASS_FILE"
if [ $? -ne 0 ]; then
    echo "Failed to create JAR file."
    exit 1
fi
echo "JAR file created successfully."

# Run the JAR file
echo "Running $JAR_FILE..."
java -jar "$JAR_FILE"

