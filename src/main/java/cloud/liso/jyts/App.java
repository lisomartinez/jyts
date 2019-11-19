package cloud.liso.jyts;

import cloud.liso.jyts.entities.Movie;
import cloud.liso.jyts.entities.Response;
import cloud.liso.jyts.eventbus.EventBus;
import cloud.liso.jyts.events.impl.commands.Command;
import cloud.liso.jyts.events.impl.events.MovieDeserializedEvent;
import cloud.liso.jyts.ui.ArgsParser;
import cloud.liso.jyts.ui.CommandFactory;
import cloud.liso.jyts.ui.Parameters;
import cloud.liso.jyts.yts.impl.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication(exclude = { JacksonAutoConfiguration.class })

public class App implements ApplicationRunner {
    @Autowired
    private EventBus bus;
    @Autowired
    private Json json;
    @Autowired
    private ArgsParser argsParser;
    @Autowired
    private CommandFactory commandFactory;

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);

        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Parameters parse1 = argsParser.parse(args.getSourceArgs());
        Command<?> command = commandFactory.createCommand(parse1);
        Path path = Paths.get("src", "test", "resources", "movies.json");
        String res = Files.lines(path).collect(Collectors.joining("\n"));
        Response parse = json.parse(res);
        List<Movie> movies = parse.getMovies();
//        bus.publish(command);
        bus.publish(new MovieDeserializedEvent(movies));
    }
}
