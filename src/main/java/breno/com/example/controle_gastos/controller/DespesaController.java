package breno.com.example.controle_gastos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import breno.com.example.controle_gastos.entity.Despesa;
import breno.com.example.controle_gastos.repository.DespesaRepository;

@Controller
public class DespesaController {

    @Autowired
    private DespesaRepository despesaRepository;

    @GetMapping("/despesas/novo")
    public String mostrarFormularioDeNovaDespesa(Model model) {
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