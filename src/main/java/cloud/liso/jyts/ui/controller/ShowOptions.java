package cloud.liso.jyts.ui.controller;

import cloud.liso.jyts.events.Event;
import cloud.liso.jyts.events.EventArgs;
import cloud.liso.jyts.ui.view.main.MenuItem;
import lombok.Data;

@Data
public class ShowOptions implements Event<MenuItem> {
    private final MenuItem content;
    @Override
    public EventArgs<MenuItem> args() {
        return () -> content;
    }
}
