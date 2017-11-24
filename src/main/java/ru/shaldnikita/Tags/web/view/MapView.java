package ru.shaldnikita.Tags.web.view;

import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.MapClickListener;
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
        tagForm.setVisible(false);

        GoogleMapInfoWindow infoWindow = new GoogleMapInfoWindow();
        infoWindow.setHeight(String.valueOf(tagForm.getHeight()*1.5)+tagForm.getHeightUnits());
        infoWindow.setId(1l);
        infoWindow.setAnchorMarker(new GoogleMapMarker());

        googleMap.setInfoWindowContents(infoWindow,tagForm);
        googleMap.addMapClickListener(e -> handleMapClick(e));

        addComponentsAndExpand(googleMap,tagForm);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    private void  handleMapClick(LatLon e){
        GoogleMapMarker addMarker = new GoogleMapMarker("?",e,false, "fonticon://Vaadin-Icons/e80d"+VaadinIcons.QUESTION_CIRCLE.getCodepoint());
        addMarker.setId(-1);
            if(!tagForm.isVisible()){

                googleMap.addMarker(addMarker);
                tagForm.setVisible(true);
            }else{

                tagForm.setVisible(false);
                googleMap.removeMarker(addMarker);
        }

    }
}
