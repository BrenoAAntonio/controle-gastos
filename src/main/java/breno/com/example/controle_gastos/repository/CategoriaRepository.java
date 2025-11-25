package breno.com.example.controle_gastos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import breno.com.example.controle_gastos.entity.Categoria;
import breno.com.example.controle_gastos.entity.User;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findAllByUser(User user);
    
}