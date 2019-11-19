package cloud.liso.jyts.entities;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
@Component
public class MovieDeserializer implements JsonDeserializer<Movie> {
    private MovieExtractor movieExtractor;
    @Override
    public Movie deserialize(JsonElement jsonElement, Type jType, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject movie = jsonElement.getAsJsonObject().getAsJsonObject("data").getAsJsonObject("movie");
        return movieExtractor.extract(movie);
    }

    @Autowired
    public void setMovieExtractor(MovieExtractor movieExtractor) {
        this.movieExtractor = movieExtractor;
    }
}
