//package com.ap.angrybirds;
//
//import com.badlogic.gdx.math.Rectangle;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class HomeScreenTest {
//
//    private HomeScreen homeScreen;
//
//    @Before
//    public void setUp() {
//        // Create a mock instance of Main
//        Main mockMain = new Main();
//        homeScreen = new HomeScreen(mockMain);
//
//        // Initialize button rectangles
//        homeScreen.Play_Button = new java.awt.Rectangle(200, 300, 100, 50); // x, y, width, height
//        homeScreen.ExitButton = new java.awt.Rectangle(400, 300, 100, 50);
//        homeScreen.SettingButton = new java.awt.Rectangle(600, 300, 100, 50);
//        homeScreen.ViewButton2 = new java.awt.Rectangle(800, 300, 100, 50);
//    }
//
//    @Test
//    public void testCheckButtonPress_PlayButton() {
//        // Simulate a touch within the Play_Button
//        String result = homeScreen.checkButtonPress(250, 325); // Inside Play_Button
//        assertEquals("PlayButton", result);
//    }
//
//    @Test
//    public void testCheckButtonPress_ExitButton() {
//        // Simulate a touch within the ExitButton
//        String result = homeScreen.checkButtonPress(450, 325); // Inside ExitButton
//        assertEquals("ExitButton", result);
//    }
//
//    @Test
//    public void testCheckButtonPress_SettingButton() {
//        // Simulate a touch within the SettingButton
//        String result = homeScreen.checkButtonPress(650, 325); // Inside SettingButton
//        assertEquals("SettingButton", result);
//    }
//
//    @Test
//    public void testCheckButtonPress_ViewButton() {
//        // Simulate a touch within the ViewButton2
//        String result = homeScreen.checkButtonPress(850, 325); // Inside ViewButton2
//        assertEquals("ViewButton", result);
//    }
//
//    @Test
//    public void testCheckButtonPress_NoButton() {
//        // Simulate a touch outside all buttons
//        String result = homeScreen.checkButtonPress(50, 50); // Outside all buttons
//        assertEquals("NoButton", result);
//    }
//}
