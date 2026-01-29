package com.cgelectrosound.gestion.utils;

import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.Stack;

public class NavigationManager {

    private static NavigationManager instance;
    private Scene mainScene;

    private final Stack<Parent> backStack = new Stack<>();
    private final Stack<Parent> forwardStack = new Stack<>();

    private Parent currentView;

    private NavigationManager() {}

    public static NavigationManager getInstance() {
        if (instance == null) {
            instance = new NavigationManager();
        }
        return instance;
    }

    public void setMainScene(Scene scene) {
        this.mainScene = scene;
    }

    public void setInitialView(Parent view) {
        this.currentView = view;
        this.backStack.clear();
        this.forwardStack.clear();
    }

    public void navigateTo(Parent newView) {
        if (currentView != null) {
            backStack.push(currentView);
        }
        forwardStack.clear();
        updateView(newView);
    }

    public void goBack() {
        if (!backStack.isEmpty()) {
            forwardStack.push(currentView);
            Parent previousView = backStack.pop();
            updateView(previousView);
        }
    }

    public void goForward() {
        if (!forwardStack.isEmpty()) {
            backStack.push(currentView);
            Parent nextView = forwardStack.pop();
            updateView(nextView);
        }
    }

    private void updateView(Parent view) {
        currentView = view;
        if (mainScene != null) {
            mainScene.setRoot(view);
        }
    }
}