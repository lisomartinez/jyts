package cloud.liso.jyts.events.impl.commands;

import cloud.liso.jyts.events.Event;
import cloud.liso.jyts.events.EventArgs;
import cloud.liso.jyts.ui.view.main.MenuItem;

public class ShowTorrentOptionsCommand implements Event<MenuItem> {
    private final MenuItem item;
    private final int id;
    private final int index;

    public ShowTorrentOptionsCommand(MenuItem item, int id, int index) {
        this.item = item;
        this.id = id;
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public int getIndex() { return index;}
    @Override
    public EventArgs<MenuItem> args() {
        return () -> item;
    }
}
