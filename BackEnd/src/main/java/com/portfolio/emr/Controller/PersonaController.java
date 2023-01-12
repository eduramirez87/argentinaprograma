package com.portfolio.emr.Controller;

import com.portfolio.emr.Entity.Persona;
import com.portfolio.emr.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class PersonaController {
    @Autowired IPersonaService IpersonaService;
    
    @GetMapping("personas/traer")
    public List<Persona> getPersona(){
        return IpersonaService.getPersona();
    }
    
    @PostMapping("/personas/crear")
    public String createPersona(@RequestBody Persona persona){
    IpersonaService.savePersona(persona);
    return "La persona fue creada correctamente";
    }
    
    @DeleteMapping("/persona/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        IpersonaService.deletePersona (id);
        return "La persona fue eliminada correctamente";
    }


    @PutMapping("/personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
                                @RequestParam("nombre") String nuevoNombre,
                                @RequestParam("apellido") String nuevoApellido,
                                @RequestParam("img") String nuevoImg){
    Persona persona = IpersonaService.findPersona(id);
    persona.setNombre(nuevoNombre);
    persona.setApellido (nuevoApellido);
    persona.setImg(nuevoImg);
    
    IpersonaService.savePersona(persona);
    return persona;
    }
}