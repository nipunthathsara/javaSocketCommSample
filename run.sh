#!/usr/bin/env bash
PORT=$1
QUEUESIZE=$2
MIN=$3
MAX=$4
numberOfClients=$5

mvn -f ./server/pom.xml clean install
mvn -f ./client/pom.xml clean install

java -cp ./server/target/server-1.0-SNAPSHOT.jar org.wso2.tcp.server.Server ${PORT} ${QUEUESIZE} ${MIN} ${MAX} &
for((i=1;i<=${numberOfClients};i++))
do
java -cp ./client/target/client-1.0-SNAPSHOT.jar org.wso2.tcp.client.Client ${PORT}
done