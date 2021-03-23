package com.bancopan.address.controller;

import com.bancopan.address.dto.ClienteDTO;
import com.bancopan.address.dto.CriarClienteDTO;
import com.bancopan.address.dto.EnderecoDTO;
import com.bancopan.address.model.Cliente;
import com.bancopan.address.service.IClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@Validated
public class ClientesController {

    private final IClienteService clienteService;

    public ClientesController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteDTO> obterCliente(@Valid @PathVariable(name = "cpf") @NotNull @Size(min = 11, max = 11) String cpf) {

        Optional<Cliente> cliente = this.clienteService.obterCliente(cpf);

        return cliente.map(value -> new ResponseEntity<>(ClienteDTO.toDto(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteDTO> criarCliente(@Valid @RequestBody CriarClienteDTO criarClienteDTO) {
        ClienteDTO clienteDTO = this.clienteService.criarCliente(criarClienteDTO);

        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }

    @PutMapping("/{cpf}/atualizar-endereco")
    @Transactional
    public ResponseEntity<ClienteDTO> atualizarEndereco(@Valid @PathVariable(name = "cpf") @NotNull @Size(min = 11, max = 11) String cpf, @RequestBody EnderecoDTO enderecoDTO) {

        Optional<ClienteDTO> clienteDTO = this.clienteService.atualizarEndereco(cpf, enderecoDTO);

        return clienteDTO.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
