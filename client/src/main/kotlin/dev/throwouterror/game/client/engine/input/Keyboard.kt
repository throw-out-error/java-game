package dev.throwouterror.game.client.engine.input

enum class Keyboard {
    NONE,  // Row 1
    ESCAPE, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, PRINT_SCREEN, SCROLL_LOCK, PAUSE,  // Row 2
    GRAVE_ACCENT, NUM1, NUM2, NUM3, NUM4, NUM5, NUM6, NUM7, NUM8, NUM9, NUM0, DASH, EQUALS, BACKSPACE,  // Alphabet
    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z,  // Row 3
    TAB, LEFT_BRACKET, RIGHT_BRACKET, BACKSLASH,  // Row 4
    CAPS_LOCK, SEMICOLON, APOSTROPHE, ENTER,  // Row 5
    RIGHT_SHIFT, COMMA, PERIOD, SLASH, LEFT_SHIFT,  // Row 6
    LEFT_CONTROL, LEFT_SUPER,  // Windows key
    LEFT_ALT, SPACE, RIGHT_ALT, RIGHT_SUPER,  // Windows key
    RIGHT_CONTROL,  // 6 Keys
    INSERT, HOME, PAGE_UP, DELETE, END, PAGE_DOWN,  // Arrow Keys
    DOWN, LEFT, RIGHT, UP,  // Numberpad Row 1
    NUM_LOCK, KP_DIVIDE, KP_MULTIPLY, KP_SUBTRACT,  // Numberpad Row 2
    KP_ADD,  // Numberpad Numbers
    KP_0, KP_1, KP_2, KP_3, KP_4, KP_5, KP_6, KP_7, KP_8, KP_9,  // Numberpad Row 3
    KP_DECIMAL, KP_ENTER,  // Max enum value
    MAX_KEYBOARD
}