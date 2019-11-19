package cloud.liso.jyts.events.impl.events;

import cloud.liso.jyts.entities.Movie;
import cloud.liso.jyts.ui.view.main.MenuItem;

import java.util.List;


public interface MoviePresenter {
    List<MenuItem> display(List<Movie> movie);
    void start();
}
