package br.uff.ic.dm.verde20221.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import br.uff.ic.dm.verde20221.Alquimia;

public class MenuScreen implements Screen{
    private final Alquimia parent;
    private final Stage stage;
    private final Texture texture = new Texture(Gdx.files.internal("img/menu.png"));
    private final Image backgroundImage = new Image(texture);

    public MenuScreen(Alquimia alquimia){
        parent = alquimia;

        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        this.backgroundImage.setWidth(Gdx.graphics.getWidth());
        this.backgroundImage.setHeight(Gdx.graphics.getHeight());

        this.stage.addActor(backgroundImage); // adds the image as an actor to the stage
        this.backgroundImage.addAction(
                Actions.sequence(
                        Actions.alpha(0.0f),
                        Actions.fadeIn(1.0f)
                )
        );

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
//        table.setDebug(true);
        table.addAction(
                Actions.sequence(
                    Actions.alpha(0.0f),
                    Actions.fadeIn(1.0f)  // Actions.fadeOut(1.0f),
            ) // Actions.sequence);
        ); // splashImage.addAction
        this.stage.addActor(table);

        // temporary until we have asset manager in
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        // Create buttons
        TextButton startGame = new TextButton("Start Game", skin);
        TextButton profile = new TextButton("Profile", skin);
        TextButton exit = new TextButton("Exit", skin);

        startGame.getLabel().setFontScale(3.0f);
        profile.getLabel().setFontScale(3.0f);
        exit.getLabel().setFontScale(3.0f);

        // Position table on bottom of screen
        table.bottom();

        // add buttons to table
        table.add(startGame).fillX().uniformX().width(500).height(100);
        table.row().pad(50, 0, 50, 0);
        table.add(profile).fillX().uniformX().width(500).height(100);
        table.row().pad(0, 0, 50, 0);
        table.add(exit).fillX().uniformX().width(500).height(100);

        // create button listeners
        startGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(new MapScreen(parent));
            }
        });

        profile.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(new ProfileScreen(parent));
            }
        });

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.exit(0);
            }
        });
    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when teh screen size is changed
        stage.getViewport().update(width, height, true);
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
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // dispose of assets when not needed anymore
        stage.dispose();
    }

}