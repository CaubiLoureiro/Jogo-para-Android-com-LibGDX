/*
    Fábrica de Software para Educação
    Professor Lauro Kozovits, D.Sc.
    ProfessorKozovits@gmail.com
    Universidade Federal Fluminense, UFF
    Rio de Janeiro, Brasil
    Subprojeto: Alchemie Zwei

    Partes do software registradas no INPI como integrantes de alguns apps para smartphones
    Copyright @ 2016..2022

    Se você deseja usar partes do presente software em seu projeto, por favor mantenha esse cabeçalho e peça autorização de uso.
    If you wish to use parts of this software in your project, please keep this header and ask for authorization to use.

 */
package br.uff.ic.dm.verde20221.screens;

import br.uff.ic.dm.verde20221.Alquimia;
import br.uff.ic.dm.verde20221.game.World;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SplashScreen implements Screen {

	/*

	 	At Android launcher Android use the following:

		config.useWakelock = true;
		config.useAccelerometer = true;
		useImmersiveMode (true);


	 */

    private final Alquimia parent;
    private final Stage stage;
    private Texture texture = new Texture(Gdx.files.internal("img/guerreira3.png"));
    private Image splashImage = new Image(texture);
    private PlayScreen ps;
    private String mapName;

    public SplashScreen(Alquimia alquimia, String mapName){
        parent = alquimia;
        this.mapName = mapName;

        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        // If the image is not the same size as the screen
        this.splashImage.setWidth(Gdx.graphics.getWidth());
        this.splashImage.setHeight(Gdx.graphics.getHeight());

        ps = new PlayScreen();
        this.stage.addActor(splashImage); // adds the image as an actor to the stage
        this.splashImage.addAction(
                Actions.sequence(
                        Actions.alpha(0.0f),
                        Actions.fadeIn(1.0f),  // Actions.fadeOut(1.0f),
                        Actions.run(new Runnable() { //Actions.delay(1) Actions.alpha(0.0f),
                                        @Override
                                        public void run() {
                                            World.load(mapName, parent);	// leva um tempo para carregar. Enquanto isso splash esta sendo exibido
                                            //Gdx.app.log(" ", " carregando!");
                                        }
                                    }
                        ),
                        Actions.fadeOut(1.0f),
                        Actions.run(new Runnable() { //Actions.delay(1) Actions.alpha(0.0f),
                                        @Override
                                        public void run() {
                                            ((Game) Gdx.app.getApplicationListener()).setScreen(ps);
                                        }
                                    }
                        )
                ) // Actions.sequence
        ); // splashImage.addAction
    } // show()

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // sets the clear color to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear the batch
        this.stage.act(); // update all actors
        this.stage.draw(); // draw all actors on the Stage.getBatch()

    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        this.dispose();

    }

    @Override
    public void dispose() {
        this.texture.dispose();
        this.stage.dispose();

    }

}
