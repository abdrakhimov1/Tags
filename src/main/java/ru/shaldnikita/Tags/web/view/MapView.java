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
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import ru.shaldnikita.Tags.app.HasLogger;
import ru.shaldnikita.Tags.app.security.SecurityUtils;
import ru.shaldnikita.Tags.backend.model.Tag;
import ru.shaldnikita.Tags.backend.repositories.TagRepository;
import ru.shaldnikita.Tags.backend.service.UserService;
import ru.shaldnikita.Tags.web.components.TagForm;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author n.shaldenkov on 22.11.2017
 */

@SpringView(name = "map")
public class MapView extends MapViewDesign implements View,HasLogger {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    UserService userService;

    private final int maxZoom=18;
    private final int minZoom=10;
    private int curZoom=10;
    private long idCount = 0;

    private GoogleMap googleMap;

    @Autowired
    private TagForm tagForm;

    @PostConstruct
    public void init() {
        googleMap = new GoogleMap("AIzaSyAYaCAX-wNZdkdsH1XoNPG75BOInwxPfTs", null, "russian");
        googleMap.setSizeFull();
        googleMap.setCenter(new LatLon(55.852357, 37.617480));
        googleMap.setMinZoom(minZoom);
        googleMap.setMaxZoom(maxZoom);
        googleMap.setZoom(curZoom);

        tagForm.setSizeFull();
        tagForm.setVisible(false);
        tagForm.getCancel().addClickListener(e ->{
           closeTagForm();
        });

        googleMap.addMapClickListener(e -> handleMapClick(e));
        googleMap.addMarkerClickListener(e -> handleMarkerClick(e));

        initMarkers();

        addComponentsAndExpand(googleMap,tagForm);
    }

    private void  handleMapClick(LatLon e){
        GoogleMapMarker addMarker = new GoogleMapMarker("?",e,false);
        addMarker.setId(idCount);

            if(!tagForm.isVisible()){
                openTagForm(e);
                tagForm.getAccept().addClickListener(click ->{
                    Long newId = addTagToUser(e);
                   googleMap.removeMarker(addMarker);
                   addMarker.setId(newId);
                   googleMap.addMarker(addMarker);
                    idCount++;
                    closeTagForm();
                });
                googleMap.addMarker(addMarker);
            }else{
                googleMap.removeMarker(addMarker);
                closeTagForm();
        }
    }

    private void handleMarkerClick(GoogleMapMarker marker){
        Long id = marker.getId();
        getLogger().info("{}",id);
        Tag tag = tagRepository.findOne(id);

        GoogleMapInfoWindow tagInfo = new GoogleMapInfoWindow(tag.getName());
        tagInfo.setPosition(marker.getPosition());

        googleMap.openInfoWindow(tagInfo);
    }

    private void initMarkers(){

         SecurityContext context = SecurityContextHolder.getContext();
         User user = (User) context.getAuthentication().getPrincipal();

        tagRepository.getAllByAuthorEmail(user.getUsername()).forEach(tag ->{

            LatLon coordinates = new LatLon(tag.getLatitude(),tag.getLongitude());

            GoogleMapMarker marker = new GoogleMapMarker(tag.getName(),coordinates,false,null);
            marker.setId(tag.getId());

            googleMap.addMarker(marker);

        });
    }

    private void openTagForm(LatLon coords){
        googleMap.setCenter(coords);
        googleMap.setDraggable(false);

        curZoom = googleMap.getZoom();
        googleMap.setZoom(maxZoom);
        googleMap.setMinZoom(maxZoom);

        tagForm.setVisible(true);
    }

    private void closeTagForm(){
        tagForm.getCategorySelect().setValue(null);
        tagForm.getMarkerSelect().setValue(null);
        tagForm.getDescr().clear();
        tagForm.setVisible(false);

        googleMap.setMinZoom(minZoom);
        googleMap.setZoom(curZoom);
        googleMap.setDraggable(true);
    }

    private Long addTagToUser(LatLon coords){
        Tag tag = new Tag();
        tag.setLongitude(coords.getLon());
        tag.setLatitude(coords.getLat());
        tag.setAuthor(SecurityUtils.getCurrentUser(userService));
        tag.setCategories(Arrays.asList(tagForm.getCategorySelect().getValue()));
        tag.setName(tagForm.getDescr().getValue());
        tag = tagRepository.save(tag);

        return tag.getId();
    }
}
