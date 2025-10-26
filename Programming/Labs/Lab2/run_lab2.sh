#!/bin/bash

# Set project directories
SRC_DIR="app/src"
BIN_DIR="app/bin"
VENDOR_JAR="app/vendor/Pokemon.jar"
BUILD_DIR="build"
OUTPUT_JAR="$BUILD_DIR/Lab2.jar"

# 1. Compile all Java sources
echo "Compiling Java files..."
mkdir -p "$BIN_DIR"
javac -cp "$VENDOR_JAR" -d "$BIN_DIR" $(find "$SRC_DIR" -name "*.java")

if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi

# 2. Create build directory if it doesn't exist
mkdir -p "$BUILD_DIR"

# 3. Create manifest
MANIFEST="MANIFEST.mf"
echo "Main-Class: Main" > $MANIFEST

# 4. Build JAR into build/
echo "Building JAR into $BUILD_DIR..."
jar cfm "$OUTPUT_JAR" "$MANIFEST" -C "$BIN_DIR" .

# 5. Run the program
echo "Running Lab2..."
java -cp "$OUTPUT_JAR:$VENDOR_JAR" Main

