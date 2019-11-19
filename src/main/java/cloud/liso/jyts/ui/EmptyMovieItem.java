package cloud.liso.jyts.ui;

import cloud.liso.jyts.entities.EmptyMovie;
import cloud.liso.jyts.ui.view.main.MenuItem;

public class EmptyMovieItem extends MenuItem {
    public EmptyMovieItem() {
        super(0, new EmptyMovie());
    }

    @Override
    public String toString() {
        return "Movie not found";
    }
}
