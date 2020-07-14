package dev.throwouterror.game.client.engine.mesh

import java.io.InputStream

/**
 * @author Throw Out Error (https://throw-out-error.dev)
 */
class EmptyMesh : Mesh() {
    override fun decode(stream: InputStream, onSuccess: (vertices: MutableList<Float>, uvCoordinates: FloatArray, normals: FloatArray, indices: IntArray) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}