package com.integrador;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.integrador.dto.UsuarioDTO;
import com.integrador.dto.UsuarioInsertDTO;
import com.integrador.resources.UsuarioResource;
import com.integrador.services.UsuarioService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UsuarioResourceTest {
	
	@InjectMocks
	private UsuarioResource usuarioResource;

	@Mock
	private UsuarioService usuarioService;
	
	private MockMvc mvcUsuario;
    
	private List<UsuarioDTO> listaUsuarios;
    
    private UsuarioInsertDTO usuarioInsertDTO;
    
    private UsuarioDTO usuarioDTO;
    
	@Before
	public void setup() {
        MockitoAnnotations.initMocks(this);	
        when(usuarioService.findAll()).thenReturn(listaUsuarios);
        when(usuarioService.insert(usuarioInsertDTO)).thenReturn(usuarioDTO);
//        when(usuarioService.delete((long)1)).thenReturn(Void);
        
		this.mvcUsuario = MockMvcBuilders.standaloneSetup(usuarioResource).build();
	}
	
	@Test
	public void testFindAllUsers() throws Exception {
		this.mvcUsuario.perform(get("/usuarios"))
				        .andExpect(status().isOk());
	}

	@Test
	public void testInsertUser() throws Exception {        
	    String user = "{\r\n" + 
    		"	\"dataNascimento\":\"2019-11-13T00:00:00+0000\",\r\n" + 
    		"	\"email\":\"jordina@mail.com\",\r\n" + 
    		"	\"nome\":\"Jordina\",\r\n" + 
    		"	\"password\":\"123\"\r\n" + 
		"}";
	    
//	    UsuarioInsertDTO usuarioInsertDTO = new UsuarioInsertDTO();
//	    usuarioInsertDTO.setEmail("jorge@mail.com");
//	    usuarioInsertDTO.setNome("Jorge alves");
//	    usuarioInsertDTO.setPassword("123");
	    
		this.mvcUsuario.perform(post("/usuarios")
				        .contentType(MediaType.APPLICATION_JSON)
				        .content(user))
				        .andExpect(status().isCreated());
	}

	@Test
	public void testDeleteUser() throws Exception {	    
		this.mvcUsuario.perform(delete("/usuarios/1"))
				        .andExpect(status().isOk());
	}
}
