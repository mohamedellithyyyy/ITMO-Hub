#!/bin/bash
echo "=============================="
echo "LAB 6 RUN SCRIPT"
echo "=============================="

# ---------------- DIRS ----------------
mkdir -p out/common out/server out/client

# ---------------- COMPILE COMMON MODELS ----------------
echo "Compiling common models..."
javac -d out/common \
  common/src/main/java/models/*.java

if [ $? -ne 0 ]; then echo "❌ Common models failed"; exit 1; fi

# ---------------- COMPILE COMMON NETWORK ----------------
echo "Compiling common network..."
javac -cp out/common -d out/common \
  common/src/main/java/network/*.java

if [ $? -ne 0 ]; then echo "❌ Common network failed"; exit 1; fi

echo "✅ Common compiled"

# ---------------- COMPILE SERVER ----------------
echo "Compiling server..."
javac -cp out/common -d out/server \
  server/src/main/java/exceptions/*.java \
  server/src/main/java/managers/*.java \
  server/src/main/java/utility/*.java \
  server/src/main/java/commands/*.java \
  server/src/main/java/network/*.java \
  server/src/main/java/ServerMain.java

if [ $? -ne 0 ]; then echo "❌ Server failed"; exit 1; fi

echo "✅ Server compiled"

# ---------------- COMPILE CLIENT ----------------
echo "Compiling client..."
javac -cp out/common -d out/client \
  client/src/main/java/console/*.java \
  client/src/main/java/network/*.java \
  client/src/main/java/ClientMain.java

if [ $? -ne 0 ]; then echo "❌ Client failed"; exit 1; fi

echo "✅ Client compiled"

# ---------------- START SERVER ----------------
echo "Starting server..."
export MUSIC_COLLECTION_FILE=data/bands.xml
java -cp out/common:out/server ServerMain &
SERVER_PID=$!
sleep 2

# ---------------- START CLIENT ----------------
echo "Starting client..."
java -cp out/common:out/client ClientMain

# ---------------- STOP SERVER ----------------
echo "Stopping server..."
kill $SERVER_PID
echo "=============================="
echo "DONE"
echo "=============================="