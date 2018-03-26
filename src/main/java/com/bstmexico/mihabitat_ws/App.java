package com.bstmexico.mihabitat_ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bstmexico.mihabitat_ws.dao.DepartamentoDao;
import com.bstmexico.mihabitat_ws.dao.HistoricosDao;
import com.bstmexico.mihabitat_ws.dao.LoginDAO;
import com.bstmexico.mihabitat_ws.model.Departamento;
import com.bstmexico.mihabitat_ws.model.HistorialMensual;
import com.bstmexico.mihabitat_ws.model.PendientesPago;
import com.bstmexico.mihabitat_ws.model.Persona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Hello world!
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class App extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(applicationClass, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<App> applicationClass = App.class;
}


//Controlador login
@RestController
class GreetingController {
	//Se liga el dao que tiene conexion a bdd
	@Autowired
	 private LoginDAO loginDao;
  
	@Autowired
	private DepartamentoDao departamentoDao;
	
	@Autowired 
	private HistoricosDao historicosDao;
	
  @RequestMapping("/login")
  public Map greeting(@RequestParam(value="name") String name, @RequestParam(value="password") String password) throws JSONException {       
  	//se indica para que el dao sepa que datasource usar
  	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
  	loginDao = (LoginDAO) context.getBean("loginDao");
      Persona persona = new Persona();
      persona= loginDao.checkLogin(name, password);
      Map mapa = new HashMap<>();
      mapa.put("persona", persona);
  	return mapa;    
      }
  
  @RequestMapping("/getTorreyEtiquetas")
  public Map getTorre(@RequestParam(value="departamento") String idDepartamento) throws JSONException{
  	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    	departamentoDao = (DepartamentoDao) context.getBean("departamentoDao");
    	Map mapa = new HashMap<>();
    	Departamento lista = departamentoDao.getTorreEtiquetas(idDepartamento);
      mapa.put("departamentos", lista);
  	return mapa;
  }
  
  @RequestMapping("/departamentosUsuarios")
  public Map getDepartamentos(@RequestParam(value="idPersona") String persona) throws JSONException{
    	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    	departamentoDao = (DepartamentoDao) context.getBean("departamentoDao");
      Map mapa = new HashMap<>();
      List<Departamento> lista = departamentoDao.getDepartamentos(persona);
      int i=0;
      while(i<lista.size()) {
      	 int saldoFavor = departamentoDao.getSaldoFavor(Integer.valueOf(lista.get(i).getIdDepartamento()));
      	 lista.get(i).setSaldoFavor(saldoFavor);
      	i++;
      }
      mapa.put("departamentos", lista);
  	return mapa;    
 
  }
  
  @RequestMapping("/getSaldoaFavor")
  public Map getSaldoaFavor(@RequestParam(value="idDepartamento") String idDepartamento) throws JSONException{
  	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    	departamentoDao = (DepartamentoDao) context.getBean("departamentoDao");
    	Map mapa = new HashMap<>();
      int saldoFavor = departamentoDao.getSaldoFavor(Integer.valueOf(idDepartamento));
      mapa.put("saldoFavor", saldoFavor);
  	return mapa;   
  }
  
  @RequestMapping("/getAdeudoTotal")
  public Map getAdeudoTotal(@RequestParam(value="idDepartamento") String idDepartamento) throws JSONException{
  	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    	Map mapa = new HashMap<>();
  	return mapa;   
  }
  
  
  @RequestMapping("/getHistorialMensual")
  public Map getHistorialMensual(@RequestParam(value="idDepartamento") String idDepartamento,
  		@RequestParam(value="fechaInicio", required=false) String fechaInicio, 
  		@RequestParam(value="fechaFin", required=false) String fechaFin) throws JSONException{
  	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    	Map mapa = new HashMap<>();
    	List<HistorialMensual> historico = new ArrayList<HistorialMensual>();

    	mapa.put("movimientos", historico);
  	return mapa;   
  }
  
  
  @RequestMapping("/getPendientesPago")
  public Map getPendientesPago(@RequestParam(value="idDepartamento") String idDepartamento) throws JSONException{
  	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
  	historicosDao = (HistoricosDao) context.getBean("historicosDao");
    	Map mapa = new HashMap<>();
    	List<PendientesPago> pendientespago = historicosDao.getPendientesPago(Integer.valueOf(idDepartamento));
    	mapa.put("pendientes", pendientespago);
  	return mapa;   
  }
  

  @RequestMapping("/getHistorialCargos")
  public Map getHistorialCargos(@RequestParam(value="idDepartamento") String idDepartamento,
  		@RequestParam(value="fechaInicio") String fechaInicio, 
  		@RequestParam(value="fechaFin") String fechaFin) throws JSONException{
  	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    	Map mapa = new HashMap<>();

  	return mapa;   
  }
  

  @RequestMapping("/getHistorialAbonos")
  public Map getHistorialAbonos(@RequestParam(value="idDepartamento") String idDepartamento,
  		@RequestParam(value="fechaInicio") String fechaInicio, 
  		@RequestParam(value="fechaFin") String fechaFin) throws JSONException{
  	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    	Map mapa = new HashMap<>();
  	return mapa;   
  }
  
}