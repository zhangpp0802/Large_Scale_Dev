package test.java;

import view.DiagramListener;

/**
 * A class for DiagramListenerSpy.
 */
public class DiagramListenerSpy implements DiagramListener {
  private int updateCount;

  /**
   * Creates a DiagramListenerSpy. This Spy object will
   * be responsible for registering and
   */
  public DiagramListenerSpy() {
    this.updateCount = 0;
  }

  /**
   * Once update() from Diagram is called, the listener will account
   * for the update call and increment accordingly.
   */
  public void update() {
    this.updateCount++;
  }

  /**
   * Gets the current update count.
   * @return current update count
   */
  public int getUpdateCount() {
    return this.updateCount;
  }
}
