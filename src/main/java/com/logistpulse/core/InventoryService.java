package com.logistpulse.core;

import com.logistpulse.core.observer.StockChanged;
import com.logistpulse.core.observer.StockPublisher;
import com.logistpulse.core.state.Lot;
import com.logistpulse.core.strategy.PolicyRegistry;
import java.util.*;

/** Une los tres patrones: State (lotes), Strategy (picking) y Observer (eventos). */
public class InventoryService {
    private final Map<String, Lot> lots = new LinkedHashMap<>();
    private final StockPublisher publisher;
    private final PolicyRegistry policies;

    public InventoryService(StockPublisher publisher, PolicyRegistry policies) {
        this.publisher = publisher; this.policies = policies;
    }

    public int available(String sku) {
        return lots.values().stream().filter(l -> l.getSku().equals(sku) && l.isAvailable())
                   .mapToInt(Lot::getQuantity).sum();
    }

    /** Ejecuta una transicion de estado y publica StockChanged si cambio el disponible. */
    private void change(Lot lot, Runnable transition, String reason) {
        int before = available(lot.getSku());
        transition.run(); // lanza IllegalStateException si la transicion es invalida
        int after = available(lot.getSku());
        if (before != after) publisher.publish(new StockChanged(lot.getSku(), before, after, reason));
    }

    public void receive(Lot lot) { lots.put(lot.getId(), lot); }
    public void store(String id)             { Lot l = get(id); change(l, l::store, "almacenado " + id); }
    public void quarantine(String id)        { Lot l = get(id); change(l, l::quarantine, "cuarentena " + id); }
    public void releaseQuarantine(String id) { Lot l = get(id); change(l, l::releaseQuarantine, "liberado de cuarentena " + id); }
    public void ship(String id)              { Lot l = get(id); change(l, l::ship, "enviado " + id); }

    /** Reserva el lote que decida la PickingPolicy configurada para el SKU. */
    public Lot reserveNext(String sku) {
        List<Lot> candidates = lots.values().stream()
                .filter(l -> l.getSku().equals(sku) && l.isAvailable()).toList();
        Lot chosen = policies.pickingFor(sku).pick(candidates)
                .orElseThrow(() -> new NoSuchElementException("Sin stock disponible para " + sku));
        change(chosen, chosen::reserve, "reservado " + chosen.getId() + " (" + policies.pickingFor(sku).name() + ")");
        return chosen;
    }

    private Lot get(String id) {
        Lot l = lots.get(id);
        if (l == null) throw new NoSuchElementException("Lote inexistente: " + id);
        return l;
    }
}
