package pe.edu.vallegrande.vg_ms_casas.repository;

import pe.edu.vallegrande.vg_ms_casas.model.Inventory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface InventoryRepository extends ReactiveCrudRepository<Inventory, Integer> {
    // Aquí puedes agregar métodos personalizados si los necesitas, por ejemplo:
     Flux<Inventory> findByStatus(String status);
}
