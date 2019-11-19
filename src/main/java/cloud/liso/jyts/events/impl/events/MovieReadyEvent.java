package cloud.liso.jyts.events.impl.events;

import cloud.liso.jyts.events.Event;
import cloud.liso.jyts.events.EventArgs;
import cloud.liso.jyts.ui.view.main.MenuItem;

import java.util.List;

public class MovieReadyEvent implements Event<List<MenuItem>> {
    private List<MenuItem> content;

    public MovieReadyEvent(List<MenuItem> content) {
        this.content = content;
    }

    @Override
    public EventArgs<List<MenuItem>> args() {
        return () -> content;
    }
}
