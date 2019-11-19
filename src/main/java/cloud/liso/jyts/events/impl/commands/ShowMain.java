package cloud.liso.jyts.events.impl.commands;

import cloud.liso.jyts.events.Event;
import cloud.liso.jyts.events.EventArgs;
import cloud.liso.jyts.ui.view.main.MenuItem;
import lombok.Data;

import java.util.List;

@Data
public class ShowMain implements Event<Integer> {
    private final int index;
    private final int row;
    private final int col;
    private final List<MenuItem> items;

    @Override
    public EventArgs<Integer> args() {
        return () -> index;
    }
}
