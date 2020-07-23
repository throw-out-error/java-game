#version 300 es
#extension GL_ARB_separate_shader_objects : enable

// Input vertex data, different for all executions of this shader.
in vec3 position;
in vec3 color;
in vec2 textureCoord;

uniform vec3 scale;

out vec3 passColor;
out vec2 passTextureCoord;

void main()
{
    gl_Position = vec4(position, 1.0) * vec4(scale, 1);
    passColor = color;
    passTextureCoord = textureCoord;
}