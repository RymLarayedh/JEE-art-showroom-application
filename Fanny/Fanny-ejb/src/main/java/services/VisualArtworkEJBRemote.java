package services;

import java.util.List;

import javax.ejb.Remote;


import entities.VisualArt;


@Remote
public interface VisualArtworkEJBRemote {
public void addVisualArt(VisualArt VA);
public void upadateVisualArt(VisualArt VA);
public void deleteVisualArt(VisualArt VA);
public VisualArt findVisualArtById(int IdVisualArt );
public List<VisualArt> findAllVisualArt ();

}
