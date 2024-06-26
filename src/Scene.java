import java.util.ArrayList;

import ky.Asset;
import ky.CollisionEntity;
import ky.Entity;

public class Scene {

	public void initialize() {
	}

	private ArrayList<ArrayList<Asset>> assetLayers = new ArrayList<ArrayList<Asset>>();

	private ArrayList<ArrayList<Entity>> entityLayers = new ArrayList<ArrayList<Entity>>();

	private ArrayList<CollisionEntity> collisionEntities = new ArrayList<CollisionEntity>();

	int width = Main.width;
	int height = Main.height;

	protected Main main;
	public boolean hasInitialized = false;

	public Scene(Main main) {
		this.main = main;
	}

	public void setScale(double scale) { // scales everything in scene (not used yet)
		for (ArrayList<Asset> layer : assetLayers) {
			for (Asset e : layer) {
				e.rescale(scale);
			}
		}
		for (ArrayList<Entity> layer : entityLayers) {
			for (Entity e : layer) {
				for (Asset[] layerj : e.getAssetLayers()) {
					for (Asset ej : layerj) {
						ej.rescale(scale);
					}
				}
			}
		}
		for (CollisionEntity e : collisionEntities) {
			for (Asset[] layerj : e.getAssetLayers()) {
				for (Asset ej : layerj) {
					ej.rescale(scale);
				}
			}
		}

	}

	public void delete() {
		assetLayers = new ArrayList<ArrayList<Asset>>();
		entityLayers = new ArrayList<ArrayList<Entity>>();
		collisionEntities = new ArrayList<CollisionEntity>();
	}

	public void add(Character character) {
		add((CollisionEntity) character);
		CollisionEntity[] characterEntities = character.getEntities();
		for (CollisionEntity entity : characterEntities) {
			add(entity);
		}
	}

	public void add(Asset asset) {
		int difference = asset.getLayer() + 1 - assetLayers.size();// check if the indicated layer exists or not
		if (difference > 0) { // if difference is greater than 0,
			for (int i = 0; i < difference; i++) { // there needs to be filler layers to reach the indicated layer
				assetLayers.add(new ArrayList<Asset>());
			}
		}
		if (!assetLayers.get(asset.getLayer()).contains(asset)) { // add to layer
			assetLayers.get(asset.getLayer()).add(asset);
		}
	}

	public void add(Entity entity) {

		int difference = entity.getLayer() + 1 - entityLayers.size();// check if the indicated layer exists or not
		if (difference > 0) { // if difference is greater than 0,
			for (int i = 0; i < difference; i++) { // there needs to be filler layers to reach the indicated layer
				entityLayers.add(new ArrayList<Entity>());
			}
		}
		if (!entityLayers.get(entity.getLayer()).contains(entity)) { // add to layer
			entityLayers.get(entity.getLayer()).add(entity);
		}

		// this keeps track of all collision entities
		if (entity instanceof CollisionEntity && !collisionEntities.contains(entity)) {
			collisionEntities.add((CollisionEntity) entity);
			// ((CollisionEntity) entity).onCollision();
		}
	}

	public ArrayList<ArrayList<Entity>> getEntityLayers() {
		return entityLayers;
	}

	public ArrayList<ArrayList<Asset>> getAssetLayers() { // gets all assets whilst keeping the layers
		return assetLayers;
	}

	public ArrayList<CollisionEntity> getCollisionEntities() {
		return collisionEntities;
	}

	public void update(double deltaT, ArrayList<Integer> keyCodes) {

	}

	public void onSceneLoad() {

	}

	public void keyPressed(int keyCode) {

	}

	public void keyReleased(int keyCode) {

	}

	public void keyTyped(int keyCode) {

	}

}