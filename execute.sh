#!/bin/bash
poolSize=$1
PORT=$2
numberOfClients=$3

javac /src/main/java/org/wso2/samples/Server.java
javac /src/main/java/org/wso2/samples/Client.java
javac /src/main/java/org/wso2/samples/ClientThread.java

java /src/main/java/org/wso2/samples/Server ${poolSize} ${PORT}
for((i=0;i<=${numberOfClients};i++))
do
java /src/main/java/org/wso2/samples/Client ${PORT}
done