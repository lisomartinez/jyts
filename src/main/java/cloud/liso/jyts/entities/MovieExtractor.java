package cloud.liso.jyts.entities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieExtractor {
    public Movie extract(JsonObject movie) {
        return Movie.builder()
                .id(id(movie))
                .runtime(runtime(movie))
                .year(year(movie))
                .rating(rating(movie))
                .title(title(movie))
                .language(language(movie))
                .description(description(movie))
                .genres(genres(movie))
                .torrents(torrents(movie))
                .build();

    }

    private List<Torrent> torrents(JsonObject movie) {
        List<Torrent> torrents = new ArrayList<>();
        movie.get("torrents").getAsJsonArray().forEach(t -> torrents.add(createTorrent(t)));
        return torrents;
    }

    private List<String> genres(JsonObject movie) {
        JsonArray genresArray = movie.get("genres").getAsJsonArray();
        List<String> genres = new ArrayList<>();
        genresArray.forEach(g -> genres.add(g.getAsString()));
        return genres;
    }

    private String description(JsonObject movie) {
        return movie.get("description_full").getAsString();
    }

    private String title(JsonObject movie) {
        return movie.get("title").getAsString();
    }

    private double rating(JsonObject movie) {
        return movie.get("rating").getAsDouble();
    }

    private int year(JsonObject movie) {
        return movie.get("year").getAsInt();
    }


    private int runtime(JsonObject movie) {
        return movie.get("runtime").getAsInt();
    }

    private int id(JsonObject movie) {
        return movie.get("id").getAsInt();
    }

    private String language(JsonObject movie) {
        return movie.get("language").getAsString();}

    private Torrent createTorrent(JsonElement torrentJson) {
        JsonObject torrent = torrentJson.getAsJsonObject();

        return Torrent.builder()
                .url(url(torrent))
                .hash(hash(torrent))
                .quality(quality(torrent))
                .type(type(torrent))
                .seeds(seeds(torrent))
                .peers(peers(torrent))
                .size(size(torrent))
                .uploaded(uploaded(torrent))
                .build();
    }

    private LocalDateTime uploaded(JsonObject torrent) {
        return LocalDateTime.parse(
                torrent.get("date_uploaded").getAsString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private long size(JsonObject torrent) {
        return torrent.get("size_bytes").getAsLong();
    }

    private int peers(JsonObject torrent) {
        return torrent.get("peers").getAsInt();
    }

    private int seeds(JsonObject torrent) {
        return torrent.get("seeds").getAsInt();
    }

    private String quality(JsonObject torrent) {
        return torrent.get("quality").getAsString();
    }


    private String type(JsonObject torrent) {
        return torrent.get("type").getAsString();
    }

    private String hash(JsonObject torrent) {
        return torrent.get("hash").getAsString();
    }

    private String url(JsonObject torrent) {
        return torrent.get("url").getAsString();
    }
}
