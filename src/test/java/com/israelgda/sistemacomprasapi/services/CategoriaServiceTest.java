package com.israelgda.sistemacomprasapi.services;

import com.israelgda.sistemacomprasapi.dto.CategoriaDTO;
import com.israelgda.sistemacomprasapi.entities.Categoria;
import com.israelgda.sistemacomprasapi.repositories.CategoriaRepository;
import com.israelgda.sistemacomprasapi.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    private Categoria categoria;
    private Categoria categoriaAtualizada;
    private CategoriaDTO categoriaDTO;
    private CategoriaDTO categoriaAtualizadaDTO;
    private Long existingId;
    private Long notExistingId;
    private List<Categoria> list;

    @BeforeEach
    void setUp(){
        existingId = 1L;
        notExistingId = 999L;
        categoria = Categoria.builder().id(1L).nome("Jogos").build();
        categoriaDTO = CategoriaDTO.builder().id(1L).nome("Jogos").build();
        categoriaAtualizada = Categoria.builder().id(1L).nome("Games").build();
        categoriaAtualizadaDTO = CategoriaDTO.builder().id(1L).nome("Games").build();
        list = new ArrayList(Arrays.asList(new Categoria().builder().id(1L).nome("Eletrônicos").build(),
                                            new Categoria().builder().id(2L).nome("Livros").build(),
                                            new Categoria().builder().id(3L).nome("Yoga").build()));
    }

    @Test
    public void findAllDeveRetornarListaOrdenadaPorNome(){
        when(categoriaRepository.findAllByOrderByNome()).thenReturn(list);

        List<CategoriaDTO> result = categoriaService.findAll();

        assertNotNull(result);
        assertEquals("Eletrônicos", result.get(0).getNome());
        assertEquals("Livros", result.get(1).getNome());
        assertEquals("Yoga", result.get(2).getNome());

    }

    @Test
    public void createDeveRetornarCategoriaQuandoNomeValido(){
        when(categoriaRepository.save(any())).thenReturn(categoria);

        CategoriaDTO result = categoriaService.create(categoriaDTO);

        assertNotNull(result);
        assertEquals(1l, result.getId());
        assertEquals("Jogos", result.getNome());
    }

    @Test
    public void updateDeveAtualizarCategoriaQuandoIdExistir(){
        when(categoriaRepository.findById(existingId)).thenReturn(Optional.of(categoriaAtualizada));
        when(categoriaRepository.save(categoriaAtualizada)).thenReturn(categoriaAtualizada);

        CategoriaDTO result = categoriaService.update(existingId, categoriaAtualizadaDTO);

        assertNotNull(result);
        assertEquals(existingId, result.getId());
        assertEquals("Games", result.getNome());
    }

    @Test
    public void updateDeveLancarResourceNotFoundExceptionQuandoIdNaoExistir(){
        when(categoriaRepository.findById(notExistingId)).thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, ()-> {
            categoriaService.update(notExistingId, categoriaAtualizadaDTO);
        });
    }

    @Test
    public void deleteDeveApagarCategoriaQuandoIdExistir(){
        doNothing().when(categoriaRepository).deleteById(existingId);

        assertDoesNotThrow(()-> {
            categoriaService.delete(existingId);
        });
    }

    @Test
    public void deleteDeveLancarResourceNotFoundExceptionQuandoIdNaoExistir(){
        doThrow(ResourceNotFoundException.class).when(categoriaRepository).deleteById(notExistingId);

        assertThrows(ResourceNotFoundException.class ,()-> {
            categoriaService.delete(notExistingId);
        });
    }


}
