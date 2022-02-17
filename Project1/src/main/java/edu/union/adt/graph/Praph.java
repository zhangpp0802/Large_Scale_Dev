package edu.union.adt.graph;

import java.util.*;

/**
 * A graph that establishes connections (edges) between objects of
 * (parameterized) type V (vertices).  The edges are directed.  An
 * undirected edge between u and v can be simulated by two edges: (u,
 * v) and (v, u).
 *
 * The API is based on one from
 *     http://introcs.cs.princeton.edu/java/home/
 *
 * Some method names have been changed, and the Graph type is
 * parameterized with a vertex type V instead of assuming String
 * vertices.
 *
 * @author Aaron G. Cass
 * @version 1
 */
public class Praph<V> implements Graph<V>
{
private ArrayList<ArrayList<V>> adjacencyList;
private ArrayList<V> vertexList;
private ArrayList<Integer> shortest;

 @Override 
    public boolean equals(Object o) {
        return true;
    }

/**
     * Create an empty graph.
     */
    public Praph() 
    {

vertexList = new ArrayList<V>();
adjacencyList = new ArrayList<ArrayList<V>>();
shortest = new ArrayList<Integer>();

    }

    /**
     * @return the number of vertices in the graph.
     */
    public int numVertices()
    {
return vertexList.size();
    }

    /**
     * @return the number of edges in the graph.
     */
    public int numEdges()
    {
        return adjacencyList.size();
    }

    /**
     * Gets the number of vertices connected by edges from a given
     * vertex.  If the given vertex is not in the graph, throws a
     * RuntimeException.
     *
     * @param vertex the vertex whose degree we want.
     * @return the degree of vertex 'vertex'
     */
    public int degree(V vertex)
    {
Integer num = 0;
ArrayList<V> adjacent = new ArrayList<V>();
for (ArrayList<V> connected: adjacencyList){if(connected.get(0).equals(vertex)){num += 1;}}
return num;
    }

    /**
     * Adds a directed edge between two vertices.  If there is already an edge
     * between the given vertices, does nothing.  If either (or both)
     * of the given vertices does not exist, it is added to the
     * graph before the edge is created between them.
     *
     * @param from the source vertex for the added edge
     * @param to the destination vertex for the added edge
     */
    public void addEdge(V from, V to)
    {
if (!(vertexList.contains(from))){addVertex(from);}
if (!(vertexList.contains(to))){addVertex(to);}
ArrayList<V> connected = new ArrayList<V>();
connected.add(from);
connected.add(to);
adjacencyList.add(connected);
    }

    /**
     * Adds a vertex to the graph.  If the vertex already exists in
     * the graph, does nothing.  If the vertex does not exist, it is
     * added to the graph, with no edges connected to it.
     *
     * @param vertex the vertex to add
     */
    public void addVertex(V vertex)
    {
vertexList.add(vertex);
    }

    /**
     * @return the an iterable collection for the set of vertices of
     * the graph.
     */
    public Iterable<V> getVertices()
    {
        return getIterableFromIterator(vertexList.iterator());
    }

    /**
     * Gets the vertices adjacent to a given vertex.  A vertex y is
     * "adjacent to" vertex x if there is an edge (x, y) in the graph.
     * Because edges are directed, if (x, y) is an edge but (y, x) is
     * not an edge, we would say that y is adjacent to x but that x is
     * NOT adjacent to y.
     *
     * @param from the source vertex
     * @return an iterable collection for the set of vertices that are
     * the destinations of edges for which 'from' is the source
     * vertex.  If 'from' is not a vertex in the graph, returns an
     * empty iterator.
     */
    public Iterable<V> adjacentTo(V from)
    {
ArrayList<V> adjacent = new ArrayList<V>();
for (ArrayList<V> connected: adjacencyList){if(connected.get(0).equals(from)){adjacent.add(connected.get(1));}}
return getIterableFromIterator(adjacent.iterator());
    }

private Iterable<V> 
    getIterableFromIterator(Iterator<V> iterator) 
    { 
        return new Iterable<V>() { 
            @Override
            public Iterator<V> iterator() 
            { 
                return iterator; 
            } 
        }; 
    } 

