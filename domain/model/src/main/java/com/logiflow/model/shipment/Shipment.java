package com.logiflow.model.shipment;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Shipment {
    private final String id;
    private ShipmentStatus status;

    public Shipment(String id) {
        this.id = id;
        this.status = ShipmentStatus.CREATED;
    }

    public void dispatch() {
        if (status != ShipmentStatus.CREATED) {
            throw new IllegalStateException("Shipment can only be dispatched from CREATED");
        }
        this.status = ShipmentStatus.IN_TRANSIT;
    }

    public void deliver() {
        if (status != ShipmentStatus.IN_TRANSIT) {
            throw new IllegalStateException("Shipment can only be delivered from IN_TRANSIT");
        }
        this.status = ShipmentStatus.DELIVERED;
    }

    public void markException() {
        if (status == ShipmentStatus.DELIVERED) {
            throw new IllegalStateException("Delivered shipment cannot change state");
        }
        this.status = ShipmentStatus.EXCEPTION;
    }
}
