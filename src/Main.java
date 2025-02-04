import Level.LevelData;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import player.Player;

import java.util.ArrayList;


public class Main extends Application
{
    private static final double SCENE_WIDTH = LevelData.Level1[1].length() * 60 ;
    private static final double SCENE_HEIGHT = 400;
    //private ArrayList<Node> plateforms = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        // Initialisation des élements à afficher
        Pane root = new Pane();

        double bottomHeight = SCENE_HEIGHT / 3;
        double topHeight = SCENE_HEIGHT - bottomHeight;

        Rectangle topHalf = new Rectangle(0, 0, SCENE_WIDTH, topHeight);
        topHalf.setFill(Color.LIGHTBLUE);

        Rectangle bottomHalf = new Rectangle(0, topHeight, SCENE_WIDTH, bottomHeight);
        bottomHalf.setFill(Color.LIGHTGREEN);

        Player player = new Player(SCENE_WIDTH /2, topHeight);
        root.getChildren().addAll(topHalf, bottomHalf, player.getShape());



        Scene scene = primaryStage.getScene();
        if (scene == null) {
            scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
            primaryStage.setScene(scene);
        }

        // configuration des events
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.Q) {
                player.setVelocity(new Point2D(-player.GetPlayerSpeed(), player.getVelocity().getY()));
            } else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                player.setVelocity(new Point2D(player.GetPlayerSpeed(), player.getVelocity().getY()));
            }else if(event.getCode() == KeyCode.SPACE) {
                if(player.isCanJump())
                    player.setVelocity(new Point2D(player.getVelocity().getX(), player.getJumpStrength()));
            }
        });

        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.Q ||
                    event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                player.setVelocity(new Point2D(0, player.getVelocity().getY()));
            }
        });

        // loop du jeu
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                player.update();
            }
        };
        gameLoop.start();

        primaryStage.setTitle("Déplacement Joueur");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}