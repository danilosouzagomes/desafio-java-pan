package com.bancopan.address.rest.client.ibge;

import com.bancopan.address.rest.client.exception.ErroConsultaClientException;
import com.bancopan.address.rest.client.ibge.dto.EstadoDTO;
import com.bancopan.address.rest.client.ibge.dto.MunicipioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class IbgeRestClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URI = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
    private static final String PATH_MUNICIPIOS = "/{UF}/municipios";

    public EstadoDTO[] obterEstados() throws ErroConsultaClientException {

        EstadoDTO[] estados = new EstadoDTO[0];

        try {
            estados = restTemplate.getForObject(BASE_URI, EstadoDTO[].class);
        } catch (RestClientException e) {
            throw new ErroConsultaClientException(this, e);
        }

        return estados;
    }

    public MunicipioDTO[] obterMunicipios(int uf) throws ErroConsultaClientException {

        Map<String, Integer> parametros = new HashMap<>();
        parametros.put("UF", uf);

        MunicipioDTO[] municipios = new MunicipioDTO[0];

        try {
            municipios = restTemplate.getForObject(BASE_URI + PATH_MUNICIPIOS, MunicipioDTO[].class, parametros);
        } catch (RestClientException e) {
            throw new ErroConsultaClientException(this, e);
        }

        return municipios;
    }
}
