package cloud.liso.jyts.ui.controller;

import cloud.liso.jyts.entities.Torrent;
import cloud.liso.jyts.eventbus.EventBus;
import cloud.liso.jyts.events.EventArgs;
import cloud.liso.jyts.events.impl.commands.ClearOptions;
import cloud.liso.jyts.events.impl.commands.DownloadTorrentCommand;
import cloud.liso.jyts.events.impl.commands.ShowTorrentOptionsCommand;
import cloud.liso.jyts.ui.view.main.MenuItem;
import cloud.liso.jyts.ui.view.shared.Window;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DownloadOptionsController {
    private EventBus bus;
    private List<Integer> options;
    private Screen screen;
    private int id;
    private int lastIndex;

    public DownloadOptionsController(EventBus bus, Window window) {
        this.bus = bus;
        this.screen = window.screen();
        this.options = new ArrayList<>();
        bus.events(ShowTorrentOptionsCommand.class)
                .cast(ShowTorrentOptionsCommand.class)
                .doOnNext(std -> id = std.getId())
                .doOnNext(std -> lastIndex = std.getIndex())
                .doOnNext(std -> loadOptions(std.args().content()))
                .doOnNext(std -> bus.publish(new ShowOptions(std.args().content())))
                .map(ShowTorrentOptionsCommand::args)
                .map(EventArgs::content)
                .subscribe(this::readOptions);
    }

    private void readOptions(MenuItem item) {
        List<Torrent> torrents = item.torrents();
        try {
            KeyStroke keyStroke = screen.readInput();
            if (keyStroke != null && keyStroke.getKeyType().equals(KeyType.Character)) {
                char character = keyStroke.getCharacter();
                int ch = Character.getNumericValue(character);
                for (int option : options) {
                    if (option == ch) {
                        bus.publish(new DownloadTorrentCommand(torrents.get(option)));
                        return;
                    }
                }
                if (character == 'c') {
                    bus.publish(new ClearOptions(item, id, lastIndex));
                }
                else {
                    readOptions(item);
                }
            } else {
                readOptions(item);
            }
        } catch (IOException ex) {

        }
    }
    public void loadOptions(MenuItem item) {
        List<Torrent> torrents = item.torrents();
        for (int i = 0; i < torrents.size(); i++) {
            options.add(i);
        }
    }
}
