package com.logiflow.model.shipment;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class Shipment {

    private final String id;
    private final Location origin;
    private final Location destination;
    private ShipmentStatus status;

    public Shipment(String id, Location origin, Location destination) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id is required");
        }
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.status = ShipmentStatus.IN_TRANSIT;
    }

    public Shipment(String id, Location origin, Location destination, ShipmentStatus status) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
    }

    public void deliver() {
        if (status != ShipmentStatus.IN_TRANSIT) {
            throw new IllegalStateException("Only shipments in transit can be delivered");
        }
        this.status = ShipmentStatus.DELIVERED;
    }

    public void markIncident() {
        if (status == ShipmentStatus.DELIVERED) {
            throw new IllegalStateException("Delivered shipment cannot have incidents");
        }
        this.status = ShipmentStatus.INCIDENT;
    }
}