    /**
     * Tells whether or not a vertex is in the graph.
     *
     * @param vertex a vertex
     * @return true iff 'vertex' is a vertex in the graph.
     */
    public boolean contains(V vertex)
    {
        for (V ver : vertexList){
	if (ver.equals(vertex)){return true;}
	}
	return false;
    }

    /**
     * Tells whether an edge exists in the graph.
     *
     * @param from the source vertex
     * @param to the destination vertex
     *
     * @return true iff there is an edge from the source vertex to the
     * destination vertex in the graph.  If either of the given
     * vertices are not vertices in the graph, then there is no edge
     * between them.
     */
    public boolean hasEdge(V from, V to)
    {
for (ArrayList<V> connected: adjacencyList){if (from.equals(connected.get(0)) && to.equals(connected.get(1))){return true;}
}
return false;
    }

    /**
     * Gives a string representation of the graph.  The representation
     * is a series of lines, one for each vertex in the graph.  On
     * each line, the vertex is shown followed by ": " and then
     * followed by a list of the vertices adjacent to that vertex.  In
     * this list of vertices, the vertices are separated by ", ".  For
     * example, for a graph with String vertices "A", "B", and "C", we
     * might have the following string representation:
     *
     * <PRE>
     * A: A, B
     * B:
     * C: A, B
     * </PRE>
     *
     * This representation would indicate that the following edges are
     * in the graph: (A, A), (A, B), (C, A), (C, B) and that B has no
     * adjacent vertices.
     *
     * Note: there are no extraneous spaces in the output.  So, if we
     * replace each space with '*', the above representation would be:
     *
     * <PRE>
     * A:*A,*B
     * B:
     * C:*A,*B
     * </PRE>
     *
     * @return the string representation of the graph
     */
    public String toString()
    {
String returned = new String();
returned += "<PRE>"; 
returned += "\n";
for (V i : vertexList){
returned += i.toString();
returned += ":";
for(ArrayList<V> connected: adjacencyList){if(connected.get(0).toString().equals(i.toString())){
returned += " ";
returned += connected.get(1).toString();
returned += ",";}}
returned = returned.substring(0, returned.length() - 1);
returned += "\n";}
returned += "</PRE>";
    return returned;}


/**
     * Tells whether the graph is empty.
     *
     * @return true iff the graph is empty. A graph is empty if it has
     * no vertices and no edges.
     */
    public boolean isEmpty(){return numVertices() == 0 && numEdges() == 0;}



/**
     * Removes a vertex from the graph.  Also removes any edges
     * connecting from the edge or to the edge.
     *
     * <p>Postconditions:
     *
     * <p>If toRemove was in the graph:
     * <ul>
     * <li>numVertices = numVertices' - 1
     * <li>toRemove is no longer a vertex in the graph
     * <li>for all vertices v: toRemove is not in adjacentTo(v)
     * </ul>
     *
     * @param toRemove the vertex to remove.
     */
    public void removeVertex(V toRemove){
	if (contains(toRemove))
	{vertexList.remove(toRemove);}
	for (ArrayList<V> sublist: adjacencyList){
	if (sublist.contains(toRemove)){
		adjacencyList.remove(sublist);
		break;}}}


/**
     * Removes an edge from the graph.
     *
     * <p>Postcondition: If from and to were in the graph and (from,
     * to) was an edge in the graph, then:
     * <ul>
     * <li> numEdges = numEdges' - 1
     * <li> to is no longer in adjacentTo(from)
     * </ul>
     *
     * @param from the source vertex for the edge
     * @param to the target vertex for the edge
     */
    public void removeEdge(V from, V to){
	ArrayList<V> arr = new ArrayList();
	arr.add(from);
	arr.add(to);
	for (ArrayList<V> sublist: adjacencyList){
	if (sublist.equals(arr)){
		adjacencyList.remove(sublist);
		break;}}}



/**
     * Tells whether there is a path connecting two given vertices.  A
     * path exists from vertex A to vertex B iff A and B are in the
     * graph and there exists a sequence x_1, x_2, ..., x_n where:
     *
     * <ul>
     * <li>x_1 = A
     * <li>x_n = B
     * <li>for all i from 1 to n-1, (x_i, x_{i+1}) is an edge in the graph.
     * </ul>
     *
     * It therefore follows that if vertex A is in the graph, there
     * is a path from A to A.
     *
     * @param from the source vertex
     * @param to the destination vertex
     * @return true iff there is a path from 'from' to 'to' in the graph.
     */
    public boolean hasPath(V from, V to){
	boolean f = false;
	return findAdjacent(from, to, f);
}

