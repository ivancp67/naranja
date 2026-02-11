package mondapiBD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import mondapiBD.exception.ConflictException;
import mondapiBD.model.Empresa;
import mondapiBD.model.TutorDocente;
import mondapiBD.model.Usuario;
import mondapiBD.repository.EmpresaRepository;
import mondapiBD.repository.TutorDocenteRepository;
import mondapiBD.repository.UsuarioRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private TutorDocenteRepository tutorRepository;

	@Override
	public Usuario crearUsuario(Usuario usuario) throws ConflictException {
		// El nombre de usuario debe ser único [6]
		if (usuarioRepository.existsByUsername(usuario.getUsername())) {
			throw new ConflictException("El nombre de usuario ya existe");
		}
		usuario.setActivo(true); // Activo por defecto [6]
		return usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public void alternarEstadoUsuario(String usuarioId, boolean activo) {
		Usuario user = usuarioRepository.findById(usuarioId).get();
		user.setActivo(activo);
		usuarioRepository.save(user);
	}

	@Override
	public List<Empresa> listarEmpresas() {
		// Ordenado alfabéticamente por nombre de empresa [7]
		return empresaRepository.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
	}

	@Override
	public List<TutorDocente> listarTutores() {
		// Ordenado alfabéticamente por nombre del tutor [8]
		return tutorRepository.findAll(Sort.by(Sort.Direction.ASC, "nombreCompleto"));
	}

	// Otros métodos de guardado estándar...
	public Empresa guardarEmpresa(Empresa e) {
		return empresaRepository.save(e);
	}

	public TutorDocente guardarTutor(TutorDocente t) {
		return tutorRepository.save(t);
	}
}
