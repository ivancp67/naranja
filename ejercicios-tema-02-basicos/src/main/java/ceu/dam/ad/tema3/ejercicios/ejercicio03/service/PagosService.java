package ceu.dam.ad.tema3.ejercicios.ejercicio03.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.tema3.ejercicios.ejercicio02.model.Cliente;
import ceu.dam.ad.tema3.ejercicios.ejercicio02.repository.ClientesRepository;
import ceu.dam.ad.tema3.ejercicios.ejercicio03.model.Pago;
import ceu.dam.ad.tema3.ejercicios.ejercicio03.repository.PagosRepository;

@Service
public class PagosService {
	@Autowired
	private ClientesRepository repoCliente;
	@Autowired
	private PagosRepository repoPago;
	
	
	public Map<String, List<Pago>> consultarPagosClientes() throws PagosException{
		try {
			Map<String, List<Pago>> mapa = new HashMap<>();
			List<Cliente> clientes = repoCliente.findAll();
			for (Cliente cliente : clientes) {
				List<Pago> pagos = repoPago.findByIdCliente(cliente.getId());
				mapa.put(cliente.getEmail(), pagos);
			}
			return mapa;
		}
		catch(DataAccessException e) {
			throw new PagosException("Error al obtener pagos de cliente", e);
		}
	}

}