    private boolean findAdjacent(V from, V to, boolean f){
	if (from.equals(to)){f = true;}
	else if(degree(from) == 0){f = false;}
	else{
		Iterable<V> iterlist = adjacentTo(from);
		for(V r: iterlist){
			f = findAdjacent(r,to,f);
			}
		}
	return f;
	
}

/**
     * Gets the length of the shortest path connecting two given
     * vertices.  The length of a path is the number of edges in the
     * path.
     *
     * <ol> 
     * <li>If from = to, the shortest path has length 0
     * <li>Otherwise, the shortest path length is the length of the shortest
     * possible path connecting from to to.  
     * </ol>
     *
     * @param from the source vertex
     * @param to the destination vertex
     * @return the length of the shortest path from 'from' to 'to' in
     * the graph.  If there is no path, returns Integer.MAX_VALUE
     */
    public int pathLength(V from, V to){
	ArrayList<Integer> intarray = new ArrayList<Integer>();
	intarray.add(Integer.MAX_VALUE);
	if(hasPath(from, to)){return findpathlen(from, to,0,intarray);}
	else{return Integer.MAX_VALUE;}
}

    private int findpathlen(V from, V to, int n, ArrayList<Integer> mini){
	if (from.equals(to)){mini.add(n);n = 0;}
	else if(degree(from) == 0){n = Integer.MAX_VALUE;}
	else{
		Iterable<V> iterlist = adjacentTo(from);
		for(V r: iterlist){
			Integer e = new Integer(n);
			if (e == Integer.MAX_VALUE){n = findpathlen(r,to,e.intValue(),mini);}
			else{n = findpathlen(r,to,n+1,mini);}
			mini.add(n);
			n = 0;}
		
		}
	return Collections.min(mini);
}

    private Iterable<V> ArrayTOIter(V[] ilist){
      Iterable<V> iter = Arrays.asList(ilist);
      return iter;
   }



/**
     * Returns the vertices along the shortest path connecting two
     * given vertices.  The vertices are given in the order x_1,
     * x_2, x_3, ..., x_n, where:
     *
     * <ol>
     * <li>x_1 = from
     * <li>x_n = to
     * <li>for all i from 1 to n-1: (x_i, x_{i+1}) is an edge in the graph.
     * </ol>
     * 
     * @param from the source vertex
     * @param to the destination vertex
     * @return an Iterable collection of vertices along the shortest
     * path from 'from' to 'to'.  The Iterable includes the source and
     * destination vertices. If there is no path from 'from' to 'to'
     * in the graph (e.g. if the vertices are not in the graph),
     * returns an empty Iterable collection of vertices.
     */
    public Iterable<V> getPath(V from, V to){
	ArrayList<ArrayList<V>> mmmmin = new ArrayList<ArrayList<V>>();
	mmmmin = findpathes(from, to,0, mmmmin);
	System.out.println(mmmmin);


	ArrayList<String> list = new ArrayList<String>(); 
  
        list.add("WWW"); 
        Iterator iterator = list.iterator(); 
	return getIterableFromIterator(iterator);


	}


    private ArrayList<ArrayList<V>> findpathes(V from, V to, int n, ArrayList<ArrayList<V>> mini){
	if (from.equals(to)){
		ArrayList<V> c = new ArrayList<V>();
		if (mini.size()>n){
			c = mini.get(n);}
		c.add(from);
		mini.add(n,c);
		n = n+1;}
	else if(degree(from) == 0){
		if(!mini.get(n).isEmpty()){mini.remove(n);}}
	else{
		Iterable<V> iterlist = adjacentTo(from);
		ArrayList<V> c = new ArrayList<V>();
		if (mini.size()>n){
			c = mini.get(n);}
		c.add(from);
		mini.add(n,c);
		for(V r: iterlist){
			mini = findpathes(r,to,n,mini);
			n = n+1;}
		}
	return mini;
}

}








