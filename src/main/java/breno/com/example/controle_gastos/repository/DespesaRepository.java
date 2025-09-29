package breno.com.example.controle_gastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import breno.com.example.controle_gastos.entity.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

}