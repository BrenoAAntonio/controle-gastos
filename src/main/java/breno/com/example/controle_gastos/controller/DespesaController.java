package breno.com.example.controle_gastos.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import breno.com.example.controle_gastos.entity.Categoria;
import breno.com.example.controle_gastos.entity.Despesa;
import breno.com.example.controle_gastos.entity.User;
import breno.com.example.controle_gastos.repository.CategoriaRepository;
import breno.com.example.controle_gastos.repository.DespesaRepository;
import breno.com.example.controle_gastos.repository.UserRepository;

@Controller
public class DespesaController {

    @Autowired
    private DespesaRepository despesaRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UserRepository userRepository;

    private User getLoggedInUser(Principal principal) {
        return userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + principal.getName()));
    }

    @GetMapping("/despesas/novo")
    public String mostrarFormularioDeNovaDespesa(Model model, Principal principal) {
        User user = getLoggedInUser(principal);
        List<Categoria> categorias = categoriaRepository.findAllByUser(user);
        model.addAttribute("todasCategorias", categorias);
        model.addAttribute("despesa", new Despesa());
        return "form-despesa";
    }

    @PostMapping("/despesas")
    public String salvarDespesa(@ModelAttribute("despesa") Despesa despesa, Principal principal) {
        User user = getLoggedInUser(principal);
        despesa.setUser(user);
        despesaRepository.save(despesa);
        return "redirect:/despesas";
    }

    @GetMapping("/despesas")
    public String listarDespesas(Model model, Principal principal) {
        User user = getLoggedInUser(principal);
        model.addAttribute("listaDespesas", despesaRepository.findAllByUser(user));
        return "lista-despesas";
    }
}