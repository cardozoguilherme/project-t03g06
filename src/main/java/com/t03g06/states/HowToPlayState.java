package com.t03g06.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.controller.Controller;
import com.t03g06.controller.menu.HowToPlayController;
import com.t03g06.model.menu.HowToPlayModel;
import com.t03g06.view.menu.HowToPlayViewer;
import com.t03g06.view.Viewer;

public class HowToPlayState extends State<HowToPlayModel> {
    public HowToPlayState(HowToPlayModel model, TextGraphics tg) {
        super(model, tg);
    }

    @Override
    public Viewer getViewer() {
        return new HowToPlayViewer(getModel(), getTextGraphics());
    }

    @Override
    public Controller getController() {
        return new HowToPlayController();
    }
}
