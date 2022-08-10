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

package br.uff.ic.dm.verde20221;

import com.badlogic.gdx.ApplicationListener;

import br.uff.ic.dm.verde20221.game.ClassThreadComandos;
import br.uff.ic.dm.verde20221.screens.MenuScreen;
import br.uff.ic.dm.verde20221.game.World;
import com.badlogic.gdx.Game;

// LINKS UTEIS
//https://itsfoss.com/best-linux-screen-recorders/

// https://stackoverflow.com/questions/27609442/how-to-get-the-sha-1-fingerprint-certificate-in-android-studio-for-debug-mode#:~:text=Click%20on%20Tasks,run%20or%20debug%20your%20application
// https://stackoverflow.com/questions/53040047/generate-apk-file-from-aab-file-android-app-bundle

// para espelhar a tela de seu celular no Ubuntu
// https://diolinux.com.br/tutoriais/espelhe-tela-do-seu-android-no-seu-linux-com-o-scrcpy.html

// como gerar o SHA1 em seu terminal
// keytool -list -v -alias myKey -keystore myKeystore.jks
// use a senha que você guardou. Ex:
// SHA1: CC:C9:86:C0:86:5D:36:64:DB:64:8F:30:44:80:F6:EC:01:87:91:46
public class Alquimia extends Game implements ApplicationListener{

	public Alquimia(InterfaceAndroidFireBase objetoAndroidFireBase){
		ClassThreadComandos.objetoAndroidFireBase = objetoAndroidFireBase;
		// Thread para receber os comandos do Firebase
		ClassThreadComandos.produtorConsumidor = new ClassThreadComandos(this);
	}

	public Alquimia() {
		// uhupapqs5nUET1X39dEkeh8eOIE2
		//uLfRy16ti2QjnbDBCMlI94GwB0t2
		// usado pelo Desktop que não faz nada
	}

	@Override
	public void create() {
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		World.dispose();
	}

}

/* código default da criação do projeto LibGDX com uma classe Alquimia
public class Alquimia extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
*/
