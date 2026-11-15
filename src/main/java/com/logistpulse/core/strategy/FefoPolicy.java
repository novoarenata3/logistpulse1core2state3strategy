package com.logistpulse.core.strategy;

import com.logistpulse.core.state.Lot;
import java.util.*;

/** First Expired, First Out: sale primero lo que vence antes. */
public class FefoPolicy implements PickingPolicy {
    @Override public String name() { return "FEFO"; }
    @Override public Optional<Lot> pick(List<Lot> lots) {
        return lots.stream().min(Comparator.comparing(Lot::getExpiry));
    }
}
