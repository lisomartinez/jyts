package cloud.liso.jyts.ui.controller;

import cloud.liso.jyts.entities.Torrent;
import cloud.liso.jyts.eventbus.EventBus;
import cloud.liso.jyts.events.EventArgs;
import cloud.liso.jyts.events.impl.commands.*;
import cloud.liso.jyts.events.impl.events.MovieReadyEvent;
import cloud.liso.jyts.ui.view.main.MenuItem;
import cloud.liso.jyts.ui.view.shared.Window;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@DependsOn({"reactorBus", "window", "downloadOptions"})
public class MainController {
    private static final int COLUMN = 1;
    private static final int ROW = 1;
    private final int ID = 1;
    private EventBus bus;
    private List<MenuItem> items;
    private Screen screen;

    public MainController(EventBus bus, Window window) {
        this.bus = bus;
        this.screen = window.screen();
        bus.events(MovieReadyEvent.class)
                .cast(MovieReadyEvent.class)
                .map(MovieReadyEvent::args)
                .map(EventArgs::content)
                .doOnNext(menuItems -> items = menuItems)
                .doOnNext(menuItems -> bus.publish(new ShowMain(1, ROW, COLUMN, items)))
                .subscribe(menuItems -> readInput(1));

        bus.events(SelectItemCommand.class)
                .cast(SelectItemCommand.class)
                .map(SelectItemCommand::args)
                .map(EventArgs::content)
                .subscribe(i -> bus.publish(new ShowMain(i, ROW, COLUMN, items)));

        bus.events(ReadNextInput.class)
                .cast(ReadNextInput.class)
                .map(ReadNextInput::args)
                .map(EventArgs::content)
                .subscribe(this::readInput);

        bus.events(ClearOptions.class)
                .cast(ClearOptions.class)
                .doOnNext(System.out::println)
                .filter(options -> options.getId() == ID)
                .map(ClearOptions::args)
                .map(EventArgs::content)
                .doOnNext(i -> bus.publish(new ShowMain(i, ROW, COLUMN, items)))
                .subscribe(this::readInput);

        bus.events(ShowMenu.class)
                .doOnNext(menuItems -> bus.publish(new ShowMain(1, ROW, COLUMN, items)))
                .subscribe(menuItems -> readInput(1));
    }

    private void readInput(int index) {
        boolean input = true;
        try {
            KeyStroke keyStroke = screen.readInput();
            if (keyStroke != null) {

                switch (keyStroke.getKeyType()) {
                    case ArrowUp:
                        bus.publish(new SelectItemCommand(calcPrev(index)));
                        bus.publish(new ReadNextInput(calcPrev(index)));
                        break;
                    case ArrowDown:
                        bus.publish(new SelectItemCommand(calcNext(index)));
                        bus.publish(new ReadNextInput(calcNext(index)));
                        break;
                    case ArrowRight:
                        bus.publish(new ShowMovieDetailsCommand(items.get(index - 1)));
                        return;
                    case Character:
                        if (keyStroke.getCharacter().equals('d')) {
                            bus.publish(new ShowTorrentOptionsCommand(items.get(index - 1), ID, index));
                        } else if (keyStroke.getCharacter().equals('s')) {
                           bus.publish(new ShowSearchDialog());
                        } else {
                            bus.publish(new ReadNextInput(index));
                        }
                        break;
                    default:
                        bus.publish(new ReadNextInput(index));
                }
            }
        } catch (IOException ex) {

        }

    }

    private List<Torrent> getTorrentsOfSelectedMovie(int index) {
        return items.get(index - 1).getMovie().getTorrents();
    }

    private int calcNext(int index) {
        if (index < items.size()) {
            return index + 1;
        } else {
            return 1;
        }
    }

    private int calcPrev(int index) {
        if (index > 1) {
            return index - 1;
        } else {
            return items.size();
        }
    }

}
