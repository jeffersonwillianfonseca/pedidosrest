package pedidosrest.entity;

import javax.persistence.*;

@Entity(name = "ENDERECO_ENTREGA")
public class Endereco {

    @Id
    @Column(name = "idEndEntrega")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndEntrega;

    private String endereco;

    private Long idPedido;

    public Long getIdEndEntrega() {
        return idEndEntrega;
    }

    public void setIdEndEntrega(Long idEndEntrega) {
        this.idEndEntrega = idEndEntrega;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }
}
