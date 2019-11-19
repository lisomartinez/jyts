package cloud.liso.jyts.ui.view.download;

import cloud.liso.jyts.entities.Torrent;
import cloud.liso.jyts.eventbus.EventBus;
import cloud.liso.jyts.events.EventArgs;
import cloud.liso.jyts.ui.controller.ShowOptions;
import cloud.liso.jyts.ui.view.main.MenuItem;
import cloud.liso.jyts.ui.view.shared.Window;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class DownloadOptions {
    private Screen screen;
    private EventBus bus;

    @Autowired
    public DownloadOptions(EventBus bus, Window window) {
        this.bus = bus;
        this.screen = window.screen();
        bus.events(ShowOptions.class)
                .cast(ShowOptions.class)
                .map(ShowOptions::args)
                .map(EventArgs::content)
                .subscribe(this::showOptions);
    }

    private void showOptions(MenuItem item) {
        List<Torrent> torrents = item.torrents();
        try {
            TerminalSize terminalSize = screen.getTerminalSize();
            StringBuilder st = new StringBuilder();
            int[] options = new int[torrents.size()];
            st.append("Options: ");
            for (int i = 0; i < torrents.size(); i++) {
                st.append("\t").append("(").append(i).append(") ")
                        .append(torrents.get(i).getQuality()).append(" ");
                options[i] = i;
            }
            TextGraphics graphics = screen.newTextGraphics();
            graphics.putString(1, terminalSize.getRows() - 2, st.toString());
            screen.refresh();
        } catch (IOException ex) {

        }
    }
}
