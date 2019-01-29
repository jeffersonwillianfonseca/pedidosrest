package pedidosrest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "ENDERECO_ENTREGA")
@Getter @Setter
public class Endereco {

    @Id
    @Column(name = "idEndEntrega")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndEntrega;

    private String endereco;

    private Long idPedido;

}
