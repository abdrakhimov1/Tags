package ru.shaldnikita.Tags.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import ru.shaldnikita.Tags.app.HasLogger;
import ru.shaldnikita.Tags.web.navigation.NavigationManager;
import ru.shaldnikita.Tags.web.view.AccessDeniedView;

/**
 * @author n.shaldenkov on 19.11.2017
 */

@SpringUI
@Viewport("width=device-width,initial-scale=1.0,user-scalable=no")
@Title("Tags")
public class VaadinUI extends UI implements HasLogger {

    private final SpringViewProvider viewProvider;

    private final NavigationManager navigationManager;

    private final MainView mainView;

    @Autowired
    public VaadinUI(SpringViewProvider viewProvider, NavigationManager navigationManager, MainView mainView) {
        this.viewProvider = viewProvider;
        this.navigationManager = navigationManager;
        this.mainView = mainView;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getLogger().info("init UI");

        setErrorHandler(event -> {
            Throwable t = DefaultErrorHandler.findRelevantThrowable(event.getThrowable());
            getLogger().error("Error during request", t);
        });

       /* // Set the theme ("globally") for all Charts
        ChartOptions.get(this).setTheme(new ChartsTheme());*/

        viewProvider.setAccessDeniedViewClass(AccessDeniedView.class);
        setContent(mainView);

        navigationManager.navigateToDefaultView();
    }
}
