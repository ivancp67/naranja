package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.Pedido;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.service.PedidosClientesServiceException;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo.CentroComercial;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo.Marca;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo.Pais;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo.Tienda;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.repository.CentroComercialRepository;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.repository.MarcaRepository;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.repository.PaisRepository;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.repository.TiendaRepository;
import jakarta.transaction.Transactional;

@Service
public class ComercialServiceImpl implements ComercialService{

	@Autowired
	private PaisRepository repoPais;
	@Autowired
	private MarcaRepository repoMarca;
	@Autowired
	private CentroComercialRepository repoComercial;
	@Autowired
	private TiendaRepository repoTienda;
	
	/** Debe buscar en BBDD todos los países cuya descripción empiece por el filtro indicado.
	 * Si no se encuentra ninguno, devolverá una lista vacía.
	 * Si hay algún error, lanzará ComercialException	 */
	@Override
	@Transactional
	public List<Pais> buscarPaises(String filtro) throws ComercialException {
		try {
			List<Pais> lista = repoPais.findByDescripcionStartingWith(filtro);
			return lista;
		} catch (DataAccessException e) {
			throw new ComercialException("Error buscando países", e);
		}
	}

	/** Debe insertar la Marca recibida en BBDD. No se insertarán sus centros comerciales asociados
	 * ni su país.
	 * Si hay algún error, lanzará ComercialException	 */
	@Override
	public void insertarMarca(Marca marca) throws ComercialException {
		try {
			if (repoMarca.findById(marca.getCodigo()).isPresent()) {
				throw new ComercialException("La marca indicada ya existe");
			}
			repoMarca.save(marca);
		} catch (DataAccessException e) {
			throw new ComercialException("Error insertando marca", e);
		}
	}

	/** Debe insertar el Centro Comercial recibido en BBDD. No se insertará su país ni marcas
	 * en ambos casos deben existir previamente. Pero sí se insertará su lista de tiendas.
	 * Si hay algún error, lanzará ComercialException	 */
	@Override
	@Transactional
	public void insertarCentroComercial(CentroComercial cc) throws ComercialException {
		try {
			repoComercial.save(cc);
		} catch (DataAccessException e) {
			throw new ComercialException("Error insertando centro comercial", e);
		}
	}

	/** Consultará un centro comercial a partir de su uuid. Si no existe, lanzará NotFoundException.
	 * La entidad consultada tendrá que traer su país, sus tiendas y sus marcas. 
	 * Si hay algún error, lanzará ComercialException	 */
	@Override
	public CentroComercial consultarCentroComercial(String uuidCentro) throws ComercialException, NotFoundException {
		try {
			UUID uuidComercial = UUID.fromString(uuidCentro);
			return repoComercial.findById(uuidComercial).orElseThrow(()-> new NotFoundException("No hay centros con ese uuid"));
		} catch (DataAccessException e) {
			throw new ComercialException("Error consultando centros comerciales", e);
		}
		
	}

	/** Consultará una tienda a partir de su id. Si no existe, lanzará NotFoundException.
	 * La entidad consultada no traerá ninguna de sus entidades asociadas. 
	 * Si hay algún error, lanzará ComercialException	 */
	@Override
	public Tienda consularTienda(Long idTienda) throws ComercialException, NotFoundException {
		try {
			return repoTienda.findById(idTienda).orElseThrow(()-> new NotFoundException("No existen tiendas con ese id"));
		} catch (DataAccessException e) {
			throw new ComercialException("Error consultando tiendas", e);
		}
		
	}

	/** Debe borrar la tienda con el id indicado. Sólo borrará la tienda, ninguna
	 * de sus entidades asociadas.
	 * Si hay algún error, lanzará ComercialException	 */
	@Override
	public void borrarTienda(Long idTienda) throws ComercialException {
		try {
			repoTienda.deleteById(idTienda);
		} catch (DataAccessException e) {
			throw new ComercialException("Error borrando tiendas", e);
		}
	}

	/** Debe borrar el centro comercial con el uuid indicado. Borrará también su lista
	 * de tiendas asociada, pero no el resto de entidades.
	 * Si hay algún error, lanzará ComercialException	 */
	@Override
	@Transactional
	public void borrarCentroComercial(String uuidCentro) throws ComercialException {
		try {
			UUID uuidComercial = UUID.fromString(uuidCentro);
			repoComercial.deleteById(uuidComercial);
		} catch (DataAccessException e) {
			throw new ComercialException("Error borrando tiendas", e);
		}
	}

	@Override
	public List<Pedido> consultarPedidosByArticulo(String descripcionArticulo) throws PedidosClientesServiceException, NotFoundException {
		return null;
	}

}
