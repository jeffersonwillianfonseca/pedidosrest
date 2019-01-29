package pedidosrest.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import pedidosrest.entity.Endereco;
import pedidosrest.repository.EnderecoRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static pedidosrest.TestBuilders.buildNovoPedido;

public class EntregasServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EntregasService entregasService;

    @Captor
    private ArgumentCaptor<Endereco> enderecoCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        entregasService = new EntregasService(enderecoRepository);
    }

    @Test
    public void enderecosEntregaReceiver_PedidoOk_DeveSalvarEndereco() {
        entregasService.enderecosEntregaReceiver(buildNovoPedido(true));
        verify(enderecoRepository).save(enderecoCaptor.capture());
        assertEquals(enderecoCaptor.getValue().getEndereco(), buildNovoPedido(true).getEndereco());
        assertEquals(enderecoCaptor.getValue().getIdPedido(), buildNovoPedido(true).getIdPedido());
    }

    @Test
    public void enderecosEntregaReceiver_PedidoInvalido_NaoDeveSalvarEndereco() {
        entregasService.enderecosEntregaReceiver(buildNovoPedido(false));
        verify(enderecoRepository, never()).save(any());
    }
}