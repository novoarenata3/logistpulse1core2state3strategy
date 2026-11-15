package com.logistpulse.core.strategy;

import com.logistpulse.core.state.Lot;
import java.util.List;
import java.util.Optional;

/** Strategy 1: que lote se despacha primero. */
public interface PickingPolicy {
    String name();
    /** Elige entre los lotes disponibles de un SKU. */
    Optional<Lot> pick(List<Lot> availableLots);
}
