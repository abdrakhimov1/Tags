package ru.shaldnikita.Tags.web.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapInfoWindow;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import org.springframework.beans.factory.annotation.Autowired;
import ru.shaldnikita.Tags.web.components.TagForm;

import javax.annotation.PostConstruct;

/**
 * @author n.shaldenkov on 22.11.2017
 */

@SpringView(name = "map")
public class MapView extends MapViewDesign implements View {

    private GoogleMap googleMap;

    @Autowired
    private TagForm tagForm;

    @PostConstruct
    public void init() {
        googleMap = new GoogleMap("AIzaSyAYaCAX-wNZdkdsH1XoNPG75BOInwxPfTs", null, "russian");
        googleMap.setSizeFull();
        googleMap.setCenter(new LatLon(55.852357, 37.617480));
        googleMap.setMinZoom(10);

        tagForm.setSizeFull();
        //tagForm.setVisible(false);

        GoogleMapInfoWindow infoWindow = new GoogleMapInfoWindow();
        infoWindow.setHeight(String.valueOf(tagForm.getHeight()*1.5)+tagForm.getHeightUnits());
        infoWindow.setId(1l);
        infoWindow.setAnchorMarker(new GoogleMapMarker());

        googleMap.setInfoWindowContents(infoWindow,tagForm);
        googleMap.addMapClickListener(e -> {

            if (!googleMap.isInfoWindowOpen(infoWindow)) {
                infoWindow.setPosition(new LatLon(e.getLat(),e.getLon()));
                googleMap.openInfoWindow(infoWindow);//tagForm.setVisible(false);
            }
            else {
                googleMap.closeInfoWindow(infoWindow);
                //tagForm.setVisible(true);
            }
        });

        addComponentsAndExpand(googleMap);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
