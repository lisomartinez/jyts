package cloud.liso.jyts.yts.impl;

import cloud.liso.jyts.eventbus.EventBus;
import cloud.liso.jyts.events.impl.commands.Command;
import cloud.liso.jyts.events.impl.commands.SearchCommand;
import cloud.liso.jyts.events.impl.events.MovieFetchedEvent;
import cloud.liso.jyts.yts.YtsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.netty.http.client.HttpClient;

@Component
public class YtsRestClient implements YtsClient {

    private EventBus bus;
    @Autowired
    public YtsRestClient(EventBus bus) {
        this.bus = bus;
        this.bus.events(SearchCommand.class)
                .cast(Command.class)
                .subscribe(this::execute);
    }

    @Override
    public void execute(Command<?> command) {
        HttpClient.create()
                .get()
                .uri("https://yts.lt/api/v2/" + command.url())
                .responseContent()
                .aggregate()
                .asString()
//                .flatMapMany(content -> ytsAdapter.toEntity(Mono.just(content)))
//                .map(presenter::display)
                .subscribe(s -> bus.publish(new MovieFetchedEvent(s)));
    }
}
