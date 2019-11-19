package cloud.liso.jyts.yts.impl;

import cloud.liso.jyts.entities.Movie;
import cloud.liso.jyts.eventbus.EventBus;
import cloud.liso.jyts.events.EventArgs;
import cloud.liso.jyts.events.impl.events.MovieDeserializedEvent;
import cloud.liso.jyts.events.impl.events.MoviePresenter;
import cloud.liso.jyts.events.impl.events.MovieReadyEvent;
import cloud.liso.jyts.ui.view.main.MenuItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class YtsMoviePresenter implements MoviePresenter {
    private EventBus bus;

    public YtsMoviePresenter(EventBus bus) {
        this.bus = bus;

        this.bus.events(MovieDeserializedEvent.class)
                .cast(MovieDeserializedEvent.class)
                .map(MovieDeserializedEvent::args)
                .map(EventArgs::content)
                .map(this::display)
                .subscribe(items -> bus.publish(new MovieReadyEvent(items)));
    }

    @Override
    public List<MenuItem> display(List<Movie> movies) {
        return IntStream.range(0, movies.size())
                .mapToObj(i -> new MenuItem(i + 1, movies.get(i)))
                .collect(Collectors.toList());
    }

    @Override
    public void start() {
        this.bus.events(MovieDeserializedEvent.class)
                .cast(MovieDeserializedEvent.class)
                .map(MovieDeserializedEvent::args)
                .map(EventArgs::content)
                .map(this::display)
                .subscribe(items -> bus.publish(new MovieReadyEvent(items)));
    }
}
