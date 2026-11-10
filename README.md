# Kafka + Avro + Schema Registry demo

```
docker compose up -d                          # Kafka 4.4.0 (localhost:9092) + Schema Registry (localhost:8081)
./gradlew runProducer -Pschema=v1             # registers user-v1.avsc, id 1
./gradlew runProducer -Pschema=v2             # registers user-v2.avsc (adds `email`), id 2
./gradlew runConsumer -Pschema=v2             # reader schema v2; Ctrl+C to stop
./gradlew runConsumer -Pschema=v1             # reader schema v1
```

The consumer prints the magic byte and the writer schema id (bytes 1-4 of the value) of every message,
then the record resolved against the chosen reader schema. Registry state:

```
curl localhost:8081/subjects/users-value/versions
curl localhost:8081/schemas/ids/2
```
