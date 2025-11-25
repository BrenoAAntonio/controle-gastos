package breno.com.example.controle_gastos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import breno.com.example.controle_gastos.entity.Despesa;
import breno.com.example.controle_gastos.entity.User;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    List<Despesa> findAllByUser(User user);

}