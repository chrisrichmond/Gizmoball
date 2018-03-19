package Model;

import physics.Vect;

public class CollisionDetails {
	private Vect velocity;
	private double tuc;
	private String collisionType;
	public CollisionDetails(Vect velocity, double tuc, String collisionType){
		this.velocity = velocity;
		this.tuc = tuc;
		this.collisionType = collisionType;
	}
	public Vect getVelocity() {
		return velocity;
	}
	public void setVelocity(Vect velocity) {
		this.velocity = velocity;
	}
	public double getTuc() {
		return tuc;
	}
	public void setTuc(double tuc) {
		this.tuc = tuc;
	}
	public String getCollisionType(){
		return collisionType;
	}
	public void setCollisionType(String collisionType){
		this.collisionType = collisionType;
	}

}
