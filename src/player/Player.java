package player;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
    private final Circle shape;
    private Point2D velocity;
    private boolean onGround = false;
    private static final double GRAVITY = 0.5;
    private static final int PLAYER_SPEED = 4;
    private static final int JUMP_STRENGTH = -11;
    private boolean canJump = false;

    public Player (double x, double y) {
        shape = new Circle(x, y, 20, Color.BLUE);
        velocity = new Point2D(0, 0);
    }

    public Circle getShape() {
        return shape;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public int GetPlayerSpeed() {
        return PLAYER_SPEED;
    }

    public int getJumpStrength() {
        return JUMP_STRENGTH;
    }

    public boolean isCanJump() {
        return canJump;
    }

    public void update() {
        shape.setCenterX(shape.getCenterX() + velocity.getX());
        shape.setCenterY(shape.getCenterY() + velocity.getY());

        velocity = velocity.add(0, GRAVITY);

        if (shape.getCenterY() >= (400 - ((double) 400 / 3))) {
            shape.setCenterY(400 - ((double) 400 / 3));
            velocity = new Point2D(velocity.getX(), 0);
            onGround = true;
            canJump = true;
        } else {
            onGround = false;
            canJump = false;
        }
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
}
