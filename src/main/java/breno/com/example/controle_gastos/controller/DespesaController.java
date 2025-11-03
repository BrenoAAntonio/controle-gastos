package breno.com.example.controle_gastos.controller;

// IMPORTS QUE ESTAVAM FALTANDO OU PRECISAVAM SER VERIFICADOS
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import breno.com.example.controle_gastos.entity.Categoria;
import breno.com.example.controle_gastos.entity.Despesa;
import breno.com.example.controle_gastos.repository.CategoriaRepository;
import breno.com.example.controle_gastos.repository.DespesaRepository;
// FIM DOS IMPORTS

@Controller
public class DespesaController {

    @Autowired
    private DespesaRepository despesaRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/despesas/novo")
    public String mostrarFormularioDeNovaDespesa(Model model) {
        List<Categoria> categorias = categoriaRepository.findAll();
        
        model.addAttribute("todasCategorias", categorias);
        model.addAttribute("despesa", new Despesa());
        
        return "form-despesa";
    }

    @PostMapping("/despesas")
    public String salvarDespesa(@ModelAttribute("despesa") Despesa despesa) {
        despesaRepository.save(despesa);
        return "redirect:/despesas";
    }

    @GetMapping("/despesas")
    public String listarDespesas(Model model) {
        model.addAttribute("listaDespesas", despesaRepository.findAll());
        return "lista-despesas";
    }
}