/**
 * 
 */
package br.ufpi.loes.oracleTest.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.jgrapht.DirectedGraph;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import br.ufpi.loes.oracleTest.core.converters.JsonToGraphConverter;
import br.ufpi.loes.oracleTest.core.graph.Event;
import br.ufpi.loes.oracleTest.core.graph.State;

/**
 * @author Ronyerison
 *
 */
public class TesteJson {
	public static void main(String[] args) {
		File jsonFile = new File("src//test//resources//actions.json");
		String json = readFile(jsonFile);
		
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(json).getAsJsonObject();
		
		JsonArray array = jsonObject.getAsJsonArray("actions");
		
		List<Action> actions = toList(array.toString());
		
		
		System.out.println("Total de ações: " + actions.size());
		
		DirectedGraph<State,Event> graph = JsonToGraphConverter.convert(actions);
		
		System.out.println("Vertices: " + graph.vertexSet().size() + "\nArestas: "+ graph.edgeSet().size());
		System.out.println(graph.toString());
	}

	private static List<Action> toList(String json) {
	    if (null == json) {
	        return null;
	    }
	    Gson gson = new Gson();
	    return gson.fromJson(json, new TypeToken<List<Action>>(){}.getType());
	}
	
	private static String readFile(File jsonFile) {
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new FileReader(jsonFile));
			
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
