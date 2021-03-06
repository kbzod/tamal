package us.havanki.tamal.entity;

/**
 * A mobile entity.
 */
public abstract class Mob extends Entity {

    private int xKnockback, yKnockback;
    private long walkDist;
    private Direction dir;

    /**
     * Creates a new mob.
     */
    protected Mob() {
        this(8, 8, 4, 3, Direction.DOWN);
    }
    /**
     * Creates a new mob.
     *
     * @param x x coordinate of entity
     * @param y y coordinate of entity
     * @param xr x radius of entity
     * @param yr y radius of entity
     * @param dir initial direction
     */
    protected Mob (int x, int y, int xr, int yr, Direction dir) {
        super (x, y, xr, yr);
        this.dir = dir;
        xKnockback = yKnockback = 0;
        walkDist = 0L;
    }

    /**
     * Gets the x knockback value for this entity. This dictates how many
     * x-coordinate pixels the entity is being knocked back.
     *
     * @return x knockback
     */
    public int getXKnockback() { return xKnockback; }
    /**
     * Sets the x knockback value for this entity.
     *
     * @param x x knockback
     */
    public void setXKnockback (int x) { xKnockback = x; }
    /**
     * Gets the y knockback value for this entity. This dictates how many
     * y-coordinate pixels the entity is being knocked back.
     *
     * @return y knockback
     */
    public int getYKnockback() { return yKnockback; }
    /**
     * Sets the y knockback value for this entity.
     *
     * @param y y knockback
     */
    public void setYKnockback (int y) { yKnockback = y; }
    /**
     * Gets the lifetime walk distance for this entity.
     *
     * @return walk distance
     */
    public long getWalkDistance() { return walkDist; }
    /**
     * Gets this entity's direction.
     *
     * @return direction
     */
    public Direction getDirection() { return dir; }

    @Override
    public void tick() {}  // was for health management

    @Override
    public boolean move (int xa, int ya) {
        // First, move the mob acoording to its knockback.
        if (xKnockback < 0) {
            move2 (-1, 0);
            xKnockback++;
        } else if (xKnockback > 0) {
            move2 (1, 0);
            xKnockback--;
        } else if (yKnockback < 0) {
            move2 (0, -1);
            yKnockback++;
        } else if (yKnockback > 0) {
            move2 (0, 1);
            yKnockback--;
        }

        // Increase the walk distance and update direction.
        if (xa != 0 || ya != 0) {
            walkDist++;
            if (xa < 0) { dir = Direction.LEFT; }
            else if (xa > 0) { dir = Direction.RIGHT; }
            if (ya < 0) { dir = Direction.UP; }
            else if (ya > 0) { dir = Direction.DOWN; }
        }

        // OK, now you can move.
        return super.move (xa, ya);
    }

    @Override
    public boolean blocks (Entity e) {
        return e.isBlockableBy (this);
    }
}
