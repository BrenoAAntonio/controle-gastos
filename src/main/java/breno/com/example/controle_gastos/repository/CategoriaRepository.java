package breno.com.example.controle_gastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import breno.com.example.controle_gastos.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}