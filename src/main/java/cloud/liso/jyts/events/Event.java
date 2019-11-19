package cloud.liso.jyts.events;

public interface Event<T> {
    EventArgs<T> args();
}
