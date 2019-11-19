package cloud.liso.jyts.events.impl.commands;

import cloud.liso.jyts.events.Event;
import cloud.liso.jyts.events.EventArgs;
import cloud.liso.jyts.ui.view.main.MenuItem;

public class ShowMovieDetailsCommand implements Event<MenuItem> {
    private MenuItem item;

    public ShowMovieDetailsCommand(MenuItem item) {
        this.item = item;
    }

    @Override
    public EventArgs<MenuItem> args() {
        return () -> item;
    }
}
