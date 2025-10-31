#!/bin/sh
echo "=== System Java ===" 
java -version 

echo -e "\n=== JAVA_HOME ===" 
echo $JAVA_HOME 

echo -e "\n=== Gradle Version ===" 
./gradlew -version | head -10