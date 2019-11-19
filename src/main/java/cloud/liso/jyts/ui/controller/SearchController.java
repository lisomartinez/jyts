package cloud.liso.jyts.ui.controller;

import cloud.liso.jyts.eventbus.EventBus;
import cloud.liso.jyts.ui.view.shared.Window;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;
import com.googlecode.lanterna.screen.Screen;
import org.springframework.stereotype.Component;

@Component
public class SearchController {
    private Screen screen;
    private EventBus bus;
    private WindowBasedTextGUI textGUI;


    public SearchController(Window window, EventBus bus) {
        this.screen = window.screen();
        this.textGUI = new MultiWindowTextGUI(window.screen());
        this.bus = bus;
        bus.events(ShowSearchDialog.class).subscribe(e -> showDialog());
    }

    private void showDialog() {
        String result = new TextInputDialogBuilder()
                .setTitle("Multi-line editor")
                .setTextBoxSize(new TerminalSize(35, 1))
                .build()
                .showDialog(textGUI);
        System.out.println("result = " + result);
    }
}
