package com.bancopan.address.service;

import com.bancopan.address.rest.client.exception.ErroConsultaClientException;
import com.bancopan.address.rest.client.ibge.IbgeRestClient;
import com.bancopan.address.rest.client.ibge.dto.EstadoDTO;
import com.bancopan.address.rest.client.ibge.dto.MunicipioDTO;
import com.bancopan.address.rest.client.viacep.ViaCepRestClient;
import com.bancopan.address.rest.client.viacep.dto.EnderecoViaCepDTO;
import com.bancopan.address.service.impl.EnderecoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

    @Mock
    private ViaCepRestClient viaCepRestClient;

    @Mock
    private IbgeRestClient ibgeRestClient;

    @InjectMocks
    private EnderecoService enderecoService;

    private ErroConsultaClientException getExcecaoTeste() {
        return new ErroConsultaClientException(this, new RestClientException("Teste"));
    }

    @Test
    void erroAoConsultarCep() {

        when(viaCepRestClient.obterCep(Mockito.anyString())).thenThrow(getExcecaoTeste());

        Assertions.assertThrows(
                ErroConsultaClientException.class,
                () -> {enderecoService.consultarCep(Mockito.anyString());});
    }

    @Test
    void erroAoConsultarEstados() {

        when(ibgeRestClient.obterEstados()).thenThrow(getExcecaoTeste());

        Assertions.assertThrows(
                ErroConsultaClientException.class,
                () -> {enderecoService.consultaEstados();});
    }

    @Test
    void erroAoConsultarMunicipios() {
        int uf = 33;

        when(ibgeRestClient.obterMunicipios(Mockito.anyInt())).thenThrow(getExcecaoTeste());

        Assertions.assertThrows(
                ErroConsultaClientException.class,
                () -> {enderecoService.consultaMunicipios(uf);});
    }

    @Test
    void consultarCep() {
        EnderecoViaCepDTO enderecoViaCepDTO = new EnderecoViaCepDTO();
        enderecoViaCepDTO.setCep("11050-030");
        enderecoViaCepDTO.setLogradouro("Rua Alexandre Herculano");
        enderecoViaCepDTO.setComplemento("até 105/106");
        enderecoViaCepDTO.setBairro("Boqueirão");
        enderecoViaCepDTO.setLocalidade("Santos");
        enderecoViaCepDTO.setUf("SP");

        when(viaCepRestClient.obterCep(Mockito.anyString())).thenReturn(enderecoViaCepDTO);

        Assertions.assertEquals(enderecoViaCepDTO, enderecoService.consultarCep(Mockito.anyString()));
    }

    @Test
    void consultarEstados() {

        EstadoDTO[] estados = new EstadoDTO[2];

        EstadoDTO saoPaulo = new EstadoDTO();
        saoPaulo.setId(1);
        saoPaulo.setNome("São Paulo");
        saoPaulo.setSigla("SP");

        EstadoDTO rioDeJaneiro = new EstadoDTO();
        rioDeJaneiro.setId(2);
        rioDeJaneiro.setNome("Rio de Janeiro");
        rioDeJaneiro.setSigla("RJ");

        estados[0] = saoPaulo;
        estados[1] = rioDeJaneiro;

        when(ibgeRestClient.obterEstados()).thenReturn(estados);

        Assertions.assertEquals(estados, enderecoService.consultaEstados());

    }

    @Test
    void consultarMunicipios() {

        MunicipioDTO[] municipios = new MunicipioDTO[2];
        MunicipioDTO santos = new MunicipioDTO();
        santos.setId(1);
        santos.setNome("Santos");

        MunicipioDTO saoPaulo = new MunicipioDTO();
        saoPaulo.setId(2);
        saoPaulo.setNome("São Paulo");

        municipios[0] = santos;
        municipios[1] = saoPaulo;

        when(ibgeRestClient.obterMunicipios(Mockito.anyInt())).thenReturn(municipios);

        Assertions.assertEquals(municipios, enderecoService.consultaMunicipios(Mockito.anyInt()));

    }
}
