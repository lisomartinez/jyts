package cloud.liso.jyts.events.impl.commands;

import cloud.liso.jyts.entities.Torrent;
import cloud.liso.jyts.events.Event;
import cloud.liso.jyts.events.EventArgs;

import java.util.List;

public class ShowDownloadMenuCommand implements Event<List<Torrent>> {
    private List<Torrent> torrents;

    public ShowDownloadMenuCommand(List<Torrent> torrents) {
        this.torrents = torrents;
    }

    @Override
    public EventArgs<List<Torrent>> args() {
        return () -> torrents;
    }
}
