package tobyspring.hellospring.api;

import java.io.IOException;
import java.net.URI;

@FunctionalInterface
public interface ApiExecutor {

    String execute(URI uri) throws IOException;
}
