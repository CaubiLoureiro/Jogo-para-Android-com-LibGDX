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
import br.uff.ic.dm.verde20221.PlayerData;
import br.uff.ic.dm.verde20221.game.ClassThreadComandos;

public class ProfileScreen implements Screen{
    private final Alquimia parent;
    private final Stage stage;
    private final Texture texture = new Texture(Gdx.files.internal("img/background-menu.png"));
    private final Image backgroundImage = new Image(texture);

    public ProfileScreen(Alquimia alquimia){
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
        Label profileScreenTitle = new Label("User Profile", skin);
        Label nicknameLabel = new Label("User Nickname:",  skin);
        final TextField nicknameField = new TextField("", skin);
        Label emailLabel = new Label("User E-Mail Address:",  skin);
        final TextField emailField = new TextField("", skin);

        // Create buttons
        TextButton update = new TextButton("Update Profile", skin);
        update.bottom();

        // Set font sizes
        TextField.TextFieldStyle textFieldStyle = skin.get(TextField.TextFieldStyle.class);
        textFieldStyle.font.getData().scale(1.0f);
        profileScreenTitle.setFontScale(5.0f);
        nicknameLabel.setFontScale(3.0f);
        emailLabel.setFontScale(3.0f);
        update.getLabel().setFontScale(3.0f);

        // Fill fields
        final PlayerData thisPlayer = PlayerData.myPlayerData();
        ClassThreadComandos.objetoAndroidFireBase.getUserProfile(thisPlayer);
        nicknameField.setText(thisPlayer.getPlayerNickName());
        emailField.setText(thisPlayer.getEmail());

        // Position table on center of screen
        table.center();

        //add buttons to table
        table.add(profileScreenTitle);
        table.row().pad(100, 0, 0, 0);
        table.add(nicknameLabel).left();
        table.row().pad(0, 0, 0, 0);
        table.add(nicknameField).fillX().uniformX().width(1000).height(100);
        table.row().pad(50, 0, 0, 0);
        table.add(emailLabel).left();
        table.row().pad(0, 0, 0, 0);
        table.add(emailField).fillX().uniformX().width(1000).height(100);
        table.row().pad(50, 0, 0, 0);
        table.add(update).fillX().uniformX().width(500).height(100);

        // create button listeners
        update.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                thisPlayer.setPlayerNickName(nicknameField.getText());
                thisPlayer.setEmail(emailField.getText());

                ClassThreadComandos.objetoAndroidFireBase.updateUserProfile(thisPlayer);

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
