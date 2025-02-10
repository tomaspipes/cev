#!/bin/bash

# Remove old compiled files
rm -rf bin

# Compile all Java files
javac -d bin -cp src $(find src -name "*.java") main/*.java

# Run the project
java -cp bin main.Main
