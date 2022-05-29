package com.skyblue.backend.tracing.repository;

import com.skyblue.backend.tracing.models.Vehicle;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface VehicleRepository  extends ReactiveCrudRepository<Vehicle, Long> {


}
