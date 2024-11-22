package com.t03g06.model;

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
    public void getY() {
        Assertions.assertEquals(10, bird.getY());
    }

    @Test
    public void Reset() {
        bird.jump();
        bird.reset();
        Assertions.assertEquals(10, bird.getY());
    }

    @Test
    public void Jump() {
        bird.jump();
        Assertions.assertEquals(7, bird.getY());
    }

    @Test
    public void ApplyGravity() {
        bird.applyGravity();
        Assertions.assertEquals(11, bird.getY());
    }

}
