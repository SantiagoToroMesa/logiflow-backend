package com.logiflow.model.shipment.gateways;

import com.logiflow.model.shipment.Shipment;
import reactor.core.publisher.Mono;

public interface ShipmentRepository {

    Mono<Shipment> findById(String shipmentId);

    Mono<Shipment> save(Shipment shipment);
}
