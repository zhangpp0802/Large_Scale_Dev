package edu.union.adt.graph.tests.zhangy4;

import java.lang.*; 
import java.util.Arrays; 

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import edu.union.adt.graph.GraphFactory;
import edu.union.adt.graph.Graph;
@RunWith(JUnit4.class)
public class BestTest
{
    private Graph<String> g;
    
    @Before
    public void setUp()
    {
        g = new GraphFactory().<String> createGraph();
    }

    @After
    public void tearDown()
    {
        g = null;
    }
    
    @Test
    public void isEmpty()
    {
	assertTrue("New graph has no vertices", g.isEmpty());
	g.addVertex("Foo");
	assertFalse("New vertex added", g.isEmpty());
	g.addVertex("ALA");
	g.removeVertex("Foo");
	g.removeVertex("ALA");
	assertTrue("Vertices also removed", g.isEmpty());
    }

    @Test
    public void removeVertex()
    {
        g.addVertex("Foo");
	g.addVertex("OXO");
	g.removeVertex("ALA");
	g.addEdge("Foo","OXO");
	assertEquals("Remove a not existing vertex", 2, g.numVertices());
	g.removeVertex("Foo");
	assertEquals("Remove a existing vertex", 1, g.numVertices());
	//assertEquals("See if remove edge", 0, g.numEdges());
	g.removeVertex("OXO");
    }

    @Test
    public void removeEdge()
    {
        g.addVertex("Foo");
	g.addVertex("OXO");
	g.addEdge("Foo","OXO");
	g.removeEdge("Foo","ALA");
        assertEquals("Remove a not existing edge", 1,g.numEdges());
	g.removeEdge("Foo","OXO");
	assertEquals("Remove an edge", 0,g.numEdges());
	g.removeVertex("OXO");
	g.removeVertex("Foo");
    }

    @Test
    public void hasPath()
    {
	g.addVertex("Foo");
	g.addVertex("OXO");
	g.addVertex("ALA");
	g.addVertex("NMN");
        g.addEdge("Foo", "OXO");
	g.addEdge("OXO", "ALA");
        assertFalse("Test not connected",
                   g.hasPath("ALA","NMN"));
	assertTrue("Test adjacent path",
                   g.hasPath("Foo","OXO"));
	assertTrue("Test non-adjacent path",
                   g.hasPath("Foo","ALA"));
	assertTrue("Test one node",
                   g.hasPath("NMN","NMN"));
	g.removeVertex("OXO");
	g.removeVertex("Foo");
	g.removeVertex("ALA");
	g.removeVertex("NMN");
    }

    @Test
    public void pathLength()
    {
        g.addVertex("Foo");
	g.addVertex("OXO");
	g.addVertex("ALA");
	g.addVertex("NMN");
	g.addVertex("BMB");
	g.addEdge("Foo","NMN");
        g.addEdge("Foo", "OXO");
	g.addEdge("OXO", "ALA");
	g.addEdge("ALA","NMN");
        assertEquals("Test only one path",2,g.pathLength("Foo","ALA"));
	assertEquals("Test not existing path", Integer.MAX_VALUE, g.pathLength("Foo","BMB"));
	assertEquals("Test shortest path",1,g.pathLength("Foo","NMN"));
	assertEquals("Test shortest path",0,g.pathLength("BMB","BMB"));
	g.removeVertex("OXO");
	g.removeVertex("Foo");
	g.removeVertex("ALA");
	g.removeVertex("NMN");
	g.removeVertex("BMB");
    }

    private Iterable<String> ArrayTOIter(String[] ilist){
      Iterable<String> iterator = Arrays.asList(ilist);
      return iterator;
   }
    
    @Test 
    public void getPath()
    {
        g.addVertex("Foo");
	g.addVertex("OXO");
	g.addVertex("ALA");
	g.addVertex("NMN");
	g.addVertex("BMB");
	g.addEdge("Foo","NMN");
        g.addEdge("Foo", "OXO");
	g.addEdge("OXO", "ALA");
	g.addEdge("ALA","NMN");
	System.out.println("FooNMN");
        assertTrue("Test Shortest Path", iteratorContains(g.getPath("Foo","NMN"), "NMN"));
	assertTrue("Test Shortest Path", iteratorContains(g.getPath("Foo","NMN"), "Foo"));
	assertFalse("Test Shortest Path", iteratorContains(g.getPath("Foo","NMN"), "ALA"));

	assertTrue("Test Shortest Path", iteratorContains(g.getPath("Foo","ALA"), "ALA"));
	assertTrue("Test Shortest Path", iteratorContains(g.getPath("Foo","ALA"), "NMN"));

	assertTrue("Test Shortest Path", iteratorContains(g.getPath("Foo","BMB"), "BMB"));
	assertFalse("Test Shortest Path", iteratorContains(g.getPath("Foo","BMB"), "ALA"));

	assertTrue("Test Shortest Path", iteratorContains(g.getPath("BMB","BMB"), "BMB"));


    }

    private boolean iteratorContains(Iterable<String> container, String x)
    {
        for (String s: container) {
            if (s.equals(x)) {
                return true;
            }
        }

        return false;
    }
}
