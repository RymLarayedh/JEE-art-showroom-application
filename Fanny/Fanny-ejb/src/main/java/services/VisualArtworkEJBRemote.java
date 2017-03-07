package services;

import javax.ejb.Remote;

import entities.VisualArt;

@Remote
public interface VisualArtworkEJBRemote {
public void addVisualArt(VisualArt VA);
}
