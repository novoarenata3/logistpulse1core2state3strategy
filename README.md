# LOGISTPULSE · Smart Inventory — Repo 1: State, Strategy, Observer
Java 17, sin dependencias.

| Patron | Donde | Que hace |
|---|---|---|
| **State** | `state/` | El lote recorre Recibido → Almacenado → Reservado → Enviado, con Cuarentena como desvio. Una transicion invalida lanza `IllegalStateException`. |
| **Strategy** | `strategy/` | `PickingPolicy` (FEFO, FIFO, LIFO) y `ReplenishmentPolicy` (punto de reorden, EOQ, Min-Max), elegidas por producto en `PolicyRegistry`. |
| **Observer** | `observer/` | `StockPublisher` publica `StockChanged`; Ledger, Reposicion y PULSEFLEET se suscriben sin que el inventario los conozca. |

`InventoryService` une los tres patrones. `Main` es una demo ejecutable.

## Ejecutar
```bash
mvn -q compile exec:java
# o sin Maven:
mkdir -p out && javac -d out $(find src -name '*.java') && java -cp out com.logistpulse.core.Main
```

> Repo 2 (Command + Chain of Responsibility): `logistpulse-commands-chain`.
