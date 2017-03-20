/**
 * 
 */
package br.ufpi.loes.oracleTest.core.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import br.ufpi.loes.oracleTest.core.Action;
import br.ufpi.loes.oracleTest.core.graph.Event;
import br.ufpi.loes.oracleTest.core.graph.State;
import br.ufpi.loes.oracleTest.core.webdriver.WebDriverManager;

/**
 * @author Ronyerison
 *
 */
public class JsonToGraphConverter {
	
	private static WebDriverManager driverManager;
	
	
	public static DirectedGraph<State, Event> convert(List<Action> actions){
		try{
//			driverManager = new WebDriverManager();
			DirectedGraph<State, Event> graph = new DirectedWeightedMultigraph<State, Event>(Event.class);
//			State data = new State(actions.get(0).getsUrl(), driverManager.countVisibleElements(actions.get(0).getsUrl()), true);

			State data = new State(actions.get(0).getsUrl(), 0, true);
			graph.addVertex(data);		
			
			for (int i = 0; i < actions.size()-1; i++) {
				State source = createState(actions.get(i));
				State target = createState(actions.get(i+1));
				Event edge = createEvent(actions.get(i));
				
				
				if(!graph.containsVertex(source)){
					graph.addVertex(source);
				}
				if(!graph.containsVertex(target)){
					graph.addVertex(target);
				}
				if(!graph.containsEdge(edge)){
					graph.addEdge(source, target, edge);
				}else{
//					((DirectedWeightedMultigraph<State, Event>) graph).setEdgeWeight(edge, graph.getEdgeWeight(edge) + 1);
					setWeitght(graph, source, target, edge);
					System.out.println("Contem aresta: " + graph.getEdgeWeight(edge));
				}
			}
			return graph;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static State createState(Action action) {
//		return new State(action.getsUrl(), driverManager.countVisibleElements(action.getsUrl()));
		return new State(action.getsUrl(), 0);
	}

	private static Event createEvent(Action action){
		return new Event(action.getsActionType(), action.getsXPath(), 1, action.getsUrl());
	}

	public static WebDriverManager getDriverManager() {
		return driverManager;
	}
	
	private static void setWeitght(DirectedGraph<State,Event> graph, State source, State target, Event edge){
		Set<Event> edges = graph.getAllEdges(source, target);
		for (Event event : edges) {
			if (event.equals(edge)) {
				event.setCount(event.getCount()+1);
			}
		}
	}

}
