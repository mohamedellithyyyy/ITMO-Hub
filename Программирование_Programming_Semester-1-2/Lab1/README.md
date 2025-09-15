# Moving and Running Lab1 on Helios

This guide explains how to move your `Lab1.java` file to **helios** and run it there.

---

## 1. Connect to Helios
Open a terminal and connect to the server:

```bash
ssh -p 2222 yourUserName@se.ifmo.ru
```
Type yes if prompted.

Enter your password when asked.

## 2. Navigate to Your File

For example, if your file is on your desktop:
```bash
cd /Users/mohamed/Desktop/itmo/
```
## 3. Copy File to Helios

Use scp to move your Lab1.java to helios:
```bash
scp -P 2222 Lab1.java yourUserName@helios.se.ifmo.ru:~/
```
## 4. Compile the Program

Once connected to helios, compile your Java file:
```bash
javac Lab1.java
```

Check that the compiled class exists:
```bash
ls
```

You should see Lab1.class.

## 5. Run the Program

Execute your program with:
```bash
java Lab1
```

## 6. Create a JAR File

Create a manifest file:
```bash
touch MANIFEST.mf
vim MANIFEST.mf
```

Inside MANIFEST.mf, add:

Main-Class: Lab1


Save and exit. => 
```bash 
:wq
```

Package into a .jar file:
```bash
jar -cfm app.jar MANIFEST.mf Lab1.class
```

Run the .jar:
```bash
java -jar app.jar
```