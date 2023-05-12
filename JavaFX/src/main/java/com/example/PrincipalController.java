package com.example;

import java.io.IOException;
import java.util.Random;

import com.Interface.Funcoes;

import java.io.File;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PrincipalController implements Runnable, Funcoes{

    @FXML
    private VBox Vbox;

    @FXML
    private ImageView asteroid1;

    @FXML
    private ImageView asteroid2;

    @FXML
    private Button leadboard;

    @FXML
    private Button play;

    @FXML
    private ImageView player;

    @FXML
    private Label scoreLabel;

    @FXML
    private TextField userText;

    private static Scene scene;
    private int score;
    private static boolean isPlaying;

    private Random random;

    private int tick;

    private Thread gameThread;

    //private MediaPlayer explosion;

    //executado quando este controlador inicializa
    public void initialize(){
        random = new Random();

        isPlaying = false;
        score = 0;
        gameThread = new Thread(this);
        gameThread.setDaemon(true);

        tick = 0;

        setPlayerAnim();
        plrAnimTick = 0; plrAnimIndex = 0;
        canFly = true; isFlying = false;
        deathTime = dieTime;

        setAsteroidAnim();
        asteAnimTick = 0;
    }

    @FXML
    void jogar(ActionEvent event) {
        isPlaying = true;

        userText.setDisable(true);
        leadboard.setDisable(true);
        play.setDisable(true);

        gameThread.start();
    }

    @FXML
    void switchToLeaderboard(ActionEvent event) throws IOException{
        App.setRoot("leaderboard");
    }

    public static void setScene(Scene actualScene){
        scene = actualScene;
        if(scene != null)
        {
            scene.setOnKeyPressed(e -> 
            {
                switch(e.getCode())
                {
                    case SPACE:
                        if(canFly == true)
                        {
                            isFlying = true;
                            System.out.println(isFlying);
                        }
                    break;

                    case E:
                        if(isPlaying == true && plrAnimIndex != 1)
                        {
                            plrAnimIndex = 2;
                        }
                    break;
                }
            });

            scene.setOnKeyReleased(e -> 
            {
                switch(e.getCode())
                {
                    case SPACE:
                        isFlying = false;
                        System.out.println(isFlying);
                    break;
                }
            });
        }
    }





    //PLAYER

    private Image[][] animPlayer;
    private static boolean canFly;
    private static boolean isFlying;
    private final double flyingSpeed = 2.5;
    
    private int plrAnimTick;
    private static int plrAnimIndex;
    private final int dieTime = 30;
    private int deathTime;

    public void setPlayerAnim(){
        animPlayer = new Image[3][3];
        animPlayer[0] = new Image[3];
        animPlayer[1] = new Image[3];
        animPlayer[2] = new Image[3];

        int cont = 0;
        for(int i = 0; i < animPlayer.length; i++)
        {
            for(int j = 0; j < animPlayer[i].length; j++)
            {
                animPlayer[i][j] = new Image(getClass().getResourceAsStream("sprites/player/player_sprites" + cont + ".png"));
                cont++;
            }
        }
    }

    public void loadPlayerAnim(){
        player.setImage(animPlayer[plrAnimIndex][plrAnimTick]);
        plrAnimTick++;
        if(plrAnimTick >= animPlayer[plrAnimIndex].length)
        {
            plrAnimTick = 0;
        }

        if(plrAnimIndex == 1)
        {
            deathTime--;
            boolean condition = deathTime <= 0;
            if(condition)
            {
                isPlaying = false;
            }
        }
    }

    public void resetPlayer(){
        canFly = true;
        
        plrAnimTick = 0;
        plrAnimIndex = 0;
        deathTime = dieTime;
        player.setImage(animPlayer[plrAnimIndex][plrAnimTick]);

        player.setLayoutX(142.0);
        player.setLayoutY(249.0);

    }

    public void playerPos(){
        if(canFly == true)
        {
            if(isFlying)
            {
                player.setLayoutY(player.getLayoutY() - flyingSpeed);
            }
            else
            {
                player.setLayoutY(player.getLayoutY() + flyingSpeed);
            }
        }
    }
    
    public void Die(){
        if(plrAnimIndex != 1)
        {
            isFlying = false;
            canFly = false;
            plrAnimIndex = 1;
            Score.compareNewScore(userText.getText(), score);
        }
    }

    public void detectCollision(ImageView aste1, ImageView aste2) {
        double x = player.getLayoutX();
        double y = player.getLayoutY();

        double distanciaEntreVetores1 = Math.sqrt(Math.pow(((aste1.getLayoutX() + 40) - (x + 50)), 2) + Math.pow((aste1.getLayoutY() + 40) - (y + 35), 2));
        double distanciaEntreVetores2 = Math.sqrt(Math.pow(((aste2.getLayoutX() + 40) - (x + 50)), 2) + Math.pow((aste2.getLayoutY() + 40) - (y + 35), 2));
        if (distanciaEntreVetores1 <= (194 / 2) || distanciaEntreVetores2 <= (194 / 2))
        {
            Die();
        }
    }

    public void outWindow(){
        double y = player.getLayoutY();
        if(y <= -30 || y >= 530)
        {
            Die();
        }
    }

    public void playerUpdate(){
        playerPos();
        detectCollision(asteroid1, asteroid2);
        outWindow();
    }








    //ASTEROIDS
    private final double asteSpeed = 3.0;
    private Image[] asteroidAnim;

    private int asteAnimTick;

    public double getRandomNumber(int min, int max) {
        return (Math.random() * (max - min)) + min;
    }

    public void asteResetPos(ImageView asteroid){
        asteroid.setLayoutX(getRandomNumber(875, 1000));
        asteroid.setLayoutY(random.nextInt(500));

        if(plrAnimIndex != 1)
        {
            score++;
            scoreLabel.setText("Score: " + score);
        }
    }

    public void asteMove(ImageView asteroid){
        asteroid.setLayoutX(asteroid.getLayoutX() - asteSpeed);
        if(asteroid.getLayoutX() <= -125.0)
        {
            asteResetPos(asteroid);
        }
    }

    public void setAsteroidAnim(){
        asteroidAnim = new Image[3];

        for(int i = 0; i < asteroidAnim.length; i++)
        {
            asteroidAnim[i] = new Image(getClass().getResourceAsStream("sprites/asteroid/sprite_" + i + ".png"));
        }
    }

    public void loadAsteAnim(){
        asteroid1.setImage(asteroidAnim[asteAnimTick]);
        asteroid2.setImage(asteroidAnim[asteAnimTick]);
        asteAnimTick++;
        if(asteAnimTick >= asteroidAnim.length)
        {
            asteAnimTick = 0;
        }
    }

    public void resetAsteroid(){
        asteAnimTick = 0;
        asteResetPos(asteroid1);
        asteResetPos(asteroid2);
        asteroid1.setImage(asteroidAnim[asteAnimTick]);
        asteroid1.setImage(asteroidAnim[asteAnimTick]);
    }

    public void asteroidUpdate(){
        asteMove(asteroid1);
        asteMove(asteroid2);
    }










    //THREAD

    public void reset(){
        gameThread = new Thread(this);
        gameThread.setDaemon(true);

        resetPlayer();
        resetAsteroid();

        tick = 0;
        score = 0;
        scoreLabel.setText("Score: " + score);

        play.setDisable(false);
        userText.setDisable(false);
        leadboard.setDisable(false);
    }

    public void animation(){
        tick++;
        if(tick >= 30)
        {
            loadPlayerAnim();
            loadAsteAnim();
            tick = 0;
        }
    }

    public void update(){
        playerUpdate();
        asteroidUpdate();
    }

    /*public void run() {
		double timePerUpdate = 1000000000.0 / (200); //UPS_SET

		long previousTime = System.nanoTime();
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;

		while(isPlaying == true)
		{
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			previousTime = currentTime;

			if(deltaU >= 1)
			{
				update();
                animation();
				deltaU--;
			}

			if(System.currentTimeMillis() - lastCheck >= 1000)
			{
				lastCheck = System.currentTimeMillis();
			}
		}

        reset();
        gameThread.interrupt();
	}*/

    public void run(){
        while(isPlaying == true)
        {
            try
            {
                Platform.runLater(() -> {
                    update();
                    animation();
                });
                gameThread.sleep(5);
            }
            catch(InterruptedException e)
            {
                gameThread.currentThread().interrupt();
            }
        }

        Platform.runLater(() -> {
            reset();
        });
    }
}