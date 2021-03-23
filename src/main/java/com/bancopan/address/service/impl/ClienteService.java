package com.bancopan.address.service.impl;

import com.bancopan.address.dto.ClienteDTO;
import com.bancopan.address.dto.CriarClienteDTO;
import com.bancopan.address.dto.EnderecoDTO;
import com.bancopan.address.model.Cliente;
import com.bancopan.address.model.Endereco;
import com.bancopan.address.repository.ClienteRepository;
import com.bancopan.address.service.IClienteService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository repository) {
        this.clienteRepository = repository;
    }

    @Override
    public Optional<Cliente> obterCliente(String cpf) {
        return this.clienteRepository.findByCpf(cpf);
    }

    @Override
    public Optional<ClienteDTO> atualizarEndereco(String cpf, EnderecoDTO enderecoDTO) {
        return this.verificarCliente(cpf, enderecoDTO);
    }

    @Override
    public ClienteDTO criarCliente(CriarClienteDTO criarClienteDTO) {
        return ClienteDTO.toDto(this.clienteRepository.save(criarClienteDTO.getCliente()));
    }

    private Optional<ClienteDTO> verificarCliente(String cpf, EnderecoDTO enderecoDTO) {
        Optional<Cliente> clienteOptional = obterCliente(cpf);

        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();

            Endereco endereco = getPreencherEndereco(enderecoDTO, cliente);

            cliente.setEndereco(endereco);

            return Optional.of(ClienteDTO.toDto(clienteRepository.save(cliente)));
        } else {
            return Optional.empty();
        }
    }

    private Endereco getPreencherEndereco(EnderecoDTO enderecoDTO, Cliente cliente) {
        Endereco endereco;

        if (cliente.getEndereco() == null) {
            endereco = new Endereco();
        } else {
            endereco = cliente.getEndereco();
        }

        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setMunicipio(enderecoDTO.getMunicipio());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setUf(enderecoDTO.getUf());
        endereco.setCliente(cliente);

        return endereco;
    }
}
