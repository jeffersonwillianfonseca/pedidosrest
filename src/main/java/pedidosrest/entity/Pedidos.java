package pedidosrest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "PEDIDOS")
@Getter @Setter
public class Pedidos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    private Long codCliente;

    @ElementCollection(targetClass = Long.class)
    private List<Long> codigosProduto;

    private Double valorTotal;

    private String endereco;
}
