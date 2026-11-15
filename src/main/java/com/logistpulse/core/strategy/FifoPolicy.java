package com.logistpulse.core.strategy;

import com.logistpulse.core.state.Lot;
import java.util.*;

/** First In, First Out: sale primero lo que llego antes. */
public class FifoPolicy implements PickingPolicy {
    @Override public String name() { return "FIFO"; }
    @Override public Optional<Lot> pick(List<Lot> lots) {
        return lots.stream().min(Comparator.comparingLong(Lot::getReceivedSeq));
    }
}
