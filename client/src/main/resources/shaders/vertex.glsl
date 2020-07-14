#version 330 core
#extension GL_ARB_separate_shader_objects : enable
// Input vertex data, different for all executions of this shader.
layout (location = 0) in vec3 position;
layout (location = 1) in vec3 scale;

void main()
{
    gl_Position = vec4(position, 1.0) * vec4(scale, 1.0);
}