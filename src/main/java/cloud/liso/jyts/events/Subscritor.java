package cloud.liso.jyts.events;

public interface Subscritor {
    <T> void onEvent(T event);
}
