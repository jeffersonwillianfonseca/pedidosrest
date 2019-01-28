package pedidosrest.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "PEDIDOS")
public class Pedidos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    private Long codCliente;

    @ElementCollection(targetClass=Long.class)
    private List<Long> codigosProduto;

    private Double valorTotal;

    private String endereco;

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Long codCliente) {
        this.codCliente = codCliente;
    }

    public List<Long> getCodigosProduto() {
        return codigosProduto;
    }

    public void setCodigosProduto(List<Long> codigosProduto) {
        this.codigosProduto = codigosProduto;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
