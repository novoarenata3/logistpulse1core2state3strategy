package com.logistpulse.core.state;

public final class ReceivedState implements LotState {
    @Override public String name() { return "RECIBIDO"; }
    @Override public LotState store() { return new StoredState(); }
    @Override public LotState quarantine() { return new QuarantineState(); }
}
