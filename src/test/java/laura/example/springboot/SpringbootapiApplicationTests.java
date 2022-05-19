package laura.example.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import laura.example.springboot.model.EGender;
import laura.example.springboot.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringbootapiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	private static final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void saveUserOk() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		User userToSave = new User("Jean",sdf.parse("1994-11-08T17:25:00.000"),"France","0665321562", EGender.MALE.name());
		String jsonUser = mapper.writer().writeValueAsString(userToSave);

		mockMvc.perform(post("/users/saveuser").content(jsonUser).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void saveUserKoCountry() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		User userToSave = new User("Michelle",sdf.parse("1994-11-08T17:25:00.000"),"Maroc","0665321562", EGender.FEMALE.name());
		String jsonUser = mapper.writer().writeValueAsString(userToSave);

		mockMvc.perform(post("/users/saveuser").content(jsonUser).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void saveUserKoAge() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		User userToSave = new User("Patrice",sdf.parse("2020-11-08T17:25:00.000"),"France","0665321562", EGender.MALE.name());
		String jsonUser = mapper.writer().writeValueAsString(userToSave);

		mockMvc.perform(post("/users/saveuser").content(jsonUser).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void getAllUsers() throws Exception {
		mockMvc.perform(get("/users/getusers"))
				.andExpect(status().isOk());
	}

	@Test
	public void getUserByName() throws Exception {
		mockMvc.perform(get("/users/getuserbyname/Jean"))
				.andExpect(status().isOk());
	}

	@Test
	public void getUserById() throws Exception {
		mockMvc.perform(get("/users/getuserbyid/1"))
				.andExpect(status().isOk());
	}

}