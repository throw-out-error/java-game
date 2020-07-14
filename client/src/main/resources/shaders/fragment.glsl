#version 330 core


in vec3 passColor;
in vec2 passTextureCoord;

out vec4 outColor;

uniform sampler2D tex;

void main() {
    outColor = vec4(passColor, 1.0) + texture(tex, passTextureCoord);
}