package group24.piazzapanic.game;

import com.badlogic.gdx.Gdx;

import group24.piazzapanic.Base;
import group24.piazzapanic.levelElements.stations.Station;

public class Physics {

    /**
     * Checks if the player is close to a station. 
     * @param station The station which we want to check if it's near.
     * @param player The player.
     * @return True if player near the station, false otherwise.
     */
    public static boolean isNear(Station station, Player player) {
        double deltaX = player.x - station.getX();
        double deltaY = player.y - station.getY();
        double threshold = (station.getWidth()) / 3; // Station width in pixels divided by three
        if (Math.abs(deltaX) <= threshold || Math.abs(deltaY) <= threshold) { // If closer than Threshold
            // if (Player.direction ==   TODO implement me. 
            System.out.println("Poggers, you are indeed near.");
            return true;
        }
        return false;
    }

    /**
     * Checks if the given coordinates are solid (i.e. a station is there).
     * @param x Decimal x position.
     * @param y Decimal y position.
     * @return Whether there is not a station at grid[floor(x)][floor(y)].
     */
    public static boolean isSolid(double x, double y) {
        x = Math.floor(x);
        y = Math.floor(y);

        if (x < 0 || y < 0 || x >= GameData.level.getWidth() || y >= GameData.level.getHeight()) {
            return true;
        }

        return GameData.level.getStation((int) x, (int) y) != null;
    }

    
    /** 
     * @param player
     * @param delta
     */
    public static void playerMovement(Player player, float delta) {
        /*
        We know:
        U (player's current velocity)
        A (acceleration, this is a constant)
        T (time since last frame)
        
        We don't know S and we're trying to find V.
        
        V = U + AT.
        
        (new vel)    (old vel)
        player_vel = player_vel + acceleration * delta;
        */
        if (Gdx.input.isKeyPressed(Base.UP_KEY)) {
            // This is just the implementation of the formula above.
            player.y_vel += Player.acceleration * delta;

            if  (player.direction != Player.facing.UP){
                player.direction = Player.facing.UP;
                player.animation.setAnimation("chef/chef_walk_back.png", 6, 1, 6);
            }
        } else if (Gdx.input.isKeyPressed(Base.DOWN_KEY)) {
            player.y_vel -= Player.acceleration * delta;

            if  (player.direction != Player.facing.DOWN){
                player.direction = Player.facing.DOWN;
                player.animation.setAnimation("chef/chef_walk_front.png", 6, 1, 6);
            }
        } else {
            // If the player is not pressing up or down, we need to deaccelerate in that direction.
            if (player.y_vel > 0) {
                player.y_vel -= Player.deacceleration * delta;
            } else if (player.y_vel < 0) {
                player.y_vel += Player.deacceleration * delta;
            }

            // If the player has decelerated past a certain point, stop them moving.
            if (Math.abs(player.y_vel) < Player.minSpeed && Math.abs(player.y_vel) != 0) {
                player.y_vel = 0;
                if  (player.direction == Player.facing.UP){
                    player.animation.setAnimation("chef/chef_idle_back.png", 6, 1, 6);
                }
                else if (player.direction == Player.facing.DOWN){
                    player.animation.setAnimation("chef/chef_idle_front.png", 6, 1, 6);
                }
            }
        }

        if (Gdx.input.isKeyPressed(Base.LEFT_KEY)) {
            player.x_vel -= Player.acceleration * delta;

            if  (player.direction != Player.facing.LEFT){
                player.direction = Player.facing.LEFT;
                player.animation.setAnimation("chef/chef_walk_left.png", 6, 1, 6);
            }
        } else if (Gdx.input.isKeyPressed(Base.RIGHT_KEY)) {
            player.x_vel += Player.acceleration * delta;

            if  (player.direction != Player.facing.RIGHT){
                player.direction = Player.facing.RIGHT;
                player.animation.setAnimation("chef/chef_walk_right.png", 6, 1, 6);
            }
        } else {
            // If the player is not pressing left or right, we need to deaccelerate in that direction.
            if (player.x_vel > 0) {
                player.x_vel -= Player.deacceleration * delta;
            } else if (player.x_vel < 0) {
                player.x_vel += Player.deacceleration * delta;
            }

            if (Math.abs(player.x_vel) < Player.minSpeed && Math.abs(player.x_vel) != 0) {
                player.x_vel = 0;
                if  (player.direction == Player.facing.RIGHT){
                    player.animation.setAnimation("chef/chef_idle_right.png", 6, 1, 6);
                }
                else if (player.direction == Player.facing.LEFT){
                    player.animation.setAnimation("chef/chef_idle_left.png", 6, 1, 6);
                }
            }
        }

        // Prevent the player from moving faster than the maximum speed.
        if (player.x_vel > Player.maxSpeed) {
            player.x_vel = Player.maxSpeed;
        } else if (player.x_vel < -Player.maxSpeed) {
            player.x_vel = -Player.maxSpeed;
        }
        if (player.y_vel > Player.maxSpeed) {
            player.y_vel = Player.maxSpeed;
        } else if (player.y_vel < -Player.maxSpeed) {
            player.y_vel = -Player.maxSpeed;
        }

        // How far the player will move in this frame.
        double delta_x = player.x_vel * delta;
        double delta_y = player.y_vel * delta;

        //System.out.println(player.x + ", " + player.y);

        // Work out collisions with stations in the level.
        if (delta_x > 0 &&
                (isSolid(player.right() + delta_x + Player.movementEpsilon, player.top() + delta_y)
                        || isSolid(player.right() + delta_x + Player.movementEpsilon, player.bottom() + delta_y))) {
            // If you are moving right, *and* there is something obstructing any of the two
            // points on the right most edge, then decrease delta_x and set x_vel to 0 so
            // that you stop just at the edge of the object.

            player.x_vel = 0;
            delta_x = Math.max(Math.floor(player.right() + delta_x) - player.right() - Player.movementEpsilon, 0);
            //                 ^ ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ ^
            // This is the x position of the obstacle's edge.
        }
        if (delta_x < 0 && (isSolid(player.left() + delta_x - Player.movementEpsilon, player.top() + delta_y)
                || isSolid(player.left() + delta_x - Player.movementEpsilon, player.bottom() + delta_y))) {
            // Left
            player.x_vel = 0;
            delta_x = Math.min(Math.ceil(player.left() + delta_x) - player.left() + Player.movementEpsilon, 0);
        }
        if (delta_y > 0 && (isSolid(player.left() + delta_x, player.top() + delta_y + Player.movementEpsilon)
                || isSolid(player.right() + delta_x, player.top() + delta_y + Player.movementEpsilon))) {
            // Up
            player.y_vel = 0;
            delta_y = Math.max(Math.floor(player.top() + delta_y) - player.top() - Player.movementEpsilon, 0);
        }
        if (delta_y < 0 && (isSolid(player.left() + delta_x, player.bottom() + delta_y - Player.movementEpsilon)
                || isSolid(player.right() + delta_x, player.bottom() + delta_y - Player.movementEpsilon))) {
            // Down
            player.y_vel = 0;
            delta_y = Math.min(Math.ceil(player.bottom() + delta_y) - player.bottom() + Player.movementEpsilon, 0);
        }

        // Actually modify the player's position.
        player.x += delta_x;
        player.y += delta_y;
    }
}
