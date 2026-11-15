package com.logistpulse.core.state;

public final class ReservedState implements LotState {
    @Override public String name() { return "RESERVADO"; }
    @Override public LotState ship() { return new ShippedState(); }
    @Override public LotState store() { return new StoredState(); } // liberar reserva
}
