package cloud.liso.jyts.events.impl.events;

import cloud.liso.jyts.entities.Movie;
import cloud.liso.jyts.events.Event;
import cloud.liso.jyts.events.EventArgs;

import java.util.List;

public class MovieDeserializedEvent implements Event<List<Movie>> {
    private List<Movie> movie;

    public MovieDeserializedEvent(List<Movie> movie) {
        this.movie = movie;
    }

    @Override
    public EventArgs<List<Movie>> args() {
        return () -> movie;
    }
}
