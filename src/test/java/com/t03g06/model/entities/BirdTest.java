package com.t03g06.model.entities;

import com.t03g06.model.GameConstants;
import com.t03g06.model.entities.Bird;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BirdTest {

    private Bird bird;

    @BeforeEach
    public void setUp() {
        bird = new Bird(10);
    }

    @Test
    public void testGetY() {
        Assertions.assertEquals(10, bird.getY());
    }

    @Test
    public void TestSetY(){
        bird.setY(18);
        Assertions.assertEquals(18,bird.getY());
    }

    @Test
    public void testReset() {
        bird.jump(GameConstants.JUMP_HEIGHT);
        bird.reset();
        Assertions.assertEquals(10, bird.getY());
    }

    @Test
    public void testJump() {
        bird.jump(GameConstants.JUMP_HEIGHT);
        Assertions.assertEquals(10- GameConstants.JUMP_HEIGHT, bird.getY());
    }

    @Test
    public void testApplyGravity() {
        bird.applyGravity(GameConstants.GRAVITY);
        Assertions.assertEquals(10 + GameConstants.GRAVITY, bird.getY());
    }
}
