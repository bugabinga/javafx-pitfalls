package de.saxsys.pitfalls.fxml.performance;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLPerformance extends Application
{
  interface ClasspathWorkaround
  {
    static void main( final String[] args ) throws IOException
    {
      Application.launch( FXMLPerformance.class, args );
    }
  }

  @Override
  public void start( final Stage primaryStage ) throws IOException
  {
    for ( int i = 0; i < 5; i++ )
    {
      System.out.println( "Loading Iteration###### " + i );
      loadWithWildcards();
      loadWithoutWildcards();
    }
    Platform.exit();
  }

  private static void loadWithoutWildcards() throws IOException
  {
    final long startTime = System.currentTimeMillis();
    final FXMLLoader loader = new FXMLLoader( FXMLPerformance.class.getResource( "/WithoutWildcards.fxml" ) );
    loader.load();
    final long estimatedTime = System.currentTimeMillis() - startTime;
    System.out.printf( "Without Wildcards: \t%dms%n", estimatedTime );
  }

  private static void loadWithWildcards() throws IOException
  {
    final long startTime = System.currentTimeMillis();
    final FXMLLoader loader = new FXMLLoader( FXMLPerformance.class.getResource( "/WithWildcards.fxml" ) );
    loader.load();
    final long estimatedTime = System.currentTimeMillis() - startTime;
    System.out.printf( "With Wildcards: \t%dms%n", estimatedTime );
  }

}
