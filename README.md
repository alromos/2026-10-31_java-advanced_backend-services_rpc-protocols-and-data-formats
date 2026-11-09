# gRPC Ping/Pong

Shared contract: `proto/ping.proto` (used by Java server, Java client and Python client).

## Java (Gradle wrapper, JDK 25 by default)

The build uses a Gradle toolchain for Java 25, so JDK 25 must be installed locally (Gradle does not download it).

```
./gradlew :server:run     # starts server on port 8080
./gradlew :client:run     # sends {"message": "Ping"}, prints Pong
```

Use `gradlew.bat` on Windows. An alternative target can be passed to the client: `./gradlew :client:run --args="host:port"`.

## Python

```
python -m venv .venv
.venv/Scripts/activate
# Linux/macOS: source .venv/bin/activate
pip install -r python-client/requirements.txt
python python-client/client.py
```

Stubs are generated from `proto/ping.proto` into `python-client/generated/` on each run.
