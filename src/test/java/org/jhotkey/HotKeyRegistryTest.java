package org.jhotkey;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.junit.jupiter.api.*;

public class HotKeyRegistryTest {
    private HotKeyListener hotKeyListener;
    private HotKey hotKey;

    private void simulateNativeKeyPress(NativeKeyListener nativeKeyListener, int keyCode, int mask) {
        NativeKeyEvent nativeKeyEvent = new NativeKeyEvent(
                NativeKeyEvent.NATIVE_KEY_PRESSED,
                mask,
                0,        // Raw Code is not important
                keyCode,
                NativeKeyEvent.CHAR_UNDEFINED,
                NativeKeyEvent.KEY_LOCATION_STANDARD);

        nativeKeyListener.nativeKeyPressed(nativeKeyEvent);
    }

    private void simulateNativeKeyRelease(NativeKeyListener nativeKeyListener, int keyCode, int mask) {
        NativeKeyEvent nativeKeyEvent = new NativeKeyEvent(
                NativeKeyEvent.NATIVE_KEY_RELEASED,
                mask,
                0,        // Raw Code is not important
                keyCode,
                NativeKeyEvent.CHAR_UNDEFINED,
                NativeKeyEvent.KEY_LOCATION_STANDARD);

        nativeKeyListener.nativeKeyReleased(nativeKeyEvent);
    }

    @BeforeEach
    void initVars() {
        hotKey = new HotKey(
                HotKey.CTRL_MASK | HotKey.SHIFT_MASK,
                HotKey.VC_R
        );

        hotKeyListener = new HotKeyListenerImplementation();
    }

    @Test
    void registersAndRemovesHotKeyListener() {
        HotKeyRegistry.registerHotKeyListener(hotKey, hotKeyListener);
        Assertions.assertTrue(HotKeyRegistry.getRegisteredHotKeyListeners(hotKey).contains(hotKeyListener));

        HotKeyRegistry.removeHotKeyListener(hotKey, hotKeyListener);
        HotKeyRegistry.removeHotKeyListener(hotKey, hotKeyListener); // remove non-existing hot key listener; supposed
        HotKeyRegistry.removeHotKeyListener(new HotKey(), hotKeyListener); // remove listener for non-existing hot key
        Assertions.assertFalse(HotKeyRegistry.getRegisteredHotKeyListeners(hotKey).contains(hotKeyListener));
    }

    @Test
    void modifesCurrentHotKey() {

        // simple hot key press

        simulateNativeKeyPress(HotKeyRegistry.getNativeKeyListener(), HotKey.VC_CONTROL, HotKey.CTRL_L_MASK);
        Assertions.assertEquals(new HotKey(HotKey.CTRL_L_MASK), HotKeyRegistry.getCurrentHotKey());

        simulateNativeKeyPress(HotKeyRegistry.getNativeKeyListener(), HotKey.VC_SHIFT, HotKey.CTRL_L_MASK | HotKey.SHIFT_L_MASK);
        Assertions.assertEquals(new HotKey(HotKey.CTRL_L_MASK | HotKey.SHIFT_L_MASK), HotKeyRegistry.getCurrentHotKey());

        simulateNativeKeyPress(HotKeyRegistry.getNativeKeyListener(), HotKey.VC_R, HotKey.CTRL_L_MASK | HotKey.SHIFT_L_MASK);
        Assertions.assertEquals(new HotKey(HotKey.CTRL_L_MASK | HotKey.SHIFT_L_MASK, HotKey.VC_R), HotKeyRegistry.getCurrentHotKey());

        // release all the keys

        simulateNativeKeyRelease(HotKeyRegistry.getNativeKeyListener(), HotKey.VC_CONTROL, HotKey.SHIFT_L_MASK);
        Assertions.assertEquals(new HotKey(HotKey.SHIFT_L_MASK, HotKey.VC_R), HotKeyRegistry.getCurrentHotKey());

        simulateNativeKeyRelease(HotKeyRegistry.getNativeKeyListener(), HotKey.VC_SHIFT, HotKey.NO_MASK);
        Assertions.assertEquals(new HotKey(HotKey.NO_MASK, HotKey.VC_R), HotKeyRegistry.getCurrentHotKey());

        simulateNativeKeyRelease(HotKeyRegistry.getNativeKeyListener(), HotKey.VC_R, HotKey.NO_MASK);
        Assertions.assertEquals(new HotKey(), HotKeyRegistry.getCurrentHotKey());
    }

    @Test
    void firesHotKeyListeners() {
        HotKeyRegistry.registerHotKeyListener(hotKey, hotKeyListener);

        // ensure that the initial fired count is 0
        Assertions.assertEquals(0, ((HotKeyListenerImplementation) hotKeyListener).getFiredCount());

        // simple hot key press

        simulateNativeKeyPress(HotKeyRegistry.getNativeKeyListener(), HotKey.VC_CONTROL, HotKey.CTRL_L_MASK);
        Assertions.assertEquals(0, ((HotKeyListenerImplementation) hotKeyListener).getFiredCount());

        simulateNativeKeyPress(HotKeyRegistry.getNativeKeyListener(), HotKey.VC_SHIFT, HotKey.SHIFT_L_MASK);
        Assertions.assertEquals(0, ((HotKeyListenerImplementation) hotKeyListener).getFiredCount());

        simulateNativeKeyPress(HotKeyRegistry.getNativeKeyListener(), HotKey.VC_R, HotKey.CTRL_L_MASK | HotKey.SHIFT_L_MASK);
        Assertions.assertEquals(1, ((HotKeyListenerImplementation) hotKeyListener).getFiredCount());

        // re-press the last key

        simulateNativeKeyRelease(HotKeyRegistry.getNativeKeyListener(), HotKey.VC_R, HotKey.CTRL_L_MASK | HotKey.SHIFT_L_MASK);
        Assertions.assertEquals(1, ((HotKeyListenerImplementation) hotKeyListener).getFiredCount());

        simulateNativeKeyPress(HotKeyRegistry.getNativeKeyListener(), HotKey.VC_R, HotKey.CTRL_L_MASK | HotKey.SHIFT_L_MASK);
        Assertions.assertEquals(2, ((HotKeyListenerImplementation) hotKeyListener).getFiredCount());

        // release all the keys

        simulateNativeKeyRelease(HotKeyRegistry.getNativeKeyListener(), HotKey.VC_CONTROL, HotKey.SHIFT_L_MASK);
        Assertions.assertEquals(2, ((HotKeyListenerImplementation) hotKeyListener).getFiredCount());

        simulateNativeKeyRelease(HotKeyRegistry.getNativeKeyListener(), HotKey.VC_SHIFT, HotKey.NO_MASK);
        Assertions.assertEquals(2, ((HotKeyListenerImplementation) hotKeyListener).getFiredCount());

        simulateNativeKeyRelease(HotKeyRegistry.getNativeKeyListener(), HotKey.VC_R, HotKey.NO_MASK);
        Assertions.assertEquals(2, ((HotKeyListenerImplementation) hotKeyListener).getFiredCount());
    }
}
