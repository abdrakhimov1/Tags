package ru.shaldnikita.Tags.web;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;
import ru.shaldnikita.Tags.app.HasLogger;

import javax.annotation.PostConstruct;

/**
 * @author n.shaldenkov on 19.11.2017
 */

@SpringViewDisplay
@UIScope
public class MainView extends MainViewDesign implements ViewDisplay,HasLogger{

    @Override
    public void showView(View view) {
        content.removeAllComponents();
        content.addComponent(view.getViewComponent());
    }
}
