package com.bancopan.address.controller;

import com.bancopan.address.rest.client.ibge.dto.EstadoDTO;
import com.bancopan.address.rest.client.ibge.dto.MunicipioDTO;
import com.bancopan.address.rest.client.viacep.dto.EnderecoViaCepDTO;
import com.bancopan.address.service.IEnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecosController {

    private final IEnderecoService enderecoService;

    public EnderecosController(IEnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/consulta-cep/{cep}")
    public ResponseEntity<EnderecoViaCepDTO> consultarCep(@Valid @PathVariable(name = "cep") @Size(min = 8, max = 8) String cep) {
        return new ResponseEntity<>(enderecoService.consultarCep(cep), HttpStatus.OK);
    }

    @GetMapping("/consulta-estados")
    public ResponseEntity<EstadoDTO[]> consultarEstados() {
        return new ResponseEntity<>(enderecoService.consultaEstados(), HttpStatus.OK);
    }

    @GetMapping("/consulta-municipios/{uf}")
    public ResponseEntity<MunicipioDTO[]> consultarMunicipios(@PathVariable(name = "uf") int uf) {
        return new ResponseEntity<>(enderecoService.consultaMunicipios(uf), HttpStatus.OK);
    }
}
