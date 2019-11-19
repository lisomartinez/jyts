package cloud.liso.jyts.ui.view.main;

import cloud.liso.jyts.entities.Movie;
import cloud.liso.jyts.entities.Torrent;
import lombok.Data;

import java.util.List;

@Data
public class MenuItem {
    private final int index;
    private final Movie movie;
    private StringBuilder st;

    @Override
    public String toString() {
        if (st == null) {
            st = new StringBuilder();
            if (movie.getId() == 0) {
                st.append("\tNot found.");
            } else {
                 st.append("\t")
                        .append(index)
                        .append(" - ")
                        .append(movie.getTitle())
                        .append(" (")
                        .append(movie.getYear())
                        .append(')');
            }
        }
        return st.toString();
    }

    public List<Torrent> torrents() {
        return movie.getTorrents();
    }
}
