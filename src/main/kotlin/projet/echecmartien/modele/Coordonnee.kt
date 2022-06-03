package projet.echecmartien.modele

class Coordonnee(private var x: Int, private var y: Int) {

    /**
     *@return la coordonnée en x
     */
    fun getX(): Int = this.x


    /**
     *@return la coordonnée en y
     */
    fun getY(): Int = this.y

    override fun equals(other: Any?): Boolean {
        if (other === null)
            return false

        if (other !is Coordonnee)
            return false

        if (other === this)
            return true

        return (this.x == other.getX()) && (this.y == other.getY())
    }

    override fun toString():String = "(${x}, $y)"
}
