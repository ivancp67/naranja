package ceu.dam.ad.tema3.ejercicios.ejercicio04.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.tema3.ejercicios.ejercicio04.modelo.Pedido;
import ceu.dam.ad.tema3.ejercicios.ejercicio04.modelo.PedidoLinea;
import ceu.dam.ad.tema3.ejercicios.ejercicio04.repository.PedidosLineasRepository;
import ceu.dam.ad.tema3.ejercicios.ejercicio04.repository.PedidosRepository;
import jakarta.transaction.Transactional;

@Service
public class PedidosService {

	@Autowired
	private PedidosRepository repoPedidos;
	@Autowired
	private PedidosLineasRepository repoPedidosLineas;



	public Pedido consultarPedido(Long idPedido) throws PedidoException, PedidoNotFoundException {
		
		try {
			Optional<Pedido> pedidoOpt = repoPedidos.findById(idPedido);
			if (pedidoOpt.isEmpty()) {
				throw new PedidoNotFoundException("No existe pedido con id " + idPedido);
			}
			Pedido pedido = pedidoOpt.get();
			pedido.setLineas(repoPedidosLineas.findByIdPedido(idPedido));
			return pedido;
		}
		catch(DataAccessException e) {
			throw new PedidoException("Error al registrar pedido", e);
		}
	}
	
	@Transactional
	public Long crearPedido(Pedido pedido) throws PedidoException {
		try{
			Pedido guardado = repoPedidos.save(pedido);
			Integer numLinea = 1;
			for (PedidoLinea linea : pedido.getLineas()) {
				linea.setIdPedido(guardado.getIdPedido());
				linea.setNumLinea(numLinea);
				numLinea++;
				repoPedidosLineas.save(linea);
			}
			return guardado.getIdPedido();
		}
		catch(DataAccessException e) {
			throw new PedidoException("Error al registrar pedido", e);
		}
	}
	
}
