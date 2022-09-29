package de.saxsys.pitfalls.memoryleaks.listener.crashingweaklistener;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.WeakChangeListener;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import de.saxsys.pitfalls.memoryleaks.listener.util.Car;

//This could be a view
class LeakingView extends StackPane
{
  LeakingView( final Car car )
  {
    final Label carLabel = new Label();
    carLabel.textProperty().bind( car.name );

    // Leaking - (If you use lambdas instead of anonymous classes you have to reference a class member from the
    // lambda, otherwise the compile will optimize the lambda to a static method and a leak is avoided)
    final ChangeListener<String> carChangedListener = ( observable, oldValue, newValue ) ->
    {
      if ( "remove".equals( newValue ) )
      {
        ( (Pane) getParent() ).getChildren().remove( this );
      }
      System.out.println( getScene().toString() );
    };

    car.name.addListener( new WeakChangeListener<>( carChangedListener ) );
    getChildren().add( carLabel );
  }
}
