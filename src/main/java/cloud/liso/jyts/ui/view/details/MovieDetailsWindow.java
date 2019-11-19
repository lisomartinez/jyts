package cloud.liso.jyts.ui.view.details;

import cloud.liso.jyts.eventbus.EventBus;
import cloud.liso.jyts.events.ShowDetails;
import cloud.liso.jyts.ui.view.main.MenuItem;
import cloud.liso.jyts.ui.view.shared.Window;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MovieDetailsWindow {
    private EventBus bus;
    private Screen screen;

    public MovieDetailsWindow(EventBus bus, Window window) {
        this.bus = bus;
        this.screen = window.screen();
        bus.events(ShowDetails.class)
                .cast(ShowDetails.class)
                .subscribe(sd -> showMenuItem(sd.getMi(), sd.getRow(), sd.getColumn()));
    }
    private void showMenuItem(MenuItem menuItem, int row, int column) {
        screen.clear();
        Display display = new Display(menuItem.getMovie());
        TextGraphics textGraphics = screen.newTextGraphics();
        display.print(textGraphics, row, column, screen.getTerminalSize().getColumns() - 4);
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
