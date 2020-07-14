package dev.throwouterror.game.client.engine.material

import dev.throwouterror.util.math.Tensor
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL13
import org.newdawn.slick.opengl.Texture
import org.newdawn.slick.opengl.TextureLoader
import java.io.IOException


class Material(val color: Tensor, private val path: String? = "") {
    private var texture: Texture? = null
    var width = 0f
        private set
    var height = 0f
        private set
    var textureID = 0
        private set

    fun create() {
        try {
            path?.let {
                if (it.isNotBlank() && it.isNotEmpty()) {
                    texture = TextureLoader.getTexture(it.split("[.]").toTypedArray()[1], Material::class.java.getResourceAsStream(path), GL11.GL_NEAREST)
                    width = texture!!.width
                    height = texture!!.height
                    textureID = texture!!.textureID
                }
            }
        } catch (e: IOException) {
            System.err.println("Can't find texture at $path")
        }
    }

    fun destroy() {
        GL13.glDeleteTextures(textureID)
    }

    fun hasTexture(): Boolean {
        return path != null && path.isNotBlank() && path.isNotEmpty()
    }

}