#version 300 es

in mediump vec3 passColor;
in mediump vec2 passTextureCoord;

out mediump vec4 outColor;

uniform sampler2D tex;

void main() {
    outColor = vec4(passColor, 1.0) + texture(tex, passTextureCoord);
}