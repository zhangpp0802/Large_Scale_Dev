package test.java;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
    DiagramTest.class,
    RelationTest.class,
    ClassTest.class,
    ThemeCreatorTest.class,
    SpyTest.class
})
public class TestSuite
{ // no implementation needed; above annotations do the work.
}
