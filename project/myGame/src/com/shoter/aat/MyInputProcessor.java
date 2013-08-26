package com.shoter.aat;

import com.badlogic.gdx.InputProcessor;
import com.shoter.game.Mouse;
import com.shoter.logger.Logger;


public class MyInputProcessor implements InputProcessor {
   @Override
   public boolean keyDown (int keycode) {
      return false;
   }

   @Override
   public boolean keyUp (int keycode) {
      return false;
   }

   @Override
   public boolean keyTyped (char character) {
      return false;
   }

   @Override
   public boolean touchDown (int x, int y, int pointer, int button) {
      return false;
   }

   @Override
   public boolean touchUp (int x, int y, int pointer, int button) {
      return false;
   }

   @Override
   public boolean touchDragged (int x, int y, int pointer) {
      return false;
   }


   @Override
   public boolean scrolled (int amount) {
      return false;
   }

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Mouse.x = screenX;
		Mouse.y = screenY;
		return false;
	}
}