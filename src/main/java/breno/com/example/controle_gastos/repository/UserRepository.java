package breno.com.example.controle_gastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import breno.com.example.controle_gastos.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}