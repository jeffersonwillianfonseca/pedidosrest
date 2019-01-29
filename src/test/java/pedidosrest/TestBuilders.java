package pedidosrest;

import com.fasterxml.jackson.databind.ObjectMapper;
import pedidosrest.entity.Pedidos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class TestBuilders {

    public static Pedidos buildNovoPedido(boolean temIdPedido) {
        Pedidos pedido = new Pedidos();
        if(temIdPedido) pedido.setIdPedido(22l);
        pedido.setCodCliente(1L);
        pedido.setCodigosProduto(asList(1l, 33l, 45l));
        pedido.setValorTotal(245.00);
        pedido.setEndereco("Rua paes leme, 245");
        return pedido;
    }

    public static Iterable<Pedidos> buildIterablePedidos() {
        Iterable<Pedidos> pedidos;
        pedidos = singletonList(buildNovoPedido(true));
        return pedidos;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
