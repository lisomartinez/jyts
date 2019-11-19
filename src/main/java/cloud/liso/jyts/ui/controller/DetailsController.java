package cloud.liso.jyts.ui.controller;

import cloud.liso.jyts.eventbus.EventBus;
import cloud.liso.jyts.events.EventArgs;
import cloud.liso.jyts.events.ShowDetails;
import cloud.liso.jyts.events.impl.commands.ClearOptions;
import cloud.liso.jyts.events.impl.commands.ShowMenu;
import cloud.liso.jyts.events.impl.commands.ShowMovieDetailsCommand;
import cloud.liso.jyts.events.impl.commands.ShowTorrentOptionsCommand;
import cloud.liso.jyts.ui.view.main.MenuItem;
import cloud.liso.jyts.ui.view.shared.Window;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@DependsOn({"reactorBus", "window", "downloadOptions"})
public class DetailsController {
    private static final int ID = 2;
    private static final int COLUMN = 1;
    private static final int ROW = 1;
    private EventBus bus;
    private Screen screen;

    public DetailsController(EventBus bus, Window window) {
        this.bus = bus;
        this.screen = window.screen();
        this.bus.events(ShowMovieDetailsCommand.class)
                .cast(ShowMovieDetailsCommand.class)
                .map(ShowMovieDetailsCommand::args)
                .map(EventArgs::content)
                .doOnNext(mi -> bus.publish(new ShowDetails(mi, ROW, COLUMN)))
                .subscribe(this::readInput);
        bus.events(ClearOptions.class)
                .cast(ClearOptions.class)
                .doOnNext(System.out::println)
                .filter(options -> options.getId() == ID)
                .doOnNext(mi -> bus.publish(new ShowDetails(mi.getMenuItem(), ROW, COLUMN)))
                .map(ClearOptions::getMenuItem)
                .subscribe(this::readInput);
    }

    private void readInput(MenuItem menuItem) {
        boolean downloaded = false;
        try {
            KeyStroke keyStroke = screen.readInput();
            if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                    case ArrowLeft:
                        bus.publish(new ShowMenu());
                        System.out.println("MENU");
                        downloaded = true;
                        break;
                    case Character:
                        if (keyStroke.getCharacter().equals('d')) {
                           bus.publish(new ShowTorrentOptionsCommand(menuItem, ID,  menuItem.getIndex()));
                            downloaded = true;
                        }
                        break;
                }
            }
            if (!downloaded) {
                readInput(menuItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
