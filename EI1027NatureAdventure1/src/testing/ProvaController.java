package testing;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei1027.clubesportiu.atleta.Atleta;

@Controller
public class ProvaController {
	
	private Atleta atleta;
	
	public void setAtleta(Atleta atleta) {
               this.atleta = atleta;
          }

	@RequestMapping("/prova_id")
	public String provaID(Model model) {
             model.addAttribute("message", 
                                "L'atleta necessita " + atleta.getFacility());
  		return "prova";
	}

	@RequestMapping("/prueba")
	public String provaWeb(Model model ) {
		String message = "probando web";
		model.addAttribute("message", message);
		return "prueba";
	}
}