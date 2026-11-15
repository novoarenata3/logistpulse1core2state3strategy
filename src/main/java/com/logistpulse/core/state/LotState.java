package com.logistpulse.core.state;

/** Patron State: cada estado del lote es una clase. Por defecto toda transicion es invalida. */
public interface LotState {
    String name();
    default LotState store()             { throw invalid("store"); }
    default LotState reserve()           { throw invalid("reserve"); }
    default LotState ship()              { throw invalid("ship"); }
    default LotState quarantine()        { throw invalid("quarantine"); }
    default LotState releaseQuarantine() { throw invalid("releaseQuarantine"); }
    /** Solo el stock ALMACENADO esta disponible para reservar. */
    default boolean isAvailable() { return false; }

    private IllegalStateException invalid(String action) {
        return new IllegalStateException("Transicion invalida: '" + action + "' desde " + name());
    }
}
