package com.android1;

import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static com.gluonhq.charm.glisten.application.AppManager.HOME_VIEW;

public class AndroidDev extends Application {

    private final AppManager appManager = AppManager.initialize(this::postInit);

    @Override
    public void init() {
        appManager.addViewFactory(HOME_VIEW, BasicView::new);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(50);
        layout.setVgap(100);
        Scene primaryScene = new Scene(layout, 1000, 500);
        Button increment = new Button("Increment");
        Button decrement = new Button("Decrement");
        Label title = new Label("Simple Counter");
        title.setFont(Font.font(30));
        layout.add(title, 0,0,2,1);
        GridPane.setHalignment(title, HPos.CENTER);
        Label count = new Label("0");
        count.setFont(Font.font(20));
        layout.add(count, 0, 1, 2, 1);
        GridPane.setHalignment(count, HPos.CENTER);
        layout.add(increment, 0, 2);
        layout.add(decrement, 1, 2);

        increment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int currentValue = Integer.parseInt(count.getText());
                currentValue++;
                count.setText(String.valueOf(currentValue));
            }
          }
        );

        decrement.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int currentValue = Integer.parseInt(count.getText());
                if(currentValue > 0){
                    currentValue--;
                    count.setText(String.valueOf(currentValue));
                }
            }
        });


        primaryStage.setScene(primaryScene);
        primaryStage.show();


    }

    private void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);

        ((Stage) scene.getWindow()).getIcons().add(new Image(AndroidDev.class.getResourceAsStream("/icon.png")));
    }

    public static void main(String args[]) {
        launch(args);
    }
}
