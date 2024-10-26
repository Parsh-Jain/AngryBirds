//package com.ap.angrybirds;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.ScreenAdapter;
//import com.badlogic.gdx.audio.Sound;
//import com.badlogic.gdx.files.FileHandle;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.utils.GdxRuntimeException;
//import com.badlogic.gdx.video.VideoPlayer;
//import com.badlogic.gdx.video.VideoPlayerCreator;
//
//import java.io.FileNotFoundException;
//
//public class VideoLoadingScreen extends ScreenAdapter {
//    private final Main main;
//    private VideoPlayer videoPlayer;
//    private Sound sound;
//
//    public VideoLoadingScreen(Main main) {
//        this.main = main;
//    }
//    @Override
//    public void show() {
//        videoPlayer=new VideoPlayer() {
//            @Override
//            public boolean play(FileHandle fileHandle) throws FileNotFoundException {
//                return false;
//            }
//
//            @Override
//            public boolean update() {
//                return false;
//            }
//
//            @Override
//            public Texture getTexture() {
//                return null;
//            }
//
//            @Override
//            public boolean isBuffered() {
//                return false;
//            }
//
//            @Override
//            public void pause() {
//
//            }
//
//            @Override
//            public void resume() {
//
//            }
//
//            @Override
//            public void stop() {
//
//            }
//
//            @Override
//            public void setOnVideoSizeListener(VideoSizeListener videoSizeListener) {
//
//            }
//
//            @Override
//            public void setOnCompletionListener(CompletionListener completionListener) {
//
//            }
//
//            @Override
//            public int getVideoWidth() {
//                return 0;
//            }
//
//            @Override
//            public int getVideoHeight() {
//                return 0;
//            }
//
//            @Override
//            public boolean isPlaying() {
//                return false;
//            }
//
//            @Override
//            public int getCurrentTimestamp() {
//                return 0;
//            }
//
//            @Override
//            public void dispose() {
//
//            }
//
//            @Override
//            public void setVolume(float v) {
//
//            }
//
//            @Override
//            public float getVolume() {
//                return 0;
//            }
//
//            @Override
//            public void setLooping(boolean b) {
//
//            }
//
//            @Override
//            public boolean isLooping() {
//                return false;
//            }
//
//            @Override
//            public void setFilter(Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter1) {
//
//            }
//        };
//    }
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        // Update and render the video
//        if (videoPlayer.isPlaying()) {
//            videoPlayer.update();
//            videoPlayer.render(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Adjust size if needed
//        }
//
//        // Check for key presses to generate sound and switch screens
//        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
//            sound.play(); // Play sound
//            main.setScreen(new HomeScreen(main)); // Set screen to home screen
//        }
//    }
//
//    @Override
//    public void dispose() {
//        if (videoPlayer != null) {
//            videoPlayer.dispose();
//        }
//        sound.dispose(); // Dispose of the sound
//    }
//}
