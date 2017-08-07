public class Vector3D extends Vector2D {

    protected int z;

    public Vector3D() {
        this(1, 1, 1);
    }

    public Vector3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    @Override
    public void multiply(int s) {
        super.multiply(s);
        this.z *= s;
    }

}