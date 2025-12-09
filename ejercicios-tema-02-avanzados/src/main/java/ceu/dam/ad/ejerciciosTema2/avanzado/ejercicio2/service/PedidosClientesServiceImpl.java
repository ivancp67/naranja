package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.Articulo;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.Cliente;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.Pedido;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.PedidoLinea;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.repository.ArticulosRepository;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.repository.ClientesRepository;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.repository.PedidosRepository;
import jakarta.transaction.Transactional;

@Service
public class PedidosClientesServiceImpl implements PedidosClientesService {

	@Autowired
	private ClientesRepository repoCliente;
	@Autowired
	private PedidosRepository repoPedido;
	@Autowired
	private ArticulosRepository repoArticulo;
	
	/** Debe crear en bbdd el cliente indicado. Si el cliente incorpora una lista de pedidos,
	 * estos no deben de registrarse en la BBDD. 	 */
	@Override
	public void crearCliente(Cliente cliente) throws PedidosClientesServiceException {
		try {
			repoCliente.save(cliente);
		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException("Error guardando cliente", e);
		}
	}

	/** Debe crear en bbdd el pedido indicado. En el pedido, el cliente tiene que estar
	 * previamente registrado, así como los artículos de las líneas. Al guardar el pedido, 
	 * guardaremos también todas sus líneas. Devolverá el pedido registrado completo.
	 * IMPORTANTE: todas las l�neas del pedido habrá que inicializarlas con su n�mero
	 * de línea antes de guardarlas. Se inicializarán comenzando en 1 */
	@Override
	@Transactional
	public Pedido crearPedido(Pedido pedido) throws PedidosClientesServiceException {
		try {
			if (repoCliente.findById(pedido.getCliente().getDni()).isEmpty()) {
				throw new PedidosClientesServiceException("No existe el cliente del pedido");
			}
			
			Integer num = 1;
			for (PedidoLinea linea : pedido.getLineas()) {
				if (repoArticulo.findById(linea.getArticulo().getId()).isEmpty()){
					throw new PedidosClientesServiceException("No existe el artículo del pedido");
				}
				linea.setNumLinea(num);
				num++;
			}
			
			return repoPedido.save(pedido);
		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException("Error guardando cliente", e);
		}
	}

	/** Debe crear en bbdd el artículo indicado. Devolverá el articulo registrado completo. */
	@Override
	public Articulo crearArticulo(Articulo articulo) throws PedidosClientesServiceException {
		try {
			return repoArticulo.save(articulo);
		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException("Error guardando artículo", e);
		}
	}
	

	/** Actualizará los datos del cliente indicado en BBDD. Sólo se actualizarán los datos
	 * de esta entidad, no de sus pedidos.	 */
	@Override
	public void actualizarCliente(Cliente cliente) throws PedidosClientesServiceException {
		try {
			if (repoCliente.findById(cliente.getDni()).isEmpty()) {
				throw new PedidosClientesServiceException("El cliente indicado no existe");
			}
			repoCliente.save(cliente);
		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException("Error actualizando cliente", e);
		}
	}

	/** Consulta el cliente con el DNI indicado en BBDD. Si no existe, lanza NotFoundException
	 * Si existe, devolverá dicho cliente con todos sus pedidos cargados.
	 */
	@Override
	public Cliente consultarCliente(String dni) throws NotFoundException, PedidosClientesServiceException {
		try {
			return repoCliente.findById(dni).orElseThrow(()-> new NotFoundException("No existe cliente con ese dni"));
		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException("Error actualizando cliente", e);
		}
	}

	/** Consulta el articulo con el ID indicado en BBDD. Si no existe, lanza NotFoundException
	 * Si existe, devolverá dicho artículo.	 */
	@Override
	public Articulo consultarArticulo(Long idArticulo) throws NotFoundException, PedidosClientesServiceException {
		
		return repoArticulo.findById(idArticulo).orElseThrow(()-> new NotFoundException("No existe artículo con ese id"));
	}

	/** Consulta el pedido con el uuid indicado. Si no existe, lanzará NotFoundExcepion.
	 * El pedido devuelto estará completo: con todas sus líneas, artículos y cliente.	 */
	@Override
	public Pedido consultarPedido(String uuid) throws NotFoundException, PedidosClientesServiceException {
		UUID uuidPedido = UUID.fromString(uuid);
		Optional<Pedido> pedidoOpt = repoPedido.findById(uuidPedido);
		if (pedidoOpt.isEmpty()) {
			throw new NotFoundException("El artículo dado no existe: " + uuid);
		}
		return pedidoOpt.get();
	}
	
	
}
