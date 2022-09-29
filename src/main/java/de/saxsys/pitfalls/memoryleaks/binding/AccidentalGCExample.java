package de.saxsys.pitfalls.memoryleaks.binding;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Andy Moncsek on 14.10.15.
 */
public class AccidentalGCExample extends Application
{
  DoubleBinding mult;

  @Override
  public void start( final Stage primaryStage ) throws Exception
  {
    final HBox container = new HBox();
    final Scene scene = new Scene( container, 400, 150 );

    final VBox vbox = new VBox();
    final Slider slider = new Slider( 0, 1, 1 );
    final Button forceGC = new Button( "(try) force gc" );
    final Label label = new Label( "" );

    primaryStage.setScene( scene );
    primaryStage.setTitle( "Slider Sample" );

    forceGC.setOnAction( event -> System.gc() );

    Bindings.
        multiply( slider.valueProperty(), 100.0 ).
        addListener( ( x, y, z ) ->
        {
          label.setText( String.format( "Slider pos: %.2f ", z.floatValue() ) );
        } );


    /**
     * Solution !!
     mult = Bindings.multiply(slider.valueProperty(), 100.0);
     mult.addListener((a, b, c) -> {
     label.setText(String.format("Slider pos: %.2f ", c.floatValue()));
     });
     **/

    vbox.getChildren().addAll( slider, forceGC );
    container.getChildren().addAll( vbox, label );
    primaryStage.show();
  }

  interface ClasspathWorkaround
  {
    static void main( final String[] args )
    {
      Application.launch( AccidentalGCExample.class, args );
    }
  }
}
