public class Vector2D implements Vector {

    protected int x;
    protected int y;

    public Vector2D() {
	
        this(1, 1);
    }

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void multiply(int s) {
        this.x *= s;
        this.y *= s;
    }

}