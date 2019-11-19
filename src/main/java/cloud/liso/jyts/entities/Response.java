package cloud.liso.jyts.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Response {
    private final List<Movie> movies;

    public Response() {
        this.movies = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }
}
