package com.bancopan.address.service;

import com.bancopan.address.dto.ClienteDTO;
import com.bancopan.address.dto.CriarClienteDTO;
import com.bancopan.address.dto.EnderecoDTO;
import com.bancopan.address.model.Cliente;
import com.bancopan.address.model.Endereco;
import com.bancopan.address.repository.ClienteRepository;
import com.bancopan.address.service.impl.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void alterarEnderecoClienteNaoEncontrado() {
        String cpf = "11122233344";
        String nome = "Cliente 1";
        EnderecoDTO enderecoDTO = new EnderecoDTO("11222333", "Rua Alexandre Herculano", "11", "Casa", "Boqueirão", "Santos", "SP");

        when(clienteRepository.findByCpf(Mockito.anyString())).thenReturn(Optional.empty());

        Optional<ClienteDTO> clienteDTO = clienteService.atualizarEndereco(cpf, enderecoDTO);

        Assertions.assertEquals(Optional.empty(), clienteDTO);
    }

    @Test
    void alterarClienteEncontradoComEndereco() {
        String cpf = "11122233344";
        String nome = "Cliente 1";

        String cepAntigo = "11222333";
        String cepNovo = "11222444";
        String logradouro = "Rua Alexandre Herculano";
        String numero = "11";
        String complemento = "Casa";
        String bairro = "Boqueirão";
        String municipio = "Santos";
        String uf = "SP";

        EnderecoDTO enderecoDTO = new EnderecoDTO(cepNovo, logradouro, numero, complemento, bairro, municipio, uf);

        Cliente clienteSemEndereco = new Cliente(cpf, nome);

        Endereco enderecoAntigo = new Endereco(cepAntigo, logradouro, numero, complemento, bairro, municipio, uf);
        Cliente clienteEnderecoAntigo = new Cliente((long) 1, cpf, nome, enderecoAntigo);

        Endereco enderecoNovo = new Endereco(cepNovo, logradouro, numero, complemento, bairro, municipio, uf);
        Cliente clienteEnderecoNovo = new Cliente((long) 1, cpf, nome, enderecoNovo);

        when(clienteRepository.findByCpf(Mockito.anyString())).thenReturn(Optional.of(clienteEnderecoAntigo));
        when(clienteRepository.save(Mockito.any())).thenReturn(clienteEnderecoNovo);

        Optional<ClienteDTO> clienteDTO = clienteService.atualizarEndereco(cpf, enderecoDTO);

        Assertions.assertNotNull(clienteDTO.get().getEnderecoDTO());
        Assertions.assertEquals(enderecoDTO.getCep(), clienteDTO.get().getEnderecoDTO().getCep());
    }

    @Test
    void alterarClienteEncontradoSemEndereco() {
        String cpf = "11122233344";
        String nome = "Cliente 1";

        String cep = "11222333";
        String logradouro = "Rua Alexandre Herculano";
        String numero = "11";
        String complemento = "Casa";
        String bairro = "Boqueirão";
        String municipio = "Santos";
        String uf = "SP";

        EnderecoDTO enderecoDTO = new EnderecoDTO(cep, logradouro, numero, complemento, bairro, municipio, uf);

        Cliente clienteSemEndereco = new Cliente(cpf, nome);

        Endereco endereco = new Endereco(cep, logradouro, numero, complemento, bairro, municipio, uf);
        Cliente clienteComEndereco = new Cliente((long) 1, cpf, nome, endereco);

        when(clienteRepository.findByCpf(Mockito.anyString())).thenReturn(Optional.of(clienteSemEndereco));
        when(clienteRepository.save(Mockito.any())).thenReturn(clienteComEndereco);

        Optional<ClienteDTO> clienteDTO = clienteService.atualizarEndereco(cpf, enderecoDTO);

        Assertions.assertNotNull(clienteDTO.get().getEnderecoDTO());
        Assertions.assertEquals(enderecoDTO.getCep(), clienteDTO.get().getEnderecoDTO().getCep());
    }

    @Test
    void clienteCriado() {
        String cpf = "11122233344";
        String nome = "Cliente 1";

        CriarClienteDTO clienteDTO = new CriarClienteDTO();
        clienteDTO.setCpf(cpf);
        clienteDTO.setNome(nome);

        Cliente clienteSalvo = new Cliente(cpf, nome);

        when(clienteRepository.save(Mockito.any())).thenReturn(clienteSalvo);

        ClienteDTO clienteRetorno = clienteService.criarCliente(clienteDTO);

        verify(clienteRepository).save(Mockito.any());
        Assertions.assertEquals(ClienteDTO.toDto(clienteSalvo).getCpf(), clienteRetorno.getCpf());
        Assertions.assertEquals(ClienteDTO.toDto(clienteSalvo).getNome(), clienteRetorno.getNome());
    }

    @Test
    void clienteEncontrado() {
        String cpf = "11122233344";
        Cliente cliente = new Cliente(Long.parseLong("1"), cpf, "Cliente 1");
        Endereco endereco = new Endereco(Long.parseLong("1"), "11222333", "Rua X", "12", "Casa", "Vila Um", "Cidade Nova", "São Paulo");

        cliente.setEndereco(endereco);
        endereco.setCliente(cliente);

        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.of(cliente));

        Optional<Cliente> clienteService = this.clienteService.obterCliente(cpf);

        Assertions.assertEquals(cliente, clienteService.get());
    }

    @Test
    void clienteNaoEncontrado() {
        String cpf = "11122233344";

        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        Optional<Cliente> clienteService = this.clienteService.obterCliente(cpf);

        Assertions.assertEquals(Optional.empty(), clienteService);
    }
}
