package breno.com.example.controle_gastos.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import breno.com.example.controle_gastos.entity.Categoria;
import breno.com.example.controle_gastos.entity.User;
import breno.com.example.controle_gastos.repository.CategoriaRepository;
import breno.com.example.controle_gastos.repository.UserRepository;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UserRepository userRepository;

    private User getLoggedInUser(Principal principal) {
        return userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + principal.getName()));
    }

    @GetMapping
    public String listarCategorias(Model model, Principal principal) {
        User user = getLoggedInUser(principal);
        model.addAttribute("listaCategorias", categoriaRepository.findAllByUser(user));
        return "lista-categorias";
    }

    @GetMapping("/novo")
    public String mostrarFormularioDeNovaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "form-categoria";
    }

    @PostMapping
    public String salvarCategoria(@ModelAttribute("categoria") Categoria categoria, Principal principal) {
        User user = getLoggedInUser(principal);
        categoria.setUser(user);
        categoriaRepository.save(categoria);
        return "redirect:/categorias";
    }
}