package pedidosrest.repository;

import org.springframework.data.repository.CrudRepository;
import pedidosrest.entity.Pedidos;

public interface PedidosRepository extends CrudRepository<Pedidos, Long> {

}
