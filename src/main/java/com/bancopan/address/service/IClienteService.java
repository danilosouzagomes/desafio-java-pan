package com.bancopan.address.service;

import com.bancopan.address.dto.ClienteDTO;
import com.bancopan.address.dto.CriarClienteDTO;
import com.bancopan.address.dto.EnderecoDTO;
import com.bancopan.address.model.Cliente;

import java.util.Optional;

public interface IClienteService {

    Optional<Cliente> obterCliente(String cpf);
    Optional<ClienteDTO> atualizarEndereco(String cpf, EnderecoDTO enderecoDTO);
    ClienteDTO criarCliente(CriarClienteDTO criarClienteDTO);
}
