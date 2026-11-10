package by.alromos.java.advanced.avro;

import java.io.IOException;
import java.io.InputStream;
import org.apache.avro.Schema;

final class AvroDemo {

  static final String BOOTSTRAP_SERVERS = "localhost:9092";
  static final String SCHEMA_REGISTRY_URL = "http://localhost:8081";
  static final String TOPIC = "users";

  private AvroDemo() {
  }

  static Schema loadSchema(String version) {
    String resource = "/avro/user-" + version + ".avsc";
    try (InputStream in = AvroDemo.class.getResourceAsStream(resource)) {
      if (in == null) {
        throw new IllegalArgumentException("Unknown schema version '" + version + "' (expected v1 or v2)");
      }
      return new Schema.Parser().parse(in);
    } catch (IOException e) {
      throw new IllegalStateException("Cannot read " + resource, e);
    }
  }

}
