package cloud.liso.jyts.yts.impl;

import cloud.liso.jyts.entities.Movie;
import cloud.liso.jyts.events.EventArgs;

public class MovieArgs implements EventArgs<Movie> {
    private Movie movie;

    @Override
    public Movie content() {
        return movie;
    }
}
