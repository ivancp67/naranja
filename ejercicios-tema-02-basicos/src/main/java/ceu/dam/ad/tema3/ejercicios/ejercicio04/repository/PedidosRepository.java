package ceu.dam.ad.tema3.ejercicios.ejercicio04.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.tema3.ejercicios.ejercicio04.modelo.Pedido;
import java.util.List;


@Repository
public interface PedidosRepository extends JpaRepository<Pedido, Long>{
	List<Pedido> findByIdPedido(Long idPedido);
}
