package com.t03g06.model.menu;

import com.t03g06.model.menu.MenuModel;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuModelTest {

    @Test
    void testGetOptions() {
        MenuModel menu = new MenuModel();
        List<String> expectedOptions = List.of("PLAY", "HOW TO PLAY", "LEADERBOARD", "QUIT");
        assertEquals(expectedOptions, menu.getOptions());
    }

    @Test
    void testGetCurrentOptionInitial() {
        MenuModel menu = new MenuModel();
        assertEquals(0, menu.getCurrentOption());
    }

    @Test
    void testNextOption() {
        MenuModel menu = new MenuModel();

        menu.nextOption();
        assertEquals(1, menu.getCurrentOption());

        menu.nextOption();
        assertEquals(2, menu.getCurrentOption());

        menu.nextOption();
        assertEquals(3, menu.getCurrentOption());

        menu.nextOption();
        assertEquals(0, menu.getCurrentOption());
    }

    @Test
    void testPreviousOption() {
        MenuModel menu = new MenuModel();

        menu.previousOption();
        assertEquals(3, menu.getCurrentOption());

        menu.previousOption();
        assertEquals(2, menu.getCurrentOption());

        menu.previousOption();
        assertEquals(1, menu.getCurrentOption(), "Erro ao voltar para a opção anterior (2 -> 1).");

        menu.previousOption();
        assertEquals(0, menu.getCurrentOption(), "Erro ao voltar para a opção anterior (1 -> 0).");
    }

    @Test
    void testFullCycleNextAndPrevious() {
        MenuModel menu = new MenuModel();

        for (int i = 0; i < menu.getOptions().size(); i++) {
            menu.nextOption();
        }
        assertEquals(0, menu.getCurrentOption());

        for (int i = 0; i < menu.getOptions().size(); i++) {
            menu.previousOption();
        }
        assertEquals(0, menu.getCurrentOption());
    }
}
