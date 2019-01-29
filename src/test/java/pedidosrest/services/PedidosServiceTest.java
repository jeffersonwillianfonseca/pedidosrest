package pedidosrest.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pedidosrest.entity.Pedidos;
import pedidosrest.repository.PedidosRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static pedidosrest.TestBuilders.buildNovoPedido;

public class PedidosServiceTest {

    @Mock
    PedidosRepository pedidosRepository;

    @Mock
    RabbitTemplate rabbitTemplate;

    @Autowired
    PedidosService pedidosService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        pedidosService = new PedidosService(pedidosRepository, rabbitTemplate);
    }

    @Test
    public void salvarPedido_PedidoCorreto_SalvaPedidoEEnviaParaFilaDeEntrega() {
        Pedidos pedido = buildNovoPedido(false);
        when(pedidosRepository.save(any())).thenReturn(pedido);
        pedidosService.salvarPedido(pedido);
        verify(rabbitTemplate).convertAndSend(any(), any(), any(Pedidos.class));
    }

    @Test
    public void salvarPedido_FalhaAoSalvarPedido_SalvaPedidoEEnviaParaFilaDeEntrega() {
        when(pedidosRepository.save(any())).thenReturn(null);
        pedidosService.salvarPedido(buildNovoPedido(false));
        verify(rabbitTemplate, never()).convertAndSend(any(), any(), any(Pedidos.class));
    }
}