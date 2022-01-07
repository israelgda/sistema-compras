package com.israelgda.sistemacomprasapi.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.israelgda.sistemacomprasapi.dto.CategoriaDTO;
import com.israelgda.sistemacomprasapi.entities.Categoria;
import com.israelgda.sistemacomprasapi.services.CategoriaService;
import com.israelgda.sistemacomprasapi.utils.TokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @MockBean
    private TokenUtil tokenUtil;

    @MockBean
    private CategoriaService categoriaService;

    private Categoria categoria;
    private Categoria categoriaAtualizada;
    private CategoriaDTO categoriaDTO;
    private CategoriaDTO categoriaAtualizadaDTO;
    private Long existingId;
    private Long notExistingId;
    private List<Categoria> list;
    private List<CategoriaDTO> listDTO;
    private String usuarioLogin;
    private String usuarioPassword;
    private String adminLogin;
    private String adminPassword;

    @BeforeEach
    void setUp(){
        existingId = 1L;
        notExistingId = 999L;
        usuarioLogin = "daniel@gmail.com";
        usuarioPassword = "123456";
        adminLogin = "israel@gmail.com";
        adminPassword = "123456";
        categoria = Categoria.builder().id(1L).nome("Jogos").build();
        categoriaDTO = CategoriaDTO.builder().id(1L).nome("Jogos").build();
        categoriaAtualizada = Categoria.builder().id(1L).nome("Games").build();
        categoriaAtualizadaDTO = CategoriaDTO.builder().id(1L).nome("Games").build();
        list = new ArrayList(Arrays.asList(new Categoria().builder().id(1L).nome("Eletrônicos").build(),
                new Categoria().builder().id(2L).nome("Livros").build(),
                new Categoria().builder().id(3L).nome("Yoga").build()));
        listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
    }

    @Test
    public void findAllDeveRetornarListaOrdenadaPorNomeSemAutenticacao() throws Exception {
        when(categoriaService.findAll()).thenReturn(listDTO);

        ResultActions result = mockMvc.perform(get("/categorias")
                                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$[0].id").value("1"));
        result.andExpect(jsonPath("$[0].nome").value("Eletrônicos"));
        result.andExpect(jsonPath("$[1].id").value("2"));
        result.andExpect(jsonPath("$[1].nome").value("Livros"));
        result.andExpect(jsonPath("$[2].id").value("3"));
        result.andExpect(jsonPath("$[2].nome").value("Yoga"));
    }

    @Test
    public void findAllDeveRetornarListaOrdenadaPorNomeQuandoUsuarioAutenticado() throws Exception {
        when(categoriaService.findAll()).thenReturn(listDTO);

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, usuarioLogin, usuarioPassword);

        ResultActions result = mockMvc.perform(get("/categorias")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$[0].id").value("1"));
        result.andExpect(jsonPath("$[0].nome").value("Eletrônicos"));
        result.andExpect(jsonPath("$[1].id").value("2"));
        result.andExpect(jsonPath("$[1].nome").value("Livros"));
        result.andExpect(jsonPath("$[2].id").value("3"));
        result.andExpect(jsonPath("$[2].nome").value("Yoga"));
    }

    @Test
    public void findAllDeveRetornarListaOrdenadaPorNomeQuandoAdminAutenticado() throws Exception {
        when(categoriaService.findAll()).thenReturn(listDTO);

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminLogin, adminPassword);

        ResultActions result = mockMvc.perform(get("/categorias")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$[0].id").value("1"));
        result.andExpect(jsonPath("$[0].nome").value("Eletrônicos"));
        result.andExpect(jsonPath("$[1].id").value("2"));
        result.andExpect(jsonPath("$[1].nome").value("Livros"));
        result.andExpect(jsonPath("$[2].id").value("3"));
        result.andExpect(jsonPath("$[2].nome").value("Yoga"));
    }

    @Test
    public void createDeveRetornarUnauthorizedQuandoSemAutenticacao() throws Exception {
        when(categoriaService.create(any())).thenReturn(categoriaDTO);

        String jsonBody = objectMapper.writeValueAsString(categoriaDTO);

        ResultActions result = mockMvc.perform(post("/categorias")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnauthorized());
    }

    @Test
    public void createDeveRetornarCategoriaDTOQuandoAdminAutenticadoENomeValido() throws Exception {
        when(categoriaService.create(any())).thenReturn(categoriaDTO);

        String jsonBody = objectMapper.writeValueAsString(categoriaDTO);

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminLogin, adminPassword);

        ResultActions result = mockMvc.perform(post("/categorias")
                .header("Authorization", "Bearer " + accessToken)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    public void createDeveRetornarForbiddenQuandoUsuarioAutenticado() throws Exception {
        when(categoriaService.create(any())).thenReturn(categoriaDTO);

        String jsonBody = objectMapper.writeValueAsString(categoriaDTO);

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, usuarioLogin, usuarioPassword);

        ResultActions result = mockMvc.perform(post("/categorias")
                .header("Authorization", "Bearer " + accessToken)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isForbidden());
    }

}
