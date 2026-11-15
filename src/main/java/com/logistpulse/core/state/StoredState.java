package com.logistpulse.core.state;

public final class StoredState implements LotState {
    @Override public String name() { return "ALMACENADO"; }
    @Override public LotState reserve() { return new ReservedState(); }
    @Override public LotState quarantine() { return new QuarantineState(); }
    @Override public boolean isAvailable() { return true; }
}
