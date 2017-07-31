#!/bin/bash
poolSize=$1
PORT=$2
numberOfClients=$3
SIZE=$4
MIN=$5
MAX=$6

javac ./src/main/java/org/wso2/samples/ClientThread.java
javac ./src/main/java/org/wso2/samples/Server.java
javac ./src/main/java/org/wso2/samples/Client.java


java ./src/main/java/org/wso2/samples/Server ${poolSize} ${PORT} ${SIZE} ${MIN} ${MAX}
for((i=0;i<=${numberOfClients};i++))
do
java ./src/main/java/org/wso2/samples/Client ${PORT}
done

#if [ javac ./src/main/java/org/wso2/samples/ClientThread.java ] && [ javac ./src/main/java/org/wso2/samples/Server.java ];
#then
#echo "Compilation successful!"
#java ./src/main/java/org/wso2/samples/Server ${poolSize} ${PORT}
#else
#echo "Compilation failed"
#fi