package cloud.liso.jyts.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Movie {
    private final int id;
    private final String title;
    private final int year;
    private final double rating;
    private final int runtime;
    private final List<String> genres;
    private final String description;
    private final String language;
    private final List<Torrent> torrents;
}
