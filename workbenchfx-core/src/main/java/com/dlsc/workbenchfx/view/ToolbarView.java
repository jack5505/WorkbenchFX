package com.dlsc.workbenchfx.view;

import static com.dlsc.workbenchfx.Workbench.STYLE_CLASS_ACTIVE_ADD_BUTTON;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import com.dlsc.workbenchfx.view.controls.ToolbarControl;
import com.dlsc.workbenchfx.view.controls.selectionstrip.SelectionStrip;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Represents the toolbar which is being shown at the top of the window.
 *
 * @author François Martin
 * @author Marco Sanfratello
 */
public class ToolbarView extends VBox implements View {

  HBox topBox;
  ToolbarControl toolbarControl;
  HBox bottomBox;

  StackPane addIconShape;
  Button addModuleBtn;
  StackPane menuIconShape;
  Button menuBtn;
  SelectionStrip<WorkbenchModule> tabBar;

  /**
   * Creates a new {@link ToolbarView} for the Workbench.
   */
  public ToolbarView() {
    init();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initializeSelf() {
    setId("toolbar");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initializeParts() {
    topBox = new HBox();
    topBox.setId("top-box");

    toolbarControl = new ToolbarControl();
    toolbarControl.setId("toolbar-control");

    bottomBox = new HBox();
    bottomBox.setId("bottom-box");

    addIconShape = new StackPane();
    addIconShape.getStyleClass().add("shape");
    addModuleBtn = new Button("", addIconShape);
    addModuleBtn.getStyleClass().add("icon");
    addModuleBtn.setId("add-button");
    addModuleBtn.getStyleClass().add(STYLE_CLASS_ACTIVE_ADD_BUTTON);

    menuIconShape = new StackPane();
    menuIconShape.getStyleClass().add("shape");
    menuBtn = new Button("", menuIconShape);
    menuBtn.getStyleClass().add("icon");
    menuBtn.setId("menu-button");

    tabBar = new SelectionStrip<>();
    // Reset default sizing from the selectionStrip constructor
    tabBar.setPrefSize(0, 0);
    tabBar.setId("tab-bar");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void layoutParts() {
    topBox.getChildren().add(toolbarControl);
    HBox.setHgrow(toolbarControl, Priority.ALWAYS);

    bottomBox.getChildren().addAll(tabBar, addModuleBtn);
    HBox.setHgrow(tabBar, Priority.ALWAYS);

    getChildren().addAll(topBox, bottomBox);
    Platform.runLater(() -> addModuleBtn.requestFocus());
  }

  /**
   * Removes the menuBtn wherever it is located.
   */
  void removeMenuBtn() {
    bottomBox.getChildren().remove(menuBtn);
    topBox.getChildren().remove(menuBtn);
  }

  /**
   * Adds the menuBtn in first position of the topBox
   */
  void addMenuBtnTop() {
    topBox.getChildren().add(0, menuBtn);
  }

  /**
   * Adds the menuBtn in first position of the bottomBox
   */
  void addMenuBtnBottom() {
    bottomBox.getChildren().add(0, menuBtn);
  }
}
