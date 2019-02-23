package org.jhotkey;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HotKey {
    public static final int VC_ESCAPE = 0x0001;

    public static final int VC_F1 = 0x003B;
    public static final int VC_F2 = 0x003C;
    public static final int VC_F3 = 0x003D;
    public static final int VC_F4 = 0x003E;
    public static final int VC_F5 = 0x003F;
    public static final int VC_F6 = 0x0040;
    public static final int VC_F7 = 0x0041;
    public static final int VC_F8 = 0x0042;
    public static final int VC_F9 = 0x0043;
    public static final int VC_F10 = 0x0044;
    public static final int VC_F11 = 0x0057;
    public static final int VC_F12 = 0x0058;

    public static final int VC_F13 = 0x005B;
    public static final int VC_F14 = 0x005C;
    public static final int VC_F15 = 0x005D;
    public static final int VC_F16 = 0x0063;
    public static final int VC_F17 = 0x0064;
    public static final int VC_F18 = 0x0065;
    public static final int VC_F19 = 0x0066;
    public static final int VC_F20 = 0x0067;
    public static final int VC_F21 = 0x0068;
    public static final int VC_F22 = 0x0069;
    public static final int VC_F23 = 0x006A;
    public static final int VC_F24 = 0x006B;

    public static final int VC_BACKQUOTE = 0x0029;

    public static final int VC_1 = 0x0002;
    public static final int VC_2 = 0x0003;
    public static final int VC_3 = 0x0004;
    public static final int VC_4 = 0x0005;
    public static final int VC_5 = 0x0006;
    public static final int VC_6 = 0x0007;
    public static final int VC_7 = 0x0008;
    public static final int VC_8 = 0x0009;
    public static final int VC_9 = 0x000A;
    public static final int VC_0 = 0x000B;

    public static final int VC_MINUS = 0x000C;    // '-'
    public static final int VC_EQUALS = 0x000D;    // '='
    public static final int VC_BACKSPACE = 0x000E;

    public static final int VC_TAB = 0x000F;
    public static final int VC_CAPS_LOCK = 0x003A;

    public static final int VC_A = 0x001E;
    public static final int VC_B = 0x0030;
    public static final int VC_C = 0x002E;
    public static final int VC_D = 0x0020;
    public static final int VC_E = 0x0012;
    public static final int VC_F = 0x0021;
    public static final int VC_G = 0x0022;
    public static final int VC_H = 0x0023;
    public static final int VC_I = 0x0017;
    public static final int VC_J = 0x0024;
    public static final int VC_K = 0x0025;
    public static final int VC_L = 0x0026;
    public static final int VC_M = 0x0032;
    public static final int VC_N = 0x0031;
    public static final int VC_O = 0x0018;
    public static final int VC_P = 0x0019;
    public static final int VC_Q = 0x0010;
    public static final int VC_R = 0x0013;
    public static final int VC_S = 0x001F;
    public static final int VC_T = 0x0014;
    public static final int VC_U = 0x0016;
    public static final int VC_V = 0x002F;
    public static final int VC_W = 0x0011;
    public static final int VC_X = 0x002D;
    public static final int VC_Y = 0x0015;
    public static final int VC_Z = 0x002C;

    public static final int VC_OPEN_BRACKET = 0x001A;    // '['
    public static final int VC_CLOSE_BRACKET = 0x001B;    // ']'
    public static final int VC_BACK_SLASH = 0x002B;    // '\'

    public static final int VC_SEMICOLON = 0x0027;    // ';'
    public static final int VC_QUOTE = 0x0028;
    public static final int VC_ENTER = 0x001C;

    public static final int VC_COMMA = 0x0033;    // ','
    public static final int VC_PERIOD = 0x0034;    // '.'
    public static final int VC_SLASH = 0x0035;    // '/'

    public static final int VC_SPACE = 0x0039;

    public static final int VC_PRINTSCREEN = 0x0E37;
    public static final int VC_SCROLL_LOCK = 0x0046;
    public static final int VC_PAUSE = 0x0E45;

    public static final int VC_INSERT = 0x0E52;
    public static final int VC_DELETE = 0x0E53;
    public static final int VC_HOME = 0x0E47;
    public static final int VC_END = 0x0E4F;
    public static final int VC_PAGE_UP = 0x0E49;
    public static final int VC_PAGE_DOWN = 0x0E51;

    public static final int VC_UP = 0xE048;
    public static final int VC_LEFT = 0xE04B;
    public static final int VC_CLEAR = 0xE04C;
    public static final int VC_RIGHT = 0xE04D;
    public static final int VC_DOWN = 0xE050;

    public static final int VC_NUM_LOCK = 0x0045;
    public static final int VC_SEPARATOR = 0x0053;

    public static final int VC_SHIFT = 0x002A;
    public static final int VC_CONTROL = 0x001D;
    public static final int VC_ALT = 0x0038;    // Option or Alt Key
    public static final int VC_META = 0x0E5B;    // Windows or Command Key
    public static final int VC_CONTEXT_MENU = 0x0E5D;

    public static final int VC_POWER = 0xE05E;
    public static final int VC_SLEEP = 0xE05F;
    public static final int VC_WAKE = 0xE063;

    public static final int VC_MEDIA_PLAY = 0xE022;
    public static final int VC_MEDIA_STOP = 0xE024;
    public static final int VC_MEDIA_PREVIOUS = 0xE010;
    public static final int VC_MEDIA_NEXT = 0xE019;
    public static final int VC_MEDIA_SELECT = 0xE06D;
    public static final int VC_MEDIA_EJECT = 0xE02C;

    public static final int VC_VOLUME_MUTE = 0xE020;
    public static final int VC_VOLUME_UP = 0xE030;
    public static final int VC_VOLUME_DOWN = 0xE02E;

    public static final int VC_APP_MAIL = 0xE06C;
    public static final int VC_APP_CALCULATOR = 0xE021;
    public static final int VC_APP_MUSIC = 0xE03C;
    public static final int VC_APP_PICTURES = 0xE064;

    public static final int VC_BROWSER_SEARCH = 0xE065;
    public static final int VC_BROWSER_HOME = 0xE032;
    public static final int VC_BROWSER_BACK = 0xE06A;
    public static final int VC_BROWSER_FORWARD = 0xE069;
    public static final int VC_BROWSER_STOP = 0xE068;
    public static final int VC_BROWSER_REFRESH = 0xE067;
    public static final int VC_BROWSER_FAVORITES = 0xE066;

    public static final int VC_KATAKANA = 0x0070;
    public static final int VC_UNDERSCORE = 0x0073;
    public static final int VC_FURIGANA = 0x0077;
    public static final int VC_KANJI = 0x0079;
    public static final int VC_HIRAGANA = 0x007B;
    public static final int VC_YEN = 0x007D;

    public static final int VC_SUN_HELP = 0xFF75;

    public static final int VC_SUN_STOP = 0xFF78;
    public static final int VC_SUN_PROPS = 0xFF76;
    public static final int VC_SUN_FRONT = 0xFF77;
    public static final int VC_SUN_OPEN = 0xFF74;
    public static final int VC_SUN_FIND = 0xFF7E;
    public static final int VC_SUN_AGAIN = 0xFF79;
    public static final int VC_SUN_UNDO = 0xFF7A;
    public static final int VC_SUN_COPY = 0xFF7C;
    public static final int VC_SUN_INSERT = 0xFF7D;
    public static final int VC_SUN_CUT = 0xFF7B;

    public static final int VC_UNDEFINED = 0x0000;

    public static final int NO_MASK = 0;
    public static final int SHIFT_L_MASK = 1 << 0;
    public static final int CTRL_L_MASK = 1 << 1;
    public static final int META_L_MASK = 1 << 2;
    public static final int ALT_L_MASK = 1 << 3;
    public static final int SHIFT_R_MASK = 1 << 4;
    public static final int CTRL_R_MASK = 1 << 5;
    public static final int META_R_MASK = 1 << 6;
    public static final int ALT_R_MASK = 1 << 7;
    public static final int SHIFT_MASK = SHIFT_L_MASK | SHIFT_R_MASK;
    public static final int CTRL_MASK = CTRL_L_MASK | CTRL_R_MASK;
    public static final int META_MASK = META_L_MASK | META_R_MASK;
    public static final int ALT_MASK = ALT_L_MASK | ALT_R_MASK;

    public static final int NUM_LOCK_MASK = 1 << 13;
    public static final int CAPS_LOCK_MASK = 1 << 14;
    public static final int SCROLL_LOCK_MASK = 1 << 15;

    private List<Integer> keyList;
    private int modifiers;

    public HotKey() {
        setModifiers(NO_MASK);
        setKeys(new ArrayList<>());
    }

    public HotKey(int modifiers, int... keys) {
        setModifiers(modifiers);
        setKeys(keys);
    }

    public int getModifiers() {
        return this.modifiers;
    }

    public List<Integer> getKeys() {
        return this.keyList;
    }

    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }

    public void setKeys(List<Integer> keys) {
        keyList = new ArrayList<>();

        Iterator<Integer> iterator = keys.iterator();
        while (iterator.hasNext()) {
            addKey(iterator.next());
        }
    }

    public void setKeys(int... keys) {
        setKeys(IntStream.of(keys).boxed().collect(Collectors.toList()));
    }

    public void addModifer(int modifier) {
        this.modifiers |= modifier;
    }

    public void removeModifer(int modifier) {
        this.modifiers &= ~modifier;
    }

    public void addKey(int keyCode) {
        if (!keyList.contains(keyCode)) {
            keyList.add(keyCode);
        }
    }

    public void removeKey(int keyCode) {
        if (keyList.contains(keyCode)) {
            keyList.remove(Integer.valueOf(keyCode));
        }
    }

    public boolean isSimilarTo(HotKey hotKey) {
        boolean isSimilar = true;

        // check for 'control' mask

        if (((getModifiers() & HotKey.CTRL_MASK) == HotKey.CTRL_MASK
                        && ((hotKey.getModifiers() & HotKey.CTRL_MASK) != HotKey.CTRL_MASK
                        && (hotKey.getModifiers() & HotKey.CTRL_L_MASK) != HotKey.CTRL_L_MASK
                        && (hotKey.getModifiers() & HotKey.CTRL_R_MASK) != HotKey.CTRL_R_MASK))
                || ((hotKey.getModifiers() & HotKey.CTRL_MASK) == HotKey.CTRL_MASK
                        && ((getModifiers() & HotKey.CTRL_MASK) != HotKey.CTRL_MASK
                        && (getModifiers() & HotKey.CTRL_L_MASK) != HotKey.CTRL_L_MASK
                        && (getModifiers() & HotKey.CTRL_R_MASK) != HotKey.CTRL_R_MASK))) {
            isSimilar = false;
        }

        // check for 'shift' mask

        if (((getModifiers() & HotKey.SHIFT_MASK) == HotKey.SHIFT_MASK
                && ((hotKey.getModifiers() & HotKey.SHIFT_MASK) != HotKey.SHIFT_MASK
                && (hotKey.getModifiers() & HotKey.SHIFT_L_MASK) != HotKey.SHIFT_L_MASK
                && (hotKey.getModifiers() & HotKey.SHIFT_R_MASK) != HotKey.SHIFT_R_MASK))
                || ((hotKey.getModifiers() & HotKey.SHIFT_MASK) == HotKey.SHIFT_MASK
                && ((getModifiers() & HotKey.SHIFT_MASK) != HotKey.SHIFT_MASK
                && (getModifiers() & HotKey.SHIFT_L_MASK) != HotKey.SHIFT_L_MASK
                && (getModifiers() & HotKey.SHIFT_R_MASK) != HotKey.SHIFT_R_MASK))) {
            isSimilar = false;
        }

        // check for 'alt' mask

        if (((getModifiers() & HotKey.ALT_MASK) == HotKey.ALT_MASK
                && ((hotKey.getModifiers() & HotKey.ALT_MASK) != HotKey.ALT_MASK
                && (hotKey.getModifiers() & HotKey.ALT_L_MASK) != HotKey.ALT_L_MASK
                && (hotKey.getModifiers() & HotKey.ALT_R_MASK) != HotKey.ALT_R_MASK))
                || ((hotKey.getModifiers() & HotKey.ALT_MASK) == HotKey.ALT_MASK
                && ((getModifiers() & HotKey.ALT_MASK) != HotKey.ALT_MASK
                && (getModifiers() & HotKey.ALT_L_MASK) != HotKey.ALT_L_MASK
                && (getModifiers() & HotKey.ALT_R_MASK) != HotKey.ALT_R_MASK))) {
            isSimilar = false;
        }

        // check for 'meta' mask

        if (((getModifiers() & HotKey.META_MASK) == HotKey.META_MASK
                && ((hotKey.getModifiers() & HotKey.META_MASK) != HotKey.META_MASK
                && (hotKey.getModifiers() & HotKey.META_L_MASK) != HotKey.META_L_MASK
                && (hotKey.getModifiers() & HotKey.META_R_MASK) != HotKey.META_R_MASK))
                || ((hotKey.getModifiers() & HotKey.META_MASK) == HotKey.META_MASK
                && ((getModifiers() & HotKey.META_MASK) != HotKey.META_MASK
                && (getModifiers() & HotKey.META_L_MASK) != HotKey.META_L_MASK
                && (getModifiers() & HotKey.META_R_MASK) != HotKey.META_R_MASK))) {
            isSimilar = false;
        }

        isSimilar = isSimilar && hotKey.keyList.equals(keyList);

        return isSimilar;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof HotKey && ((HotKey) obj).keyList.equals(keyList) && ((HotKey) obj).modifiers == modifiers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyList, modifiers);
    }
}
