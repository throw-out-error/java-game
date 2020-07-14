#version 330 core
#extension GL_ARB_separate_shader_objects : enable
// Input vertex data, different for all executions of this shader.
layout (location = 0) in vec3 position;
layout (location = 1) in vec3 scale;

out vec3 color;

void main()
{
    gl_Position = vec4(position, 1.0);
    color = position;
}