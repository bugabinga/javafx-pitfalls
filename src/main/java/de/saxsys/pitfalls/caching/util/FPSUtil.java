package de.saxsys.pitfalls.caching.util;


import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

import com.sun.javafx.perf.PerformanceTracker;

public class FPSUtil
{
  private abstract static class FpsTimer extends AnimationTimer
  {
    long delta;
    long lastFrameTime;

    @Override
    public void handle( final long now )
    {
      delta = now - lastFrameTime;
      lastFrameTime = now;
      tick();
    }

    abstract void tick();

    double getAverageFPS()
    {
      final double frameRate = 1.0d / delta;
      return ( frameRate * 1.0e9 );
    }
  }

  private static FpsTimer tracker;

  private static double getFPS()
  {
    return tracker.getAverageFPS();
  }

  public static void displayFPS( Label fpsLabel )
  {
    fpsLabel.sceneProperty().addListener( ( observable, oldValue, newValue ) ->
    {
      if ( newValue != null )
      {
        System.setProperty( "prism.verbose", "true" );
        System.setProperty( "prism.dirtyopts", "false" );
        // System.setProperty("javafx.animation.fullspeed", "true");
        System.setProperty( "javafx.animation.pulse", "10" );

        tracker = new FpsTimer()
        {
          @Override
          void tick()
          {
            fpsLabel.setText( String.format( "FPS: %.0f fps", getFPS() ) );
          }
        };
        tracker.start();
      }
    } );

  }

}
