package com.logistpulse.core.state;

/** Desvio: solo puede volver a ALMACENADO tras la liberacion de calidad. */
public final class QuarantineState implements LotState {
    @Override public String name() { return "CUARENTENA"; }
    @Override public LotState releaseQuarantine() { return new StoredState(); }
}
