package ru.femboypig.utils.interfaces;

public interface Cancellable {

    void setCancelled(boolean cancelled);

    boolean isCancelled();

    default void cancel() {
        setCancelled(true);
    }
}