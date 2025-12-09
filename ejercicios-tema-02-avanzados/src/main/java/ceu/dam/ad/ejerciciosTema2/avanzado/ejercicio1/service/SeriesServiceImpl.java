package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.model.Serie;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.repository.SeriesRepository;
import jakarta.transaction.Transactional;

@Service
public class SeriesServiceImpl implements SeriesService {
	@Autowired
	private SeriesRepository repoSerie;
	/** Consulta la serie con el id indicado por parámetro y la devuelve. Tiene que incluir todas sus entidades 
	 * asociadas. Si la serie no existe, se lanzará SerieNotFoundException. Si hay cualquier otro error, se
	 * lanzará SeriesServiceException
	 */
	@Override
	public Serie consultarSerie(Long idSerie) throws SerieNotFoundException, SeriesServiceException {
		try {
			Optional<Serie> serieOpt = repoSerie.findById(idSerie);
			if (serieOpt.isEmpty()) {
				throw new SerieNotFoundException("La serie no existe:" + idSerie);
			}
			return serieOpt.get();
		} catch (DataAccessException e) {
			throw new SeriesServiceException("Error consultando serie", e);
		}
	}
	
	/** Consultará todas las series que contengan en su descripción la palabra indicada en el filtro por
	 * parámetro. Si no se encuentra ninguna, lanzará SerieNotFoundException. Si hay cualquier otro error, se
	 * lanzará SeriesServiceException
	 */
	@Override
	public List<Serie> buscarSeries(String filtroDescripcion) throws SerieNotFoundException, SeriesServiceException {
		try {
			List<Serie> series = repoSerie.findByDescripcionContaining(filtroDescripcion);
			
			if (series.isEmpty()) {
				throw new SerieNotFoundException("No se encuentra ninguna serie con ese filtro" + filtroDescripcion);
			}
			return series;
		} catch (DataAccessException e) {
			throw new SeriesServiceException("Error creando serie", e);
		}
		
		
	}
	
	/** Debe crear la serie y todas sus entidades asociadas en bbdd. Si hay algún error, lanzará 
	 * SeriesServiceException. 
	 * Devolverá la serie creada con todos sus datos completos.
	 */
	@Override
	@Transactional
	public Serie crearSerie(Serie serie) throws SeriesServiceException {
		try {
			return repoSerie.save(serie);
		} catch (DataAccessException e) {
			throw new SeriesServiceException("Error creando la serie", e);
		}
	}
	
	/** Debe eliminar la serie con el id indicado, y todas sus entidades asociadas de bbdd. 
	 * Si hay algún error, lanzará SeriesServiceException.
	 */
	@Override
	@Transactional
	public void elimnarSerie(Long idSerie) throws SeriesServiceException {
		try {
			repoSerie.deleteById(idSerie);
		} catch (DataAccessException e) {
			throw new SeriesServiceException("Error eliminado la serie", e);
		}
		
	}
	
	/** Actualizará la serie que se pasa por parámetro y todas las entidades asociadas que estén modificadas. 
	 * Si hay algún error, lanzará SeriesServiceException
	 */
	@Override
	@Transactional
	public void actualizarSerie(Serie serie) throws SeriesServiceException {
		try {
			repoSerie.save(serie);
		} catch (DataAccessException e) {
			throw new SeriesServiceException("Error eliminado la serie", e);
		}
	}
	
	
}
