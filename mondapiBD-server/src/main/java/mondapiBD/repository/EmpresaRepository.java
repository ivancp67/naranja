package mondapiBD.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mondapiBD.model.Empresa;

public interface EmpresaRepository extends MongoRepository<Empresa, String> {
    // Las búsquedas por activos y ordenación se pueden manejar con Sort en el Service [6]
}
