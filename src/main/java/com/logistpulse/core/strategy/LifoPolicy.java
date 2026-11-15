package com.logistpulse.core.strategy;

import com.logistpulse.core.state.Lot;
import java.util.*;

/** Last In, First Out: sale primero lo que llego despues. */
public class LifoPolicy implements PickingPolicy {
    @Override public String name() { return "LIFO"; }
    @Override public Optional<Lot> pick(List<Lot> lots) {
        return lots.stream().max(Comparator.comparingLong(Lot::getReceivedSeq));
    }
}
