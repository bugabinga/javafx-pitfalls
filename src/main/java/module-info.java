open module javafx.pitfalls {
  requires transitive javafx.graphics;
  requires javafx.fxml;
  requires javafx.controls;

  exports de.saxsys.pitfalls.caching;
  exports de.saxsys.pitfalls.concurrency;
  exports de.saxsys.pitfalls.fxml.performance;
  exports de.saxsys.pitfalls.memoryleaks.binding;
  exports de.saxsys.pitfalls.memoryleaks.listener.util;
  exports de.saxsys.pitfalls.memoryleaks.listener.crashingweaklistener;
  exports de.saxsys.pitfalls.memoryleaks.listener.leakinglistener;
  exports de.saxsys.pitfalls.pixels;
}