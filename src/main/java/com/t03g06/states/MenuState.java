package com.t03g06.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.controller.Controller;
import com.t03g06.controller.MenuController;
import com.t03g06.model.MenuModel;
import com.t03g06.view.MenuViewer;
import com.t03g06.view.Viewer;

public class   MenuState extends State<MenuModel> {
    public MenuState(MenuModel model, TextGraphics tg) {
        super(model, tg);
    }

    @Override
    protected Viewer getViewer() {
        return new MenuViewer(getModel(), getTextGraphics());
    }

    @Override
    protected Controller getController() {
        return new MenuController(getModel());
    }
}
