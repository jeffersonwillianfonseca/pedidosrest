package pedidosrest.controller;

import org.hibernate.service.spi.InjectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pedidosrest.entity.Pedidos;
import pedidosrest.services.PedidosService;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pedidosrest.TestBuilders.asJsonString;
import static pedidosrest.TestBuilders.buildIterablePedidos;
import static pedidosrest.TestBuilders.buildNovoPedido;

public class PedidosControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PedidosService pedidosService;

    @InjectMocks
    private PedidosController pedidosController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(pedidosController)
                .build();
    }
    @Test
    public void salvarPedido() throws Exception {
        when(pedidosService.salvarPedido(any(Pedidos.class))).thenReturn(true);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
        .post("/pedidos")
        .content(asJsonString(buildNovoPedido(true)))
        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
        .andReturn();
        assertEquals(result.getResponse().getContentAsString(),
                "Inserido com sucesso");
    }

    @Test
    public void listarTodosPedidos() throws Exception {
        when(pedidosService.findAllPedidos()).thenReturn(buildIterablePedidos());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/pedidos"))
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(),
                asJsonString(buildIterablePedidos()));
    }
}