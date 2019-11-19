package cloud.liso.jyts.ui.view.main;

import cloud.liso.jyts.eventbus.EventBus;
import cloud.liso.jyts.events.impl.commands.SearchCommand;
import cloud.liso.jyts.events.impl.commands.ShowMain;
import cloud.liso.jyts.exceptions.ArgumentMissingException;
import cloud.liso.jyts.exceptions.InvalidOptionException;
import cloud.liso.jyts.exceptions.NoParametersSuppliedException;
import cloud.liso.jyts.ui.view.shared.Window;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;


@Component
@DependsOn({"reactorBus", "window"})
public class Menu {
    private static final int COLUMN = 1;
    private static final int ROW = 1;
    private final int ID = 1;
    private final Pattern VALID_OPTION = Pattern.compile("^([1-9]|1[0-9]{1,2}|20)[a-z]$");
    private EventBus bus;
    private Window window;
    private List<MenuItem> items;
    private Screen screen;

    @Autowired
    public Menu(EventBus bus, Window window) {
        this.bus = bus;
        this.window = window;
        this.screen = window.screen();
        this.items = new ArrayList<>();
        start(new String[3]);

    }

    public void start(String[] args) {
        bus.events(ShowMain.class)
                .cast(ShowMain.class)
                .subscribe(m -> show(m.getIndex(), m.getRow(), m.getCol(), m.getItems()));

    }

    private void show(int index, int row, int col, List<MenuItem> items) {
        try {
            screen.clear();

            TextGraphics textGraphics = screen.newTextGraphics();
            textGraphics.setForegroundColor(TextColor.ANSI.RED);
            textGraphics.setBackgroundColor(TextColor.ANSI.GREEN);

            IntStream.range(1, items.size() + 1).forEach(i -> {
                if (i == index) {
                    textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
                    textGraphics.setBackgroundColor(TextColor.ANSI.WHITE);
                } else {
                    textGraphics.setForegroundColor(TextColor.ANSI.RED);
                    textGraphics.setBackgroundColor(TextColor.ANSI.GREEN);
                }

                textGraphics.putString(col, i + row, items.get(i - 1).toString());
            });

            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createCommand(String[] args) {
        Arrays.stream(args, 1, args.length)
                .reduce((s, s2) -> s + " " + s2)
                .map(SearchCommand::new)
                .ifPresent(bus::publish);
    }

    private void validateArgs(String[] args) {
        if (args.length == 0) throw new NoParametersSuppliedException();
        if (!args[0].equals("-s")) throw new InvalidOptionException(args[0]);
        if (args.length < 2) throw new ArgumentMissingException(args[0]);
    }
}
