# SpringBootKafka_ESI

## Setting Up Kafka

- Make sure you are navigated inside the bin directory.

## Start Zookeeper and Kafka Broker

-   Start up the Zookeeper.

```
./zookeeper-server-start.sh ../config/zookeeper.properties
```

-   Start up the Kafka Broker

```
./kafka-server-start.sh ../config/server.properties
```

## Create a topic ?

### For input words

```
./kafka-topics.sh --create --topic word -zookeeper localhost:2181 --replication-factor 1 --partitions 4
```

### For sentences

```
./kafka-topics.sh --create --topic word -zookeeper localhost:2181 --replication-factor 1 --partitions 4
```

Install Cassandra
-----------------
This installs Apache Cassandra:

```Shell
brew install cassandra
```

Starting/Stopping Cassandra
---------------------------
Use this command to start Cassandra:

```Shell
launchctl load ~/Library/LaunchAgents/homebrew.mxcl.cassandra.plist
```

Use this command to stop Cassandra:

```Shell
launchctl unload ~/Library/LaunchAgents/homebrew.mxcl.cassandra.plist
```

#Starting the application
```
Start the spring boot application from the class ClientReader
## Start the browser/postman and write a get request localhost:8080/postWord?word="Kafka"
```

