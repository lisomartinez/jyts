package cloud.liso.jyts.events;

@FunctionalInterface
public interface EventArgs<T> {
    T content();
}
