package com.logistpulse.core.observer;

import java.util.ArrayList;
import java.util.List;

/** Notificador: guarda suscriptores y les avisa. No conoce a ninguno en concreto. */
public class StockPublisher {
    private final List<StockListener> listeners = new ArrayList<>();
    public void subscribe(StockListener l) { listeners.add(l); }
    public void unsubscribe(StockListener l) { listeners.remove(l); }
    public void publish(StockChanged e) { new ArrayList<>(listeners).forEach(l -> l.onStockChanged(e)); }
}
