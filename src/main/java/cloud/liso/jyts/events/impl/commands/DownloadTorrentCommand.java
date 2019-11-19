package cloud.liso.jyts.events.impl.commands;

import cloud.liso.jyts.entities.Torrent;
import cloud.liso.jyts.events.Event;
import cloud.liso.jyts.events.EventArgs;

public class DownloadTorrentCommand implements Event<Torrent> {
    private Torrent option;

    public DownloadTorrentCommand(Torrent option) {
        this.option = option;
    }

    @Override
    public EventArgs<Torrent> args() {
        return () -> option;
    }
}
