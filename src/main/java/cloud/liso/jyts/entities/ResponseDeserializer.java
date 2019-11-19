package cloud.liso.jyts.entities;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;


@Component
public class ResponseDeserializer implements JsonDeserializer<Response> {

    private MovieExtractor extractor;

    @Autowired
    public ResponseDeserializer(MovieExtractor extractor) {
        this.extractor = extractor;
    }

    @Override
    public Response deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonElement data = jsonElement.getAsJsonObject().get("data");
        if (noMoviesFound(data)) return RespondeWithoutMovies();
        extractor = new MovieExtractor();
        JsonArray moviesJson = getMoviesArrayFrom(data);
        return responseFrom(moviesJson);
    }

    private Response RespondeWithoutMovies() {
        Response response = new Response();
        response.addMovie(new EmptyMovie());
        return response;
    }

    private boolean noMoviesFound(JsonElement data) {
        int movie_count = data.getAsJsonObject().get("movie_count").getAsInt();
        if (movie_count == 0) return true;
        return false;
    }

    private JsonArray getMoviesArrayFrom(JsonElement jsonElement) {
        return jsonElement.getAsJsonObject().getAsJsonArray("movies");
    }

    private Response responseFrom(JsonArray moviesJson) {
        Response response = new Response();
        moviesJson.forEach(m -> addMovieToResponse(m, response));
        return response;
    }

    private void addMovieToResponse(JsonElement json, Response response) {
        response.addMovie(extractedMovieFrom(json));
    }

    private Movie extractedMovieFrom(JsonElement movie) {
        return extractor.extract(movie.getAsJsonObject());
    }
}
