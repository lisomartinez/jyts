package cloud.liso.jyts.events.impl.commands;

import cloud.liso.jyts.events.EventArgs;
import lombok.Data;

import static cloud.liso.jyts.ui.Extensions.urlify;

@Data
public final class SearchCommand implements Command<String> {
    private static final String relPath = "list_movies.json?query_term=";
    private final String movie;

    @Override
    public EventArgs<String> args() {
        return () ->  relPath + urlify(movie);
    }

    @Override
    public String url() {
        return args().content();
    }
}
