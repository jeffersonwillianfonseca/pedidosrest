package pedidosrest.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import pedidosrest.entity.Pedidos;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PedidosTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void novoPedido_DeveSerializarNovoPedido() throws JsonProcessingException {
        Pedidos pedido = new Pedidos();
        pedido.setCodCliente(1L);
        pedido.setCodigosProduto(Arrays.asList(1l, 33l, 45l));
        pedido.setValorTotal(245.00);

        String pedidoSerializado = objectMapper.writeValueAsString(pedido);
        assertEquals(pedidoSerializado, "{\"idPedido\":null," +
                "\"codCliente\":1,\"codigosProduto\":[1,33,45]," +
                "\"valorTotal\":245.0,\"endereco\":null}");
    }

    @Test
    public void novoPedido_DeveDesserializarNovoPedido() throws IOException {
        Pedidos pedidos = objectMapper.readValue("{\"idPedido\":null," +
                        "\"codCliente\":1,\"codigosProduto\":[1,33,45]," +
                        "\"valorTotal\":245.0,\"endereco\":\"Filipinas 34 Londrina\"}",
                Pedidos.class);
        assertNull(pedidos.getIdPedido());
        assertEquals(pedidos.getEndereco(), "Filipinas 34 Londrina");
        assertEquals(1L, (long) pedidos.getCodCliente());
    }
}
