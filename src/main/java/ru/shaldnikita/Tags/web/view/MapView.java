package ru.shaldnikita.Tags.web.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import ru.shaldnikita.Tags.web.components.TagForm;
import ru.shaldnikita.Tags.web.components.TagFormDesign;

import javax.annotation.PostConstruct;

/**
 * @author n.shaldenkov on 22.11.2017
 */

@SpringView(name = "map")
public class MapView extends MapViewDesign implements View {

    private GoogleMap googleMap;
    private TagForm tagFormDesign;

    @PostConstruct
    public void init() {
        googleMap = new GoogleMap("AIzaSyAYaCAX-wNZdkdsH1XoNPG75BOInwxPfTs", null, "russian");
        googleMap.setSizeFull();
        googleMap.setCenter(new LatLon(55.852357, 37.617480));
        googleMap.setMinZoom(10);

        tagFormDesign = new TagForm();
        tagFormDesign.setSizeFull();

        googleMap.addMapClickListener(e -> {
            if (getComponentCount() > 1)
                removeComponent(tagFormDesign);
            else
                addComponentsAndExpand(tagFormDesign);
        });

        addComponentsAndExpand(googleMap);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
