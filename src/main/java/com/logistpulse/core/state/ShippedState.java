package com.logistpulse.core.state;

/** Estado final: no permite ninguna transicion. */
public final class ShippedState implements LotState {
    @Override public String name() { return "ENVIADO"; }
}
