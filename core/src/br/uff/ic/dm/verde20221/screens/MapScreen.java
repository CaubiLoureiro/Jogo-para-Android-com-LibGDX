package br.uff.ic.dm.verde20221.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import br.uff.ic.dm.verde20221.Alquimia;

public class MapScreen implements Screen{
    private final Alquimia parent;
    private final Stage stage;
    private final Texture texture = new Texture(Gdx.files.internal("img/background-menu.png"));
    private final Image backgroundImage = new Image(texture);
    private String[] mapNames = {"MysteryDungeon", "SnowMap", "map1", "Mapa_Jogo", "mapa_Planoc", "Mapa1"};

    public MapScreen(Alquimia alquimia){
        parent = alquimia;

        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        this.backgroundImage.setWidth(Gdx.graphics.getWidth());
        this.backgroundImage.setHeight(Gdx.graphics.getHeight());
        this.stage.addActor(backgroundImage); // adds the image as an actor to the stage

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
//        table.setDebug(true);
        this.stage.addActor(table);

        // temporary until we have asset manager in
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        // Create text areas and labels
        Label profileScreenTitle = new Label("Select a Map", skin);

        // Create buttons
        TextButton selectMap1 = new TextButton("Select " + mapNames[0], skin);
        TextButton selectMap2 = new TextButton("Select " + mapNames[1], skin);
        TextButton selectMap3 = new TextButton("Select " + mapNames[2], skin);
        TextButton selectMap4 = new TextButton("Select " + mapNames[3], skin);
        TextButton selectMap5 = new TextButton("Select " + mapNames[4], skin);
        TextButton selectMap6 = new TextButton("Select " + mapNames[5], skin);
        TextButton goBack = new TextButton("Back to Menu", skin);
        goBack.bottom();

        // Create buttons table
        Table buttonsTable = new Table();
        buttonsTable.center();
//        buttonsTable.setDebug(true);

        // Add buttons to group
        buttonsTable.add(selectMap1).fillX().uniformX().width(600).height(150).left().pad(0, 50, 50, 50);
        buttonsTable.add(selectMap2).fillX().uniformX().width(600).height(150).center().pad(0, 50, 50, 50);
        buttonsTable.add(selectMap3).fillX().uniformX().width(600).height(150).right().pad(0, 50, 50, 50);
        buttonsTable.row();
        buttonsTable.add(selectMap4).fillX().uniformX().width(600).height(150).left().pad(50, 50, 0, 50);
        buttonsTable.add(selectMap5).fillX().uniformX().width(600).height(150).center().pad(50, 50, 0, 50);
        buttonsTable.add(selectMap6).fillX().uniformX().width(600).height(150).right().pad(50, 50, 0, 50);

        // Set font sizes
        TextField.TextFieldStyle textFieldStyle = skin.get(TextField.TextFieldStyle.class);
        textFieldStyle.font.getData().scale(1.0f);

        profileScreenTitle.setFontScale(5.0f);

        selectMap1.getLabel().setFontScale(3.0f);
        selectMap2.getLabel().setFontScale(3.0f);
        selectMap3.getLabel().setFontScale(3.0f);
        selectMap4.getLabel().setFontScale(3.0f);
        selectMap5.getLabel().setFontScale(3.0f);
        selectMap6.getLabel().setFontScale(3.0f);
        goBack.getLabel().setFontScale(3.0f);

        // Position table on center of screen
        table.center();

        //add buttons to table
        table.add(profileScreenTitle).center().padBottom(100);
        table.row().pad(50, 0, 50, 0);
        table.add(buttonsTable).center();
        table.row().pad(0, 0, 50, 0);
        table.add(goBack).fillX().uniformX().width(500).height(100).center().padTop(100);

        // create button listeners
        selectMap1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(new SplashScreen(parent, mapNames[0]));
            }
        });

        selectMap2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(new SplashScreen(parent, mapNames[1]));
            }
        });

        selectMap3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(new SplashScreen(parent, mapNames[2]));
            }
        });

        selectMap4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(new SplashScreen(parent, mapNames[3]));
            }
        });

        selectMap5.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(new SplashScreen(parent, mapNames[4]));
            }
        });

        selectMap6.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(new SplashScreen(parent, mapNames[5]));
            }
        });

        goBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(new MenuScreen(parent));
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

