package pedidosrest.repository;

import org.springframework.data.repository.CrudRepository;
import pedidosrest.entity.Endereco;

public interface EnderecoRepository extends CrudRepository<Endereco, Long> {
}
