package com.bstmexico.mihabitat_ws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bstmexico.mihabitat_ws.dao.DepartamentoDao;
import com.bstmexico.mihabitat_ws.dao.HistoricosDao;
import com.bstmexico.mihabitat_ws.dao.LoginDAO;

import com.bstmexico.mihabitat_ws.model.Departamento;

import com.bstmexico.mihabitat_ws.model.PendientesPago;
import com.bstmexico.mihabitat_ws.model.Persona;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/login")
	public Map login(
			
	@RequestParam(value="user") String user,
	@RequestParam(value="password") String password) throws JSONException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		loginDao = context.getBean(LoginDAO.class);
	    Persona usuario = new Persona();
	    usuario= loginDao.checkLogin(user, password);
	    Map mapa = new HashMap<>();
	    mapa.put("Usuario", usuario);
	    return mapa;    
    }
	
	@RequestMapping("/getDepartamentoHabitante")
	public Map getDepartamentoHabitante(
	@RequestParam(value="idUsuario") String idUsuario) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		departamentoDao = context.getBean(DepartamentoDao.class);
		List<Departamento> list = departamentoDao.getDepartamentos(idUsuario);
		context.close();	
		int i =0;
		while(list.size()>i) {
			int saldo = departamentoDao.getSaldoFavor(list.get(i).getIdDepartamento());
			list.get(i).setSaldoFavor(saldo);
			i++;
		}
		Map mapa = new HashMap<>();
		mapa.put("Departamentos", list);
	  	return mapa;   
	}
	
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping("/getTorreyEtiquetas")
  public Map getTorre(@RequestParam(value="departamento") String idDepartamento) throws JSONException{
  	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    	departamentoDao = (DepartamentoDao) context.getBean("departamentoDao");
    	Map mapa = new HashMap<>();
    	Departamento lista = departamentoDao.getTorreEtiquetas(idDepartamento);
      mapa.put("departamentos", lista);
  	return mapa;
  }
    
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @RequestMapping("/getSaldoaFavor")
  public Map getSaldoaFavor(@RequestParam(value="idDepartamento") String idDepartamento) throws JSONException{
  	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    	departamentoDao = (DepartamentoDao) context.getBean("departamentoDao");
    	Map mapa = new HashMap<>();
      int saldoFavor = departamentoDao.getSaldoFavor(Integer.valueOf(idDepartamento));
      mapa.put("saldoFavor", saldoFavor);
  	return mapa;   
  }
     
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @RequestMapping("/getPendientesPago")
  public Map getPendientesPago(@RequestParam(value="idDepartamento") String idDepartamento) throws JSONException{
  	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
  	historicosDao = (HistoricosDao) context.getBean("historicosDao");
    	Map mapa = new HashMap<>();
    	List<PendientesPago> pendientespago = historicosDao.getPendientesPago(Integer.valueOf(idDepartamento));
    	mapa.put("pendientes", pendientespago);
  
  	return mapa;   
  }
  
}