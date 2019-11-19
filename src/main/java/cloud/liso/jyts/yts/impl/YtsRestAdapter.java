package cloud.liso.jyts.yts.impl;

import cloud.liso.jyts.entities.Movie;
import cloud.liso.jyts.entities.Response;
import cloud.liso.jyts.eventbus.EventBus;
import cloud.liso.jyts.events.Event;
import cloud.liso.jyts.events.impl.events.MovieDeserializedEvent;
import cloud.liso.jyts.events.impl.events.MovieFetchedEvent;
import cloud.liso.jyts.yts.YtsAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class YtsRestAdapter implements YtsAdapter {

    private EventBus bus;
    private Json json;
    @Autowired
    public YtsRestAdapter(EventBus bus, Json json) {
        this.bus = bus;
        this.json = json;
        this.bus.events(MovieFetchedEvent.class)
                .map(this::extractMovie)
                .flatMap(this::deserialize)
                .subscribe(this::publishMovieDeserializedEvent);
    }

    private void publishMovieDeserializedEvent(List<Movie> movie) {
        bus.publish(new MovieDeserializedEvent(movie));
    }

    private Mono<List<Movie>> deserialize(String content) {
        return Mono.just(content)
                .map(s -> json.parse(s))
                .map(Response::getMovies);
    }

    private String extractMovie(Event e) {
        return (String) e.args().content();
    }

}
