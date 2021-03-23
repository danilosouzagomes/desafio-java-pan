package com.bancopan.address.service;

import com.bancopan.address.model.Cliente;
import com.bancopan.address.model.Endereco;
import com.bancopan.address.repository.ClienteRepository;
import com.bancopan.address.service.impl.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void clienteEncontrado() {
        String cpf = "11122233344";
        Cliente cliente = new Cliente(Long.parseLong("1"), cpf, "Cliente 1");
        Endereco endereco = new Endereco(Long.parseLong("1"), "11222333", "Rua X", "12", "Casa", "Vila Um", "Cidade Nova", "São Paulo");

        cliente.setEndereco(endereco);
        endereco.setCliente(cliente);

        BDDMockito.given(clienteRepository.findByCpf(cpf)).willReturn(Optional.of(cliente));

        Optional<Cliente> clienteService = this.clienteService.obterCliente(cpf);

        Assertions.assertEquals(cliente, clienteService.get());
    }

    @Test
    void clienteNaoEncontrado() {
        String cpf = "11122233344";

        BDDMockito.given(clienteRepository.findByCpf(cpf)).willReturn(Optional.empty());

        Optional<Cliente> clienteService = this.clienteService.obterCliente(cpf);

        Assertions.assertEquals(Optional.empty(), clienteService);
    }
}
