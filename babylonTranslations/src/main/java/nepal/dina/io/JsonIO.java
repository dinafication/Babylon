package nepal.dina.io;

import java.io.IOException;

import nepal.dina.data.json.glodsy.Word;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonIO {

	public <T> Object getObject(String s, Class<T> classs)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper om = new ObjectMapper();
		T o = om.readValue(s, classs);

		return o;
	}

	public <T> String getString(T object)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper om = new ObjectMapper();
		String s = om.writeValueAsString(object);

		return s;
	}
	
	

}
