#!/bin/bash

echo "🚀 Creating Maven-style project structure..."

# ---------------- ROOT MODULES ----------------
mkdir -p client/src/main/java/network
mkdir -p client/src/main/java/console

mkdir -p server/src/main/java/network
mkdir -p server/src/main/java/commands
mkdir -p server/src/main/java/managers
mkdir -p server/src/main/java/exceptions

mkdir -p common/src/main/java/models
mkdir -p common/src/main/java/network

mkdir -p data

# ---------------- CLIENT FILES ----------------
touch client/src/main/java/ClientMain.java
touch client/src/main/java/network/Client.java
touch client/src/main/java/network/ServerConnection.java
touch client/src/main/java/console/ConsoleReader.java
touch client/src/main/java/console/CommandBuilder.java

# ---------------- SERVER FILES ----------------
touch server/src/main/java/ServerMain.java

touch server/src/main/java/network/ConnectionAcceptor.java
touch server/src/main/java/network/RequestReader.java
touch server/src/main/java/network/ResponseSender.java
touch server/src/main/java/network/Server.java

touch server/src/main/java/commands/AddCommand.java
touch server/src/main/java/commands/ClearCommand.java
touch server/src/main/java/commands/Command.java
touch server/src/main/java/commands/CommandManager.java
touch server/src/main/java/commands/CommandProcessor.java
touch server/src/main/java/commands/ExecuteScriptCommand.java
touch server/src/main/java/commands/FilterStartsWithNameCommand.java
touch server/src/main/java/commands/HeadCommand.java
touch server/src/main/java/commands/HelpCommand.java
touch server/src/main/java/commands/InfoCommand.java
touch server/src/main/java/commands/PrintUniqueParticipantsCommand.java
touch server/src/main/java/commands/RemoveByIdCommand.java
touch server/src/main/java/commands/RemoveHeadCommand.java
touch server/src/main/java/commands/RemoveLowerCommand.java
touch server/src/main/java/commands/SaveCommand.java
touch server/src/main/java/commands/ShowCommand.java
touch server/src/main/java/commands/SumOfParticipantsCommand.java
touch server/src/main/java/commands/UpdateCommand.java

touch server/src/main/java/managers/CollectionManager.java
touch server/src/main/java/managers/FileManager.java
touch server/src/main/java/managers/IdGenerator.java

touch server/src/main/java/exceptions/CommandExecutionException.java
touch server/src/main/java/exceptions/DuplicateIdException.java
touch server/src/main/java/exceptions/FileLoadException.java
touch server/src/main/java/exceptions/InvalidInputException.java

# ---------------- COMMON FILES ----------------
touch common/src/main/java/models/Color.java
touch common/src/main/java/models/Coordinates.java
touch common/src/main/java/models/Location.java
touch common/src/main/java/models/MusicBand.java
touch common/src/main/java/models/MusicGenre.java
touch common/src/main/java/models/Person.java

touch common/src/main/java/network/CommandType.java
touch common/src/main/java/network/Request.java
touch common/src/main/java/network/Response.java

# ---------------- DATA ----------------
touch data/bands.xml

echo "✅ Project structure created successfully!"